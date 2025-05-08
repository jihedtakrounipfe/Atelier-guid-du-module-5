package com.example.movie_management.service;

import com.example.movie_management.model.Movie;
import com.example.movie_management.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "movies")
    public List<Movie> findAll() {
        logger.info("Cache miss - Retrieving movies from the database");
        List<Movie> movies = repository.findAll();
        logger.info("Fetched {} movies from the database", movies.size());
        return movies;
    }

    @CacheEvict(value = "movies", allEntries = true)
    public Movie save(Movie movie) {
        logger.info("Saving movie: {}", movie.getTitle());
        Movie savedMovie = repository.save(movie);
        logger.info("Movie saved: {}", savedMovie.getTitle());
        return savedMovie;
    }

    @CacheEvict(value = "movies", key = "#id")
    public void delete(Long id) {
        logger.info("Deleting movie with ID: {}", id);
        repository.deleteById(id);
        logger.info("Movie with ID: {} deleted", id);
    }
}
