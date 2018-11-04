package ru.mail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static ru.mail.util.StringUtils.escapeCharacters;

public class SearchResultDto {

    @JsonProperty("search_engine")
    private String searchEngine;
    private List<String> values;

    public String getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');
        stringBuilder.append("\"search_engine\":\"");
        stringBuilder.append(searchEngine);
        stringBuilder.append("\",\"values\":");

//         if not enough the such format [elem1, elem2] and required strict the such one ["elem1","elem2"]

        final StringBuilder stringBuilderValues = new StringBuilder();
        stringBuilderValues.append('[');
        values.forEach(val -> {
            stringBuilderValues.append('"');
            stringBuilderValues.append(escapeCharacters(val));
            stringBuilderValues.append('"');
            if (!val.equals(values.get(values.size()-1)))
                stringBuilderValues.append(',');
        });
        stringBuilderValues.append(']');
        stringBuilder.append(stringBuilderValues);

//         if enough [elem1, elem2]
//        stringBuilder.append(values);
        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}
