package com.softserve.academy.model.movieSession;

import com.softserve.academy.model.cinema.PhysicalSeat;

import java.util.Objects;

public class MovieSessionSeat {
    private PhysicalSeat physicalSeat;
    private boolean available;

    public MovieSessionSeat(PhysicalSeat physicalSeat, boolean available) {
        this.physicalSeat = physicalSeat;
        this.available = available;
    }

    public MovieSessionSeat() {
    }

    public PhysicalSeat getPhysicalSeat() {
        return physicalSeat;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


}
