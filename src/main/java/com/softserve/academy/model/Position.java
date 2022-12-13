package com.softserve.academy.model;

public class Position {
    private int row;
    private int placeInRow;

    public Position(int row, int placeInRow) {
        this.row = row;
        this.placeInRow = placeInRow;
    }

    public int getRow() {
        return row;
    }

    public int getPlaceInRow() {
        return placeInRow;
    }
}
