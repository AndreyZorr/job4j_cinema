package ru.job4j.cinema.service.hall;

import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.hall.HallRepository;

import java.util.Optional;

public class SimpleHallService implements HallService {

    private final HallRepository hallRepository;

    public SimpleHallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Optional<Hall> findById(int id) {
        return hallRepository.findById(id);
    }
}
