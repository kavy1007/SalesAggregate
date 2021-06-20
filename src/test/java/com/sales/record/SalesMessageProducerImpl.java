package com.sales.record;

import com.sales.record.model.ProductAdjustment;
import com.sales.record.model.ProductType;
import com.sales.record.model.SalesMessage;
import com.sales.record.model.SalesType;
import com.sales.record.producer.ISalesMessageProducer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SalesMessageProducerImpl implements ISalesMessageProducer {
    @Override
    public Stream<SalesMessage> publishSalesRecords() {
        SalesMessage salesMessage = new SalesMessage(1, null,
                SalesType.SALE, BigDecimal.TEN, ProductType.APPLE, BigDecimal.ZERO);
        SalesMessage salesMessage1 = new SalesMessage(10, null,
                SalesType.SALE, BigDecimal.TEN, ProductType.APPLE, BigDecimal.ZERO);
        SalesMessage salesMessage2 = new SalesMessage(10, null,
                SalesType.SALE, BigDecimal.TEN, ProductType.ORANGE, BigDecimal.ZERO);
        SalesMessage adjustSalesMessage2 = new SalesMessage(10, ProductAdjustment.ADD,
                SalesType.ADJUSTMENT, BigDecimal.TEN, ProductType.APPLE, BigDecimal.ONE);
        List<SalesMessage> salesMessages = Arrays.asList(salesMessage,
                salesMessage, salesMessage, salesMessage, salesMessage, salesMessage1,
                salesMessage1, adjustSalesMessage2, salesMessage2, adjustSalesMessage2
                , adjustSalesMessage2);
        return Stream.of(salesMessages.stream(), salesMessages.stream(),
                salesMessages.stream(), salesMessages.stream(), salesMessages.stream(), salesMessages.stream())
                .flatMap(salesMessageStream1 -> salesMessageStream1);
    }
}
