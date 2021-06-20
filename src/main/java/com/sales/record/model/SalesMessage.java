package com.sales.record.model;

import java.math.BigDecimal;
import java.time.Instant;

public class SalesMessage implements Comparable<SalesMessage> {
    private int qty = 1;
    private Instant createTs;
    private ProductAdjustment productAdjustment;
    private SalesType salesType;
    private BigDecimal unitPrice;
    private ProductType productType;
    private BigDecimal adjustmentPrice;

    public SalesMessage(int qty, ProductAdjustment productAdjustment,
                        SalesType salesType, BigDecimal unitPrice,
                        ProductType productType, BigDecimal adjustmentPrice) {
        this.qty = qty;
        this.productAdjustment = productAdjustment;
        this.salesType = salesType;
        this.unitPrice = unitPrice;
        this.productType = productType;
        this.adjustmentPrice = adjustmentPrice;
    }

    public boolean isSales() {
        return salesType.equals(SalesType.SALE);
    }

    public BigDecimal calculateSales() {
        return unitPrice.multiply(BigDecimal.valueOf(qty));
    }

    public ProductAdjustment getProductAdjustment() {
        return productAdjustment;
    }

    public int getQty() {
        return qty;
    }

    public BigDecimal getAdjustmentPrice() {
        return adjustmentPrice;
    }

    public ProductType getProductType() {
        return productType;
    }

    @Override
    public int compareTo(SalesMessage salesMessage) {
        if (this.createTs.isAfter(salesMessage.createTs)) {
            return 1;
        }
        return -1;
    }

    public void setSalesTimeStamp(Instant instant) {
        createTs = instant;
    }

    public boolean isAdjustment() {
        return salesType.equals(SalesType.ADJUSTMENT);
    }

    public Instant getCreateTs() {
        return createTs;
    }

}

