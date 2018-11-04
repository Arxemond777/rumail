package ru.mail.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    private static final Map<String, String> escapeCharacters = new HashMap<String, String>() {{
        put("\t", "\\\\t");
        put("\n", "\\\\n");
    }};

    /**
     * Make invisible characters visible ones
     * @param inputString - string with invisible characters
     * @return - string with visible characters
     */
    public static String escapeCharacters(String inputString) {

        for (Map.Entry<String, String> entry : escapeCharacters.entrySet()){
            if(inputString.contains(entry.getKey())){
                inputString = inputString.replace(entry.getKey(), entry.getValue());
            }
        }
        return inputString;
    }

    public static StringBuilder stringBuilderWrapString(StringBuilder sb, Object value) {
        sb.append("\'");
        sb.append(value);
        sb.append("\'");

        return sb;
    }
    public static StringBuilder stringBuilderWrapStringWithComma(StringBuilder sb, Object value) {
        stringBuilderWrapString(sb,value);
        stringBuilderAddComma(sb);

        return sb;
    }

    public static StringBuilder stringBuilderAddComma(StringBuilder sb) {
        sb.append(',');

        return sb;
    }
}
