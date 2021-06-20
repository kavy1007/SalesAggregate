package com.sales.record.consumer;

import java.io.IOException;

public interface ISalesNotificationConsumer {
    void consumeSalesNotificationMessage() throws InterruptedException, IOException;
}
