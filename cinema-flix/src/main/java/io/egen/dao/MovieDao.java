package io.egen.dao;

import io.egen.entity.Movie;
import io.egen.exception.MovieNotFoundException;

import java.util.List;

public interface MovieDao {
	
	Movie createMovie(Movie movie);
	List<Movie> findAllMovies();
	Movie findMovieByName(String name);
	Movie findMovieById(String id);
	Movie updateMovie(Movie movie);
	void updateRating(Movie movie,int rating);
	void deleteMovie(String movieID) throws MovieNotFoundException;

}
