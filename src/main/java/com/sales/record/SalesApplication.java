package com.sales.record;

import com.sales.record.consumer.SalesNotificationConsumer;
import com.sales.record.producer.ISalesMessageProducer;
import com.sales.record.producer.SalesMessageProducerImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class SalesApplication {
    private static final Logger logger = Logger.getLogger(SalesApplication.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        Properties properties = readProperties();
        ISalesMessageProducer salesMessageProducer = new SalesMessageProducerImpl();
        SalesNotificationConsumer salesNotificationConsumer = new SalesNotificationConsumer(salesMessageProducer,
                Integer.parseInt(properties.getProperty("report.aggreagteCount")),
                Integer.parseInt(properties.getProperty("report.adjustmentCount")));
        salesNotificationConsumer.consumeSalesNotificationMessage();
    }


    private static Properties readProperties() throws IOException {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            return prop;
        }
    }

}
