package com.example.movie_management.service;

import com.example.movie_management.model.Movie;
import com.example.movie_management.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
