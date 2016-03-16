package io.egen.service;

import io.egen.entity.Movie;
import io.egen.exception.MovieAlreadyExistsException;
import io.egen.exception.MovieNotFoundException;

import java.util.List;

public interface MovieService {
	
	List<Movie> findAllMovies();
	
	Movie createMovie(Movie movie) throws MovieAlreadyExistsException;
	
	Movie updateMovie(String id,Movie movie) throws MovieNotFoundException,MovieAlreadyExistsException;
	
	void updateRating(String id,int rating) throws MovieNotFoundException;
	
	Movie findMovieByName(String name) throws MovieNotFoundException;
	
	void deleteMovie(String id) throws MovieNotFoundException;
	
}
