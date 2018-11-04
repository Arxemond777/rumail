package ru.mail.api.event;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import ru.mail.dto.EventDto;
import ru.mail.dto.SearchResultDto;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Queue;

import static ru.mail.api.event.RoutingApi.SEND_EVENT_MSG_ROUTE;

@Controller
@AutoJsonRpcServiceImpl
public class EventAPIImpl implements EventAPI {
    @Autowired
    private MessageSource messageSource;

    private static final Logger log = LoggerFactory.getLogger(EventAPIImpl.class);

    public final static Queue<String> queueEvents = new LinkedList<>();

    @Override
    public String saveToCh(
            Timestamp eventTimeStamp,
            String uid,
            String searchText,
            float longGeo,
            float latGeo,
            SearchResultDto searchResult
    ) {

        EventDto eventDto = new EventDto(eventTimeStamp, uid, searchText, longGeo, latGeo, searchResult);
// committed for disable the spam messages when there is highload
//        log.info(messageSource.getMessage("api.event.received.msg", new Object[]{SEND_EVENT_MSG_ROUTE,eventDto}, null));

        queueEvents.offer(eventDto.toString());
        return "Ok";
    }
}












//'{"json-rpc":2.0, "id":123,"method":"save_to_ch", "params":{"event_timestamp": 1535068800,"uid": "uid_123","search_text": "Тестовы запрос \t в базу КХ\n","long": 55.1111,"lat": 33.2222,"search_result": {"search_engine":"Test","values":["prod_uid_1","prod_uid_2","prod_uid_3","prod_uid_4"]}}}'