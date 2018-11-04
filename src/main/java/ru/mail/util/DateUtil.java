package ru.mail.util;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public enum FormatPatterns {
        yyyy_MM_dd_WITH_DASHES("yyyy-MM-dd");

        private final String format;

        FormatPatterns(String format) {
            this.format = format;
        }

        public DateTimeFormatter getFormatter() {
            return DateTimeFormatter.ofPattern(format);
        }
    }

    public static String formatTimestampToFormatPatterns(Timestamp timestamp, FormatPatterns formatPatterns) {
        return timestamp.toLocalDateTime().format(formatPatterns.getFormatter());
    }
}
