package com.example.monitoringmanagement.service;

import com.example.monitoringmanagement.dtos.EnergyConsumptionDTO;
import com.example.monitoringmanagement.entities.EnergyConsumption;
import com.example.monitoringmanagement.repositories.EnergyConsumptionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static com.example.monitoringmanagement.MonitoringManagementApplication.exceeded;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final EnergyConsumptionRepository energyConsumptionRepository;
    private final String QUEUE_URL = "amqps://jvdeskzk:OnOHm4qmHlmQ70K5k-WODjJWE1WoAsDj@beaver.rmq.cloudamqp.com/jvdeskzk";
    private final String QUEUE_REQUEST_URL = "amqps://vbgmwnox:vt67SYk8B1sMWwQ1rv6ykuCHQLzndC1z@possum.lmq.cloudamqp.com/vbgmwnox";
    private final String QUEUE_REPLY_URL = "amqps://jiasdvbw:QWbSGsO-Fv1e8Ltl4li8WNValfV7ZOJa@seal.lmq.cloudamqp.com/jiasdvbw";
    private final String QUEUE_NAME = "sensor";
    private final String QUEUE_REQ_NAME = "request";
    private final String QUEUE_REP_NAME = "reply";
    private Connection connection;
    private Channel channel;
    private Connection connection2;
    private Channel channel2;
    private ObjectMapper objectMapper = new ObjectMapper();
    private double currentConsumption = 0;
    private Date currentHour = new Date();
    public AtomicInteger max = new AtomicInteger();

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostConstruct
    public void setupConnection() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(QUEUE_URL);
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        ConnectionFactory factory2 = new ConnectionFactory();
        factory2.setUri(QUEUE_REPLY_URL);
        connection2 = factory2.newConnection();
        channel2 = connection2.createChannel();
        channel2.queueDeclare(QUEUE_REP_NAME, false, false, false, null);
        consume();
        consumeReply();
    }

    public void sendReq(UUID deviceId) throws InterruptedException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(this.QUEUE_REQUEST_URL);
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_REQ_NAME, false, false, false, null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(deviceId);
        channel.basicPublish("", QUEUE_REQ_NAME,null, jsonString.getBytes());
        Thread.sleep(1000);
        conn.close();
    }

    public void consume() throws Exception{
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            EnergyConsumptionDTO data = objectMapper.readValue(message, EnergyConsumptionDTO.class);
            try {
                sendReq(data.getDevice_id());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("here:" + max.get());
            int currentHour = new Date(data.getTime()).getHours();
            if(this.currentHour.getHours() == currentHour){
                this.currentConsumption += data.getValue();
                if(this.currentConsumption > max.get()){
                    exceeded = true;
                    System.out.println("sending message");
                    messagingTemplate.convertAndSend("/topic/alerts", "Value exceeded");
                }
            }
            else{
                EnergyConsumption energyConsumption = EnergyConsumption.builder()
                        .timestamp(this.currentHour.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .hrConsumption(data.getValue())
                        .deviceId(data.getDevice_id())
                        .build();
                this.currentHour = new Date(data.getTime());
                energyConsumptionRepository.save(energyConsumption);
            }
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
        exceeded = false;
    }

    public void consumeReply() throws IOException {

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            max.set(Integer.parseInt(message));
            System.out.println(max);
        };

        channel2.basicConsume(QUEUE_REP_NAME, true, deliverCallback, consumerTag -> {});
    }

}
