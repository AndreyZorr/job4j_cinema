package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.dto.HallDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.GenreRepository;
import ru.job4j.cinema.repository.HallRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleFilmSessionDtoService implements FilmSessionDtoService {

    private final FilmSessionRepository filmSessionRepository;

    private final FilmRepository filmRepository;

    private final GenreRepository genreRepository;

    private final HallRepository hallRepository;

    public SimpleFilmSessionDtoService(FilmSessionRepository filmSessionRepository, FilmRepository filmRepository,
                                       GenreRepository genreRepository, HallRepository hallRepository) {
        this.filmSessionRepository = filmSessionRepository;
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.hallRepository = hallRepository;
    }

    @Override
    public Optional<FilmSessionDto> create(int filmSessionId) {
        Optional<FilmSessionDto> filmSessionDto = Optional.empty();
        Optional<FilmSession> filmSession = filmSessionRepository.findById(filmSessionId);
        if (filmSession.isPresent()) {
            Film film = filmRepository.findById(filmSession.orElseThrow().getFilmId()).orElseThrow();
            Genre genre = genreRepository.findById(film.getGenreId()).orElseThrow();
            Hall hall = hallRepository.findById(filmSession.orElseThrow().getHallId()).orElseThrow();
            FilmDto filmDto = new FilmDto(film, genre);
            HallDto hallDto = new HallDto(hall);
            filmSessionDto = Optional.of(new FilmSessionDto(filmSession.get().getId(), filmDto, hallDto,
                    filmSession.get().getStartTime(), filmSession.get().getEndTime(), filmSession.get().getPrice()));
        }
        return filmSessionDto;
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        Collection<FilmSession> filmSessions = filmSessionRepository.findAll();
        Collection<FilmSessionDto> filmSessionsDto = new ArrayList<>();
        for (FilmSession filmSession : filmSessions) {
            filmSessionsDto.add(create(filmSession.getId()).orElseThrow());
        }
        return filmSessionsDto;
    }
}
