package com.softserve.academy.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && placeInRow == position.placeInRow;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, placeInRow);
    }

}
