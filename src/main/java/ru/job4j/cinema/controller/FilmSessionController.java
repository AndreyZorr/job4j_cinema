package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.FilmSessionDtoService;

@ThreadSafe
@Controller
@RequestMapping("/film-sessions")
public class FilmSessionController {

    private final FilmSessionDtoService filmSessionDtoService;

    public FilmSessionController(FilmSessionDtoService filmSessionDtoService) {
        this.filmSessionDtoService = filmSessionDtoService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("filmSessions", filmSessionDtoService.findAll());
        return "film_sessions/list";
    }
}
