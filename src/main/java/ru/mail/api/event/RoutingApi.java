package ru.mail.api.event;

public class RoutingApi {
    public static final String SEND_EVENT_MSG_ROUTE = "/send_event_msg";

    /**
     * The method name by the technical specification
     * "Пример тела POST запроса :
     * ```json
     * {"json-rpc":2.0, "id":123,"method":"save_to_ch", "params":"
     */
    public static final String SEND_EVENT_METHOD_NAME = "save_to_ch";
}
