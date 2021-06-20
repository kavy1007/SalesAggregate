package com.sales.record;

import com.sales.record.consumer.SalesNotificationConsumer;
import com.sales.record.model.ProductAdjustment;
import com.sales.record.model.SalesMessage;
import com.sales.record.model.SalesType;
import com.sales.record.producer.ISalesMessageProducer;
import com.sales.record.producer.SalesMessageProducerImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TestSalesNotification {
    @Test
    public void testSales() throws IOException, InterruptedException {
        ISalesMessageProducer salesMessageProducer = new SalesMessageProducerImpl();
        SalesNotificationConsumer salesNotificationConsumer = new SalesNotificationConsumer(salesMessageProducer, 10, 50);
        salesNotificationConsumer.consumeSalesNotificationMessage();
    }

    public static BlockingQueue<SalesMessage> getSalesMessages() throws IOException {
        BlockingQueue<SalesMessage> queue = new LinkedBlockingDeque<SalesMessage>();

        SalesMessage salesMessage = new SalesMessage(1, null,
                SalesType.SALE, BigDecimal.TEN, "APPLE", BigDecimal.ZERO);
        SalesMessage salesMessage1 = new SalesMessage(10, null,
                SalesType.SALE, BigDecimal.TEN, "APPLE", BigDecimal.ZERO);
        SalesMessage salesMessage2 = new SalesMessage(10, null,
                SalesType.SALE, BigDecimal.TEN, "ORANGE", BigDecimal.ZERO);
        SalesMessage adjustSalesMessage2 = new SalesMessage(10, ProductAdjustment.ADD,
                SalesType.ADJUSTMENT, BigDecimal.TEN, "APPLE", BigDecimal.ONE);
        Arrays.asList(salesMessage,
                salesMessage, salesMessage, salesMessage, salesMessage, salesMessage2,
                salesMessage2, adjustSalesMessage2, salesMessage2, salesMessage, salesMessage, salesMessage, salesMessage1,
                salesMessage1, adjustSalesMessage2, salesMessage, salesMessage2, salesMessage, salesMessage, salesMessage1,
                salesMessage1, adjustSalesMessage2, salesMessage, salesMessage, salesMessage, salesMessage, salesMessage1,
                salesMessage1, adjustSalesMessage2).forEach(queue::offer);
        return queue;
    }
}
