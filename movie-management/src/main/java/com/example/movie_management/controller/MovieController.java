package com.example.movie_management.controller;

import com.example.movie_management.model.Movie;
import com.example.movie_management.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return service.findAll();
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return service.save(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        service.delete(id);
    }
}
