package com.sales.record.consumer;

import com.sales.record.infra.SalesRecords;
import com.sales.record.model.SalesMessage;
import com.sales.record.producer.ISalesMessageProducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class SalesNotificationConsumer implements ISalesNotificationConsumer {
    private ISalesMessageProducer salesMessageProducer;
    private SalesRecords salesRecords;
    private int aggreagteCount;
    private int adjustmentCount;

    public SalesNotificationConsumer(ISalesMessageProducer salesMessageProducer, int aggreagteCount, int adjustmentCount) {
        salesRecords = new SalesRecords(new HashMap<>());
        this.salesMessageProducer = salesMessageProducer;
        this.adjustmentCount = adjustmentCount;
        this.aggreagteCount = aggreagteCount;
    }

    public void consumeSalesNotificationMessage() throws InterruptedException, IOException {
        while (true) {
            while (!salesMessageProducer.getRecords().isEmpty()) {
                SalesMessage salesMessage = salesMessageProducer.getRecords().poll(10, TimeUnit.SECONDS);
                if (salesMessage != null) {
                    salesRecords.addSales(salesMessage);
                    if (salesRecords.getSalesMessages().size() % 10 == 0) {
                        PrintSales printSales = new PrintSales(salesRecords);
                        printSales.printSales();
                    }
                    if (salesRecords.getSalesMessages().size() % 50 == 0) {
                        PrintSales printSales = new PrintSales(salesRecords);
                        printSales.logLatestAdjustments();
                    }
                }
            }
            if (salesMessageProducer.getRecords().isEmpty()) {
                salesMessageProducer.readSampleData();
            }
        }
    }


}
