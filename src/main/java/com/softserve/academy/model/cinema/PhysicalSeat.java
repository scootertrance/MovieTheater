package com.softserve.academy.model.cinema;

import com.softserve.academy.model.cinema.util.TypeOfSeat;

public class PhysicalSeat {
    private TypeOfSeat type;

    public PhysicalSeat(TypeOfSeat type) {
        this.type = type;
    }

    public PhysicalSeat() {
    }

    public TypeOfSeat getType() {
        return type;
    }

    public void setType(TypeOfSeat type) {
        this.type = type;
    }
}
