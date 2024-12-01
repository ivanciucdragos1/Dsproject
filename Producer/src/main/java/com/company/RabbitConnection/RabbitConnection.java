package com.company.RabbitConnection;

import com.company.CsvReader.CsvReader;
import com.company.EnergyConsumption.EnergyConsumption;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class RabbitConnection {

    private final CsvReader csvReader = new CsvReader();
    private UUID device_Id;
    private final String RabbitURL = "amqps://jvdeskzk:OnOHm4qmHlmQ70K5k-WODjJWE1WoAsDj@beaver.rmq.cloudamqp.com/jvdeskzk";
    private final String QUEUE_NAME = "sensor";

    public RabbitConnection( UUID device_Id) {
        this.device_Id = device_Id;
    }

    public void sendData() throws InterruptedException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(this.RabbitURL);
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        long timestamp = System.currentTimeMillis();
        int i = 0;
        csvReader.readSensor();
        ArrayList<Double> data = csvReader.getData();
        for(double m : data){
            EnergyConsumption energyConsumption = new EnergyConsumption(timestamp, this.device_Id, m);
            timestamp += 600000;
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(energyConsumption);
            channel.basicPublish("", QUEUE_NAME,null, jsonString.getBytes());
            Thread.sleep(1000);
            System.out.println(m);
        }
    }
}
