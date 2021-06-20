package com.sales.record.infra;

import com.sales.record.model.SalesMessage;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesRecords {
    private Map<String, ProductSalesRepo> productSalesRecord;
    private List<SalesMessage> salesMessages = new ArrayList<>();

    public SalesRecords(Map<String, ProductSalesRepo> productSalesRecord) {
        this.productSalesRecord = productSalesRecord;
    }

    public Map<String, ProductSalesRepo> getProductSalesRecord() {
        return productSalesRecord;
    }

    public List<SalesMessage> getSalesMessages() {
        return salesMessages;
    }


    public void addSales(SalesMessage salesMessage) {
        salesMessage.setSalesTimeStamp(Instant.now());
        salesMessages.add(salesMessage);
    }


    public void aggregateSales() {
        List<SalesMessage> recentSales = salesMessages.subList(salesMessages.size() - 10, salesMessages.size());
        Map<String, List<SalesMessage>> productsalesMessages = recentSales.stream()
                .collect(Collectors.groupingBy(SalesMessage::getProductType));
        productsalesMessages.forEach((key, value) -> {
            ProductSalesRepo productSales = productSalesRecord
                    .getOrDefault(key, new ProductSalesRepo());
            productSales.calculateTotalSalesPrice(value);
            productSalesRecord.put(key, productSales);
        });
    }


}
