package ru.job4j.cinema.dto;

import java.time.LocalDateTime;

public class FilmSessionDto {

    private int id;

    private FilmDto filmDto;

    private HallDto hallDto;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int price;

    public FilmSessionDto(int id, FilmDto filmDto, HallDto hallDto,
                          LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.id = id;
        this.filmDto = filmDto;
        this.hallDto = hallDto;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FilmDto getFilmDto() {
        return filmDto;
    }

    public void setFilmDto(FilmDto filmDto) {
        this.filmDto = filmDto;
    }

    public HallDto getHallDto() {
        return hallDto;
    }

    public void setHallDto(HallDto hallDto) {
        this.hallDto = hallDto;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
