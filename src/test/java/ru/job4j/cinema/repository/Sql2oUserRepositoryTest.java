package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.user.Sql2oUserRepository;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Sql2oUserRepositoryTest {

    private static Sql2oUserRepository sql2oUserRepository;

    private static Sql2o cleanUsers;

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

        cleanUsers = sql2o;
        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @AfterEach
    public void cleanUsers() {
        try (var connection = cleanUsers.open()) {
            connection.createQuery("DELETE FROM USERS").executeUpdate();
        }
    }

    @Test
    void whenSaveUserThenSuccess() {
        var user = new User(0, "test@mail.com", "Test User", "password");
        var savedUser = sql2oUserRepository.save(user).get();
        var fetchedUser = sql2oUserRepository.findByEmailAndPassword("test@mail.com", "password").get();
        assertThat(fetchedUser).usingRecursiveComparison().isEqualTo(savedUser);
    }
}
