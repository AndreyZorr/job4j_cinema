package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.FilmDto;

import java.util.Collection;
import java.util.Optional;

public interface FilmDtoService {

    Optional<FilmDto> create(int filmId);

    Collection<FilmDto> findAll();
}
