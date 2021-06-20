package com.sales.record;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sales.record.consumer.SalesNotificationConsumer;
import com.sales.record.model.SalesMessage;
import com.sales.record.producer.ISalesMessageProducer;
import com.sales.record.producer.SalesMessageProducerImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SalesApplication {
    public static void main(String[] args) throws IOException {
        readPropertiesFile();
        ISalesMessageProducer salesMessageProducer = new SalesMessageProducerImpl(readPropertiesFile());
        SalesNotificationConsumer salesNotificationConsumer = new SalesNotificationConsumer(salesMessageProducer);
        salesNotificationConsumer.consumeSalesNotificationMessage();
    }


    public static List<SalesMessage> readPropertiesFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/SalesData.json"));
        return new Gson().fromJson(br, new TypeToken<List<SalesMessage>>() {
        }.getType());

    }
}
