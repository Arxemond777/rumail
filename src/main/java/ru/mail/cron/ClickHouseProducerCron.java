package ru.mail.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mail.component.ClickHousePropertyComponent;
import ru.mail.component.mbean.ClickHouseQueuePoolingManagerComponent;
import ru.mail.service.ClickHouseJDBC;

import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static ru.mail.api.event.EventAPIImpl.queueEvents;

/**
 * This class is consume several records (defined by
 * {@link ClickHouseQueuePoolingManagerComponent#MIN_VALUE_FOR_PROCESSING_POLL_RECORDS})
 * you can change max count of record via MBean see {@link ClickHouseQueuePoolingManagerComponent}
 * from {@link ru.mail.api.event.EventAPIImpl#queueEvents} and send ones
 * to ClickHouse {@link ClickHousePropertyComponent}
 *
 */
@Component
public class ClickHouseProducerCron {
    @Autowired
    private ClickHouseQueuePoolingManagerComponent clickHouseQueuePoolingManagerComponent;
    @Autowired
    private ClickHouseJDBC clickHouseJDBC;

    @Scheduled(fixedRate = 10)
    @Async
    public void reportCurrentTime() {
        if (!queueEvents.isEmpty()) {

            String messages = IntStream.range(0, clickHouseQueuePoolingManagerComponent.getMaxProcessingPollRecords())
                    .mapToObj(index -> queueEvents.poll())
                    .filter(Objects::nonNull)// if less then
                    // ClickHouseQueuePoolingManagerComponent.maxProcessingPollRecords of elements rest in the queue
                    .collect(joining(","));

            clickHouseJDBC.insertIntoTest(messages);

        }
    }

}