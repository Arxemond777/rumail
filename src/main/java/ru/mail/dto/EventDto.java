package ru.mail.dto;

import java.sql.Timestamp;

import static ru.mail.util.DateUtil.FormatPatterns.*;
import static ru.mail.util.DateUtil.*;
import static ru.mail.util.StringUtils.*;

public class EventDto {
    private final Timestamp eventTimeStamp;
    private final String uid;
    private final String searchText;
    private final float longGeo;
    private final float latGeo;
    private final SearchResultDto searchResultDto;

    public EventDto(Timestamp eventTimeStamp, String uid, String searchText, float longGeo, float latGeo, SearchResultDto searchResultDto) {
        this.eventTimeStamp = eventTimeStamp;
        this.uid = uid;
        this.searchText = searchText;
        this.longGeo = longGeo;
        this.latGeo = latGeo;
        this.searchResultDto = searchResultDto;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('(');

        stringBuilderWrapStringWithComma(stringBuilder, formatTimestampToFormatPatterns(eventTimeStamp, yyyy_MM_dd_WITH_DASHES));

        stringBuilderWrapStringWithComma(stringBuilder, uid);
        stringBuilderWrapStringWithComma(stringBuilder, getSearchText());
        stringBuilderAddComma(stringBuilder.append(longGeo));
        stringBuilderAddComma(stringBuilder.append(latGeo));
        stringBuilderWrapString(stringBuilder, searchResultDto);

        stringBuilder.append(')');

        return stringBuilder.toString();
    }

    private String getSearchText() {
        return escapeCharacters(searchText);
    }
}