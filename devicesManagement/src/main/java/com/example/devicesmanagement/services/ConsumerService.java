package com.example.devicesmanagement.services;

import com.example.devicesmanagement.entities.Device;
import com.example.devicesmanagement.repositories.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static com.example.devicesmanagement.DevicesManagementApplication.exceeded;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final DeviceRepository deviceRepository;
    private final String QUEUE_REQUEST_URL = "amqps://vbgmwnox:vt67SYk8B1sMWwQ1rv6ykuCHQLzndC1z@possum.lmq.cloudamqp.com/vbgmwnox";
    private final String QUEUE_NAME = "request";
    private final String QUEUE_REPLY_URL = "amqps://jiasdvbw:QWbSGsO-Fv1e8Ltl4li8WNValfV7ZOJa@seal.lmq.cloudamqp.com/jiasdvbw";
    private final String QUEUE_REPLY = "reply";
    private Connection connection;
    private Channel channel;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void setupConnection() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(QUEUE_REQUEST_URL);
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        consume();
    }

    public void sendData(int max) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(this.QUEUE_REPLY_URL);
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_REPLY, false, false, false, null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(max);
        channel.basicPublish("", QUEUE_REPLY,null, jsonString.getBytes());
        Thread.sleep(1000);
        conn.close();
    }

    public void consume() throws Exception{
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(message);
            Device device = deviceRepository.getDeviceById(UUID.fromString(message.substring(1,message.length()-1))).orElse(null);
            try {
                sendData(device.getMaxHrConsumption());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(message);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
        exceeded = false;
    }

}
