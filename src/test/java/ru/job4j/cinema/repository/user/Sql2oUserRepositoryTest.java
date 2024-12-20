package ru.job4j.cinema.repository.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.User;

import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oUserRepositoryTest {
    private static Sql2oUserRepository sql2oUserRepository;

    private static Sql2o cleanTableClient;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oUserRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        cleanTableClient = sql2o;
        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @AfterEach
    public void cleanTable() {
        try (var connection = cleanTableClient.open()) {
            connection.createQuery("DELETE FROM USERS").executeUpdate();
        }
    }

    @Test
    public void whenSaveThenGetSame() {
        var user = sql2oUserRepository.save(new User(0, "Vlad", "vlad@email.com", "vlad"));
        var savedUser = sql2oUserRepository.findByEmailAndPassword(user.orElseThrow().getEmail(), user.orElseThrow().getPassword());
        assertThat(savedUser.orElseThrow()).usingRecursiveComparison().isEqualTo(user.orElseThrow());
    }

    @Test
    public void whenSaveSeveralThenGetAll() {
        var user1 = sql2oUserRepository.save(new User(0, "Sem", "sem@email.com", "sem"));
        var savedUser1 = sql2oUserRepository.findByEmailAndPassword(user1.orElseThrow().getEmail(), user1.orElseThrow().getPassword());
        var user2 = sql2oUserRepository.save(new User(0, "Rom", "rom@email.com", "rom"));
        var savedUser2 = sql2oUserRepository.findByEmailAndPassword(user2.orElseThrow().getEmail(), user2.orElseThrow().getPassword());
        assertThat(savedUser1.orElseThrow()).usingRecursiveComparison().isEqualTo(user1.orElseThrow());
        assertThat(savedUser2.orElseThrow()).usingRecursiveComparison().isEqualTo(user2.orElseThrow());
    }
}