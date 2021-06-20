package com.sales.record.model;

import java.math.BigDecimal;

public enum ProductAdjustment {
    ADD {
        @Override
        public BigDecimal adjustPrice(BigDecimal totalPrice, BigDecimal totalAdjustmentPrice) {
            return totalPrice.add(totalAdjustmentPrice);
        }
    },
    SUBTRACT {
        @Override
        public BigDecimal adjustPrice(BigDecimal totalPrice, BigDecimal totalAdjustmentPrice) {
            return totalPrice.subtract(totalAdjustmentPrice);
        }
    },
    MULTIPLY {
        @Override
        public BigDecimal adjustPrice(BigDecimal totalPrice, BigDecimal totalAdjustmentPrice) {
            return totalPrice.multiply(totalAdjustmentPrice);
        }
    };

    public abstract BigDecimal adjustPrice(BigDecimal totalPrice, BigDecimal totalAdjustmentPrice);
}
