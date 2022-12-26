package com.softserve.academy.model.movieSession;

import com.softserve.academy.model.Position;
import com.softserve.academy.model.cinema.PhysicalSeat;

public class MovieSessionSeat {
    private PhysicalSeat physicalSeat;
    private boolean available;
    private Position position;

    public MovieSessionSeat(PhysicalSeat physicalSeat, boolean available, Position position) {
        this.physicalSeat = physicalSeat;
        this.available = available;
        this.position = position;
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

    public Position getPosition() {
        return position;
    }
}
