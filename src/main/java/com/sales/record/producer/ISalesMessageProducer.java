package com.sales.record.producer;

import com.sales.record.model.SalesMessage;

import java.util.stream.Stream;

public interface ISalesMessageProducer {
    Stream<SalesMessage> publishSalesRecords();
}
