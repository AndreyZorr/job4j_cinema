package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.FilmSessionDto;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionDtoService {

    Optional<FilmSessionDto> create(int filmSessionId);

    Collection<FilmSessionDto> findAll();
}
