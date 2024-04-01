package org.example.services;

import org.example.model.Cinema;
import org.example.model.Seat;
import org.example.model.SeatComputer;
import org.example.repositories.CinemaRepository;
import org.example.repositories.SeatComputerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final CinemaRepository cinemaRepository;
    private final SeatComputerRepository seatComputerRepository;
    private Cinema cinema;

    public BookServiceImpl(CinemaRepository cinemaRepository, SeatComputerRepository seatComputerRepository){
        this.cinemaRepository = cinemaRepository;
        this.seatComputerRepository = seatComputerRepository;
    }

    public boolean bookSeat(int row, int column){
        return cinema.bookSeat(row, column);
    }

    public List<Seat> getBestSeats(int numberOfSeats){
        return cinema.getBestSeats(numberOfSeats);
    }
    public void init(){
        SeatComputer seatComputer = new SeatComputer();
        this.cinema = new Cinema(9 ,10, seatComputer);
        cinemaRepository.save(cinema);
        seatComputerRepository.save(seatComputer);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                cinema.getSeats().add(createSeat(i, j));
            }
        }
        seatComputer.setCinema(cinema);

    }

    private Seat createSeat(int row, int column){
        int columns = cinema.getSeatsPerRow();
        int c;
        if (column >= columns / 2) {
            c = Math.abs(column - columns / 2 + 1) * 2;
        } else {
            c = Math.abs(columns / 2 - column) * 2 - 1;
        }
        return new Seat(row, c);
    }

    public String getSeats(){
        StringBuilder seats = new StringBuilder();

        for (int i = 0; i < cinema.getRows(); i++) {
            for (int j = 0; j < cinema.getSeatsPerRow(); j++) {
                seats.append(cinema.getSeats().get(i * cinema.getSeatsPerRow() + j).isBooked() ? "X" : "O");
            }
            seats.append("\n");
        }
        return seats.toString();
    }
}
