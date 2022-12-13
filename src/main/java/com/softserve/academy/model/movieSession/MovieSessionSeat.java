package com.softserve.academy.model.movieSession;

import com.softserve.academy.model.cinema.PhysicalSeat;

public class MovieSessionSeat {
    private PhysicalSeat physicalSeat;
    private boolean available;

    public MovieSessionSeat(PhysicalSeat physicalSeat, boolean available) {
        this.physicalSeat = physicalSeat;
        this.available = available;
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
