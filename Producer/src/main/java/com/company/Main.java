package com.company;

import com.company.CsvReader.CsvReader;
import com.company.RabbitConnection.RabbitConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) {
        try {
            String configFilePath = "C:\\Users\\Alex\\Desktop\\DragosProject\\Producer\\src\\main\\java\\com\\company\\config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            UUID device_id = UUID.fromString(prop.getProperty("DEVICE_ID"));
            RabbitConnection rabbitConnection = new RabbitConnection(device_id);
            rabbitConnection.sendData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
    }
}
