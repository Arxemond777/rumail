package ru.mail.controller;

import org.springframework.web.bind.annotation.*;
import ru.mail.dto.StatsByReceivedQueriesDto;

import static ru.mail.api.event.EventAPIImpl.queueEvents;

@RestController
public class StatController {

    @GetMapping("/get_stat_by_events")
    public StatsByReceivedQueriesDto getStat() {
        return new StatsByReceivedQueriesDto(queueEvents.size(), queueEvents);
    }

}