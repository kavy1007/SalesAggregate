package com.sales.record.producer;

import com.sales.record.model.SalesMessage;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public interface ISalesMessageProducer {
    BlockingQueue<SalesMessage> getRecords();

    void readSampleData() throws IOException, InterruptedException;
}
