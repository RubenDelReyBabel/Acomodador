package org.example.controllers;

import org.example.model.Seat;
import org.example.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/cinema/book")
    public boolean book(Integer row, Integer column) {
        return bookService.bookSeat(row, column);
    }

    @RequestMapping("/cinema/seats/best/{numberOfSeats}")
    public List<Seat> getBestSeats(@PathVariable int numberOfSeats) {
        return bookService.getBestSeats(numberOfSeats);
    }

    @PostMapping("/cinema/init")
    public void init() {
        bookService.init();
    }

    @GetMapping("/cinema/seats")
    public String getSeats() {
        return bookService.getSeats();
    }
}
