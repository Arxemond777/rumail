package ru.mail.dto;

import java.util.Queue;

public class StatsByReceivedQueriesDto {
    private final long count;
    private final Queue<String> items;

    public StatsByReceivedQueriesDto(long count, Queue<String> items) {
        this.count = count;
        this.items = items;
    }

    public long getCount() {
        return count;
    }

    public Queue<String> getItems() {
        return items;
    }
}
