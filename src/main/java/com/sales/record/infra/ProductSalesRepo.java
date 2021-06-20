package com.sales.record.infra;

import com.sales.record.model.ProductAdjustment;
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
                if (salesMessage.getProductAdjustment().equals(ProductAdjustment.ADD)) {
                    totalSalesPrice = totalSalesPrice.add(salesMessage.getAdjustmentPrice().multiply(BigDecimal.valueOf(salesQty)));
                } else if (salesMessage.getProductAdjustment().equals(ProductAdjustment.SUBTRACT)) {
                    totalSalesPrice = totalSalesPrice.subtract(salesMessage.getAdjustmentPrice().multiply(BigDecimal.valueOf(salesQty)));
                } else if (salesMessage.getProductAdjustment().equals(ProductAdjustment.MULTIPLY)) {
                    totalSalesPrice = totalSalesPrice.multiply(salesMessage.getAdjustmentPrice().multiply(BigDecimal.valueOf(salesQty)));
                }
            }
        });
    }
}
