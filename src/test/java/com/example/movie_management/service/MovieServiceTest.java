package com.example.movie_management.service;

import com.example.movie_management.model.Movie;
import com.example.movie_management.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    private MovieRepository repository;
    private MovieService service;

    @BeforeEach
    void setUp() {
        repository = mock(MovieRepository.class);
        service = new MovieService(repository);
    }

    @Test
    void testFindAll() {
        List<Movie> mockMovies = Arrays.asList(new Movie(1L, "Titanic", "Drama", 1997));
        when(repository.findAll()).thenReturn(mockMovies);

        List<Movie> result = service.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave() {
        Movie movie = new Movie(2L, "Inception", "Sci-Fi", 2010);
        when(repository.save(movie)).thenReturn(movie);

        Movie saved = service.save(movie);
        assertEquals("Inception", saved.getTitle());
        verify(repository, times(1)).save(movie);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        service.delete(id);
        verify(repository, times(1)).deleteById(id);
    }
}
