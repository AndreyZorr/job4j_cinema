package ru.job4j.cinema.dto;

import ru.job4j.cinema.model.Hall;

public class HallDto {

    private int id;

    private String name;

    private int[] rowCount;

    private int[] placeCount;

    private String description;

    public HallDto(Hall hall) {
        this.id = hall.getId();
        this.name = hall.getName();
        setRowCount(hall.getRowCount());
        setPlaceCount(hall.getPlaceCount());
        this.description = hall.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        int[] rows = new int[rowCount];
        for (int i = 0; i < rowCount; i++) {
            rows[i] = i + 1;
        }
        this.rowCount = rows;
    }

    public int[] getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        int[] places = new int[placeCount];
        for (int i = 0; i < placeCount; i++) {
            places[i] = i + 1;
        }
        this.placeCount = places;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
