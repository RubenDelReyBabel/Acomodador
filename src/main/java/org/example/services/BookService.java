package org.example.services;

import org.example.model.Seat;

import java.util.List;

public interface BookService {

    boolean bookSeat(int row, int column);
    List<Seat> getBestSeats(int numberOfSeats);
    void init();
    String getSeats();
}
