package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.service.filmdto.FilmDtoService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmControllerTest {

    private FilmDtoService filmDtoService;

    private FilmController filmController;

    @BeforeEach
    public void initServices() {
        filmDtoService = mock(FilmDtoService.class);
        filmController = new FilmController(filmDtoService);
    }

    @Test
    public void whenRequestFilmListPageThenGetPageWithFilms() {
        var film1 = new Film.Builder()
                .buildId(1)
                .buildName("Name1")
                .buildDescription("Description1")
                .buildYear(1999)
                .buildGenreId(1)
                .buildMinimalAge(7)
                .buildDurationInMinutes(90)
                .buildFileId(1)
                .build();
        var film2 = new Film.Builder()
                .buildId(2)
                .buildName("Name2")
                .buildDescription("Description2")
                .buildYear(2000)
                .buildGenreId(2)
                .buildMinimalAge(17)
                .buildDurationInMinutes(95)
                .buildFileId(2)
                .build();
        var genre1 = new Genre(1, "Genre1");
        var genre2 = new Genre(2, "Genre2");
        var expectedFilms = List.of(new FilmDto(film1, genre1), new FilmDto(film2, genre2));
        when(filmDtoService.findAll()).thenReturn(expectedFilms);

        var model = new ConcurrentModel();
        var view = filmController.getAll(model);
        var actualFilms = model.getAttribute("films");

        assertThat(view).isEqualTo("films/list");
        assertThat(actualFilms).isEqualTo(expectedFilms);
    }
}