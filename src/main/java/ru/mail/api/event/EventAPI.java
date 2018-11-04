package ru.mail.api.event;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import ru.mail.dto.SearchResultDto;

import java.sql.Timestamp;

import static ru.mail.api.event.RoutingApi.*;

@JsonRpcService(SEND_EVENT_MSG_ROUTE)
public interface EventAPI {
    @JsonRpcMethod(value = SEND_EVENT_METHOD_NAME)
    String saveToCh(
            @JsonRpcParam(value = "event_timestamp") Timestamp eventTimeStamp,
            @JsonRpcParam(value = "uid") String uid,
            @JsonRpcParam(value = "search_text") String searchText,
            @JsonRpcParam(value = "long") float longGeo,
            @JsonRpcParam(value = "lat") float latGeo,
            @JsonRpcParam(value = "search_result") SearchResultDto searchResult
    );
}
