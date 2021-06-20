package com.sales.record;

import com.sales.record.consumer.SalesNotificationConsumer;
import com.sales.record.producer.ISalesMessageProducer;
import org.junit.jupiter.api.Test;

public class TestSalesNotification {
    @Test
    public void testSales() {
        ISalesMessageProducer salesMessageProducer = new SalesMessageProducerImpl();
        SalesNotificationConsumer salesNotificationConsumer = new SalesNotificationConsumer(salesMessageProducer);
        salesNotificationConsumer.consumeSalesNotificationMessage();
    }
}
