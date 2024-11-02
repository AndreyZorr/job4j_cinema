package ru.job4j.cinema.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.*;

class IndexControllerTest {

    @Test
    void whenRequestIndexThenGetIndexPage() {
        var indexController = new IndexController();
        var view = indexController.getIndex();
        assertThat(view).isEqualTo("index");
    }
}