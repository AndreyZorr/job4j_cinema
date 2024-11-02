package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.dto.HallDto;
import ru.job4j.cinema.model.*;
import ru.job4j.cinema.service.filmsessiondto.FilmSessionDtoService;
import ru.job4j.cinema.service.ticket.TicketService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicketControllerTest {

    private TicketService ticketService;

    private FilmSessionDtoService filmSessionDtoService;

    private TicketController ticketController;

    @BeforeEach
    public void initServices() {
        filmSessionDtoService = mock(FilmSessionDtoService.class);
        ticketService = mock(TicketService.class);
        ticketController = new TicketController(filmSessionDtoService, ticketService);
    }

    @Test
    public void whenRequestBuyTicketPageThenGetPageWithFilmSessionDto() {
        var film = new Film.Builder()
                .buildName("FilmName1")
                .buildDescription("FilmDescription1")
                .buildYear(1999)
                .buildGenreId(1)
                .buildMinimalAge(7)
                .buildDurationInMinutes(90)
                .buildFileId(1)
                .build();
        var genre = new Genre(1, "FilmGenre1");
        var hall = new Hall(1, "HallName1", 5, 10, "HallDescription1");
        var filmSession = new FilmSessionDto(1, new FilmDto(film, genre), new HallDto(hall),
                LocalDateTime.now(), LocalDateTime.now(), 300);
        when(filmSessionDtoService.create(filmSession.getId())).thenReturn(Optional.of(filmSession));

        var model = new ConcurrentModel();
        var request = new MockHttpServletRequest();
        var response = new MockHttpServletResponse();
        var session = new MockHttpSession();
        var view = ticketController.buy(model, filmSession.getId(), request, session, response);
        var actualFilmSession = model.getAttribute("filmSession");

        assertThat(view).isEqualTo("tickets/buy");
        assertThat(actualFilmSession).isEqualTo(filmSession);
    }

    @Test
    public void whenRequestBuyPageAfterCreateTicketThenGetSuccessPageWithFilmSessionDtoAndTicketData() {
        var film = new Film.Builder()
                .buildName("FilmName1")
                .buildDescription("FilmDescription1")
                .buildYear(1999)
                .buildGenreId(1)
                .buildMinimalAge(7)
                .buildDurationInMinutes(90)
                .buildFileId(1)
                .build();
        var genre = new Genre(1, "FilmGenre1");
        var hall = new Hall(1, "HallName1", 5, 10, "HallDescription1");
        var filmSession = new FilmSessionDto(1, new FilmDto(film, genre), new HallDto(hall),
                LocalDateTime.now(), LocalDateTime.now(), 300);
        var ticket = new Ticket(1, 3, 5, 7, 9);
        var user = new User(1, "Name", "email@email.com", "pass");
        when(filmSessionDtoService.create(filmSession.getId())).thenReturn(Optional.of(filmSession));

        var model = new ConcurrentModel();
        var request = new MockHttpServletRequest();
        request.setRequestURI("/film-session/" + filmSession.getId() + "/ticket/buy/success");
        var response = new MockHttpServletResponse();
        var session = new MockHttpSession();
        session.setAttribute("user", user);
        session.setAttribute("ticket", ticket);
        var view = ticketController.buy(model, filmSession.getId(), request, session, response);
        var actualFilmSession = model.getAttribute("filmSession");
        var actualTicket = model.getAttribute("ticket");

        assertThat(view).isEqualTo("tickets/success");
        assertThat(actualFilmSession).isEqualTo(filmSession);
        assertThat(actualTicket).isEqualTo(ticket);
    }
}