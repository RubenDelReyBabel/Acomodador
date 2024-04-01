package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seat {

    @Id
    @GeneratedValue
    private int id;
    private int row;
    private int column;
    private boolean booked;

    @ManyToOne
    private Cinema cinema;

    public Seat(int row, int column){
        this.row = row;
        this.column = column;
        this.booked = false;
    }
}
