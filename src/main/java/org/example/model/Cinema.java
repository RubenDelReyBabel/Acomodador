package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cinema {

    @Id
    @GeneratedValue
    private int id;
    private int rows;
    private int seatsPerRow;

    @OneToMany(mappedBy = "cinema")
    private List<Seat> seats = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seatcomputer_id", referencedColumnName = "id")
    private SeatComputer seatComputer;

    public Cinema(int rows, int columns, SeatComputer seatComputer) {
        this.rows = rows;
        this.seatsPerRow = columns;
        this.seatComputer = seatComputer;
    }

    public boolean bookSeat(int row, int column) {
        Seat seat = getSeat(row, column);
        if (seat == null) {
            return false;
        }
        seat.setBooked(true);
        return true;
    }

    public Seat getSeat(int row, int column){
        return seats.stream().filter(seat -> seat.getRow() == row && seat.getColumn() == column).findFirst().orElse(null);
    }

    public List<Seat> getBestSeats(int numberOfSeats){
        return seatComputer.getBestSeats(numberOfSeats);
    }
}
