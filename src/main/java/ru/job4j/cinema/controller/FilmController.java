package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.FilmDtoService;

@ThreadSafe
@Controller
@RequestMapping("/films")
public class FilmController {

    private final FilmDtoService filmDtoService;

    public FilmController(FilmDtoService filmDtoService) {
        this.filmDtoService = filmDtoService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("films", filmDtoService.findAll());
        return "films/list";
    }
}
