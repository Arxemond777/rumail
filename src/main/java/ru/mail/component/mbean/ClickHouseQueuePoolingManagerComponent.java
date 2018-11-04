package ru.mail.component.mbean;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import static java.lang.Integer.signum;
/**
* You can changes max count via path $JAVA_HOME/bin/jmc for example /usr/lib/jvm/jdk1.8.0_172/bin/jmc
* in "JVM Browser" find your process -> click by arrow in the left side one ->
* choice in "select" "MBean server" -> double click -> in a new window (bellow there is tabs-panel)
* find the tab "MBean Browser" click by one -> choice ClickHouse->JMX->ClickHouseProducerCron
* and change the property which you wish.
*  */
@Component
@ManagedResource(
        objectName = "ClickHouse:type=JMX,name=ClickHouseProducerCron",
        description = "configure ClickHouseProducerCron"
)
public class ClickHouseQueuePoolingManagerComponent {

    private short maxProcessingPollRecords = 15; // max count of values in one insert
    private short MIN_VALUE_FOR_PROCESSING_POLL_RECORDS = 1;

    @ManagedAttribute(description = "ClickHouseProducerCron maxProcessingPollRecords")
    public void setMaxProcessingPollRecords(short maxProcessingPollRecords) {
        this.maxProcessingPollRecords = maxProcessingPollRecords;
    }

    /**
     * if maxProcessingPollRecords <=0 then {@link ClickHouseQueuePoolingManagerComponent#MIN_VALUE_FOR_PROCESSING_POLL_RECORDS}
     * @return max value for one operation poll from
     */
    @ManagedAttribute(description = "ClickHouseProducerCron maxProcessingPollRecords")
    public short getMaxProcessingPollRecords() {
        return signum(maxProcessingPollRecords) == 1 ? maxProcessingPollRecords : MIN_VALUE_FOR_PROCESSING_POLL_RECORDS;
    }
}
