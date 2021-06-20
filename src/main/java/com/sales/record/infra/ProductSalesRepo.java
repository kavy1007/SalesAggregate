package com.sales.record.infra;

import com.sales.record.model.SalesMessage;

import java.math.BigDecimal;
import java.util.List;

public class ProductSalesRepo {
    private int salesQty;
    private BigDecimal totalSalesPrice = BigDecimal.ZERO;

    public BigDecimal getTotalSalesPrice() {
        return totalSalesPrice;
    }

    public int getSalesQty() {
        return salesQty;
    }

    public void calculateTotalSalesPrice(List<SalesMessage> salesMessages) {
        salesMessages.forEach(salesMessage -> {
            if (salesMessage.isSales()) {
                salesQty += salesMessage.getQty();
                totalSalesPrice = totalSalesPrice.add(salesMessage.calculateSales());
            } else {
                totalSalesPrice = salesMessage.getProductAdjustment()
                        .adjustPrice(totalSalesPrice, salesMessage.getAdjustmentPrice().multiply(BigDecimal.valueOf(salesQty)));

            }
        });
    }
}
