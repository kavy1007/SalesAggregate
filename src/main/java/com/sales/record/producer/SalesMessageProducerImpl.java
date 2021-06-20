package com.sales.record.producer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sales.record.model.SalesMessage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SalesMessageProducerImpl implements ISalesMessageProducer {
    private static final Logger logger = Logger.getLogger(SalesMessageProducerImpl.class.getName());
    private static String FILE_PATH = "src/main/resources/SalesData.json";

    private BlockingQueue<SalesMessage> salesMessagesQ;

    public SalesMessageProducerImpl() throws IOException, InterruptedException {
        readSampleData();
    }

    public static void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }

    @Override
    public BlockingQueue<SalesMessage> getRecords() {
        return salesMessagesQ;
    }

    public void readSampleData() throws IOException, InterruptedException {
        salesMessagesQ = new LinkedBlockingDeque<>();
        while (true) {
            try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
                if (!br.ready()) {
                    logger.info("Waiting for the messages to be Produced");
                    TimeUnit.MILLISECONDS.sleep(2000);
                }
                if (br.ready()) {
                    List<SalesMessage> salesMessages = new Gson().fromJson(br, new TypeToken<List<SalesMessage>>() {
                    }.getType());
                    salesMessages.forEach(salesMessage -> {
                        salesMessagesQ.offer(salesMessage);
                    });
                    PrintWriter writer = new PrintWriter(FILE_PATH);
                    writer.print("");
                    return;
                }
            }
        }

    }
}
