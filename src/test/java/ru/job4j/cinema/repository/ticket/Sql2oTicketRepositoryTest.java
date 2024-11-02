package ru.job4j.cinema.repository.ticket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Ticket;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oTicketRepositoryTest {

    private static Sql2oTicketRepository sql2oTicketRepository;

    private static Sql2o sql2oTicket;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oTicketRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oTicket = sql2o;
        sql2oTicketRepository = new Sql2oTicketRepository(sql2o);
    }

    @AfterEach
    public void cleanTable() {
        try (var connection = sql2oTicket.open()) {
            connection.createQuery("DELETE FROM TICKETS").executeUpdate();
        }
    }

    @Test
    public void whenSaveThenGetSame() {
        var ticket = sql2oTicketRepository.save(new Ticket(0, 3, 5, 7, 9));
        var savedTicket = sql2oTicketRepository.findById(ticket.orElseThrow().getId());
        assertThat(savedTicket.orElseThrow()).usingRecursiveComparison().isEqualTo(ticket.orElseThrow());
    }

    @Test
    public void whenSaveExistingTicketThenNothingSave() {
        var ticket1 = sql2oTicketRepository.save(new Ticket(0, 3, 5, 7, 9));
        var ticket2 = sql2oTicketRepository.save(new Ticket(0, 3, 5, 7, 9));
        assertThat(ticket1).isNotEmpty();
        assertThat(ticket2).isEmpty();
    }
}