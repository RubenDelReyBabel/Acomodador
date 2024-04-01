package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
@Entity
public class SeatComputer {

    @Id
    @GeneratedValue
    private int id;
    private int rows;
    private int seatsPerRow;

    @OneToOne
    private Cinema cinema;

    public List<Seat> getBestSeats(int numberOfSeats){
        this.rows = cinema.getRows();
        this.seatsPerRow = cinema.getSeatsPerRow();

        List<Seat> bestSeats = new ArrayList<>();
        int bestScore = 0;
        for (int i = 1; i < rows; i++){
            for (int j = 1; j < seatsPerRow; j++){
                if (j + numberOfSeats >= seatsPerRow){
                    break;
                }
                List<Seat> seats = new ArrayList<>();
                int score = 0;
                for (int k = 0; k < numberOfSeats; k++){
                    Seat seat = cinema.getSeat(i, j + k);
                    if (seat.isBooked()){
                        break;
                    }
                    seats.add(seat);
                    score += computeSeat(i, seat.getColumn());
                }
                if (seats.size() == numberOfSeats && score > bestScore){
                    bestSeats = seats;
                    bestScore = score;
                }
            }
        }
        return bestSeats;
    }

    private int computeSeat(int row, int column){
        return computeRow(row) + computeColumn(column);
    }

    private int computeRow(int row){
        return row;
    }

    private int computeColumn(int column){
        if (seatsPerRow % 2 != 0){
            column++;
        }
        return seatsPerRow / 2 - column / 2;
    }
}
