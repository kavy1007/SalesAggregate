package com.sales.record.consumer;

import com.sales.record.infra.ProductSalesRepo;
import com.sales.record.infra.SalesRecords;
import com.sales.record.model.SalesMessage;

import java.util.List;

public class PrintSales {
    private SalesRecords salesRecords;

    public PrintSales(SalesRecords salesRecords) {
        this.salesRecords = salesRecords;
    }


    public void printSales() {
        salesRecords.aggregateSales();
        printHeader();
        salesRecords.getProductSalesRecord().forEach(this::printSales);
        System.out.printf("%s%n", "----------------------------------------------------------------------------------------------------------------");
    }

    private void printHeader() {
        System.out.print(String.format("%s%n", "----------------------------------------------------------------------------------------------------------------"));
        System.out.print(String.format("%30s %25s %10s %25s %10s%n", "Product", "|", "Total_Price", "|", "Sales_Qty"));
        System.out.print(String.format("%s%n", "----------------------------------------------------------------------------------------------------------------"));
    }


    private void printSales(String productType, ProductSalesRepo productSales) {
        System.out.print(String.format("%30s %25s %10s %25s %10s%n", productType,
                "|", productSales.getTotalSalesPrice(), "|", productSales.getSalesQty()));
    }

    public void logLatestAdjustments() {
        List<SalesMessage> salesMessages = salesRecords.getSalesMessages();
        System.out.println("Pausing the Application to log the Adjustments");
        printAdjustmentHeader();
        List<SalesMessage> recentAdjustments = salesMessages.subList(salesMessages.size() - 50, salesMessages.size());
        recentAdjustments.stream().filter(SalesMessage::isAdjustment).forEach(this::printAdjustments);
        System.out.printf("%s%n", "----------------------------------------------------------------------------------------------------------------");
    }

    private void printAdjustmentHeader() {
        System.out.printf("%s%n", "----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%30s %25s %10s %25s %10s%n", "Product", "|", "Type Of Adjustment", "|", "TimeStamp");
        System.out.printf("%s%n", "----------------------------------------------------------------------------------------------------------------");
    }

    private void printAdjustments(SalesMessage salesMessage) {
        System.out.printf("%30s %25s %10s %25s %10s%n", salesMessage.getProductType(),
                "|", salesMessage.getProductAdjustment(), "|", salesMessage.getCreateTs());
    }

}
