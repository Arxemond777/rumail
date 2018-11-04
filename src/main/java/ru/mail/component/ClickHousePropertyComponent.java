package ru.mail.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * This file is intended for access the ClickHouse properties
 */
@Component
public class ClickHousePropertyComponent {
    @Value("${click.house.host.port}")
    private String hostPort;
    @Value("${click.house.user}")
    private String user;
    @Value("${click.house.pass}")
    private String pass;

    private final static String URI_RESIDES_RESOURCE = "/default?user=";
    private static final String URI_PATH_PASSWORD = "&password=";

    public String getURIClickHouse() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(hostPort);
        stringBuilder.append(URI_RESIDES_RESOURCE);

        stringBuilder.append(user);

        if (!isEmpty(pass)) {
            stringBuilder.append(URI_PATH_PASSWORD);
            stringBuilder.append(pass);
        }


        return stringBuilder.toString();
    }
}
