package ru.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.mail.component.ClickHousePropertyComponent;

import javax.annotation.PostConstruct;
import java.sql.*;

import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class ClickHouseJDBC implements AutoCloseable {
    @Autowired
    private ClickHousePropertyComponent clickHousePropertyComponent;
    @Autowired
    private MessageSource messageSource;

    private static final Logger log = LoggerFactory.getLogger(ClickHouseJDBC.class);
    private Connection conn;
    private final String INSERT_INTO_TEST = "insert into test values";

    /**
     * Creates new instance
     *
     * @throws SQLException in case of connection issue
     */
    @PostConstruct
    public void init() throws SQLException {
        conn = DriverManager.getConnection(clickHousePropertyComponent.getURIClickHouse());
    }


    public void insertIntoTest(String query) {
        if (isEmpty(query)) return;

        StringBuilder st = new StringBuilder();
        st.append(INSERT_INTO_TEST);
        st.append(query);
// committed for disable the spam messages when there is highload
//        log.info(messageSource.getMessage("click.house.execute.insert", new Object[]{st.toString()}, null));


        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(st.toString());
        } catch (Exception e) {
            log.warn(messageSource.getMessage("click.house.failed.send.msg.to.click.house", new Object[]{e.getMessage()}, null));
        }
    }

    @Override
    public void close() throws Exception {
        if (nonNull(conn))
            conn.close();
    }
}