package com.example.movie_management.service;

import com.example.movie_management.model.Movie;
import com.example.movie_management.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class MovieServiceIntegrationTest {

    @Autowired
    private MovieService service;

    @Autowired
    private MovieRepository repository;

    @Test
    void testSaveAndFindAll() {
        repository.deleteAll();

        Movie movie = new Movie(null, "Interstellar", "Sci-Fi", 2014);
        service.save(movie);

        List<Movie> movies = service.findAll();
        assertEquals(1, movies.size());
        assertEquals("Interstellar", movies.get(0).getTitle());
    }

    @Test
    void testDelete() {
        Movie movie = new Movie(null, "Matrix", "Sci-Fi", 1999);
        Movie saved = service.save(movie);

        service.delete(saved.getId());

        assertTrue(repository.findById(saved.getId()).isEmpty());
    }
}
