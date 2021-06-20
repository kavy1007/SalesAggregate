package com.sales.record.consumer;

import com.sales.record.infra.SalesRecords;
import com.sales.record.producer.ISalesMessageProducer;

import java.util.HashMap;

public class SalesNotificationConsumer implements ISalesNotificationConsumer {
    private ISalesMessageProducer salesMessageProducer;
    private SalesRecords salesRecords;

    public SalesNotificationConsumer(ISalesMessageProducer salesMessageProducer) {
        salesRecords = new SalesRecords(new HashMap<>());
        this.salesMessageProducer = salesMessageProducer;
    }

    public void consumeSalesNotificationMessage() {
        salesMessageProducer.publishSalesRecords()
                .map(salesMessage -> {
                    salesRecords.addSales(salesMessage);
                    return salesRecords;
                })
                .filter(salesRecords1 -> salesRecords1.getSalesMessages().size() % 10 == 0)
                .peek(salesRecords1 -> {
                    PrintSales printSales = new PrintSales(salesRecords1);
                    printSales.printSales();
                })
                .filter(salesRecords1 -> salesRecords1.getSalesMessages().size() % 50 == 0)
                .forEach(salesRecords1 -> {
                    PrintSales printSales = new PrintSales(salesRecords1);
                    printSales.logLatestAdjustments();
                });

    }


}
