package io.egen.test.service;

import io.egen.dao.MovieDao;
import io.egen.entity.IMDB;
import io.egen.entity.Movie;
import io.egen.exception.MovieAlreadyExistsException;
import io.egen.exception.MovieNotFoundException;
import io.egen.service.MovieService;
import io.egen.service.MovieServiceImpl;
import io.egen.test.TestConfig;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class MovieServiceTest {
	
	@Mock
	private MovieDao dao;
	
	@InjectMocks
	private MovieService service = new MovieServiceImpl();
	
	private Movie movie;
	
	private IMDB imdb;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		imdb = new IMDB();
		imdb.setImdbID("test");
		imdb.setId(UUID.randomUUID().toString());
		movie = new Movie();
		movie.setTitle("test");
		movie.setImdb(imdb);
		movie.setMovieID(UUID.randomUUID().toString());
	}
	
	@Test
	public void testFindAllMovies () {
		service.findAllMovies();
		Mockito.verify(dao).findAllMovies();
	}
	
	@Test
	public void testFindMovieByName () throws MovieNotFoundException {
		Mockito.when(dao.findMovieByName(movie.getTitle())).thenReturn(movie);
		
		Movie actual = service.findMovieByName(movie.getTitle());
		Assert.assertEquals(movie, actual);
	}

	@Test(expected=MovieNotFoundException.class)
	public void testFindMovieByNameException () throws MovieNotFoundException {
		Mockito.when(dao.findMovieByName(movie.getTitle())).thenReturn(null);
		
		service.findMovieByName(movie.getTitle());
	}
	
	
	@Test(expected=MovieAlreadyExistsException.class)
	public void testCreateMovieException () throws MovieAlreadyExistsException {
		Mockito.when(dao.findMovieByName(movie.getTitle())).thenReturn(movie);
		service.createMovie(movie);
	}
	
	
	@Test
	public void testCreateMovie() throws MovieAlreadyExistsException {
		Mockito.when(dao.findMovieByName(movie.getTitle())).thenReturn(null);
		service.createMovie(movie);
		Mockito.verify(dao).createMovie(movie);
	}
	
	@Test(expected=MovieNotFoundException.class)
	public void testUpdateMovieException() throws MovieNotFoundException, MovieAlreadyExistsException {
		Mockito.when(dao.findMovieById(movie.getMovieID())).thenReturn(null);
		service.updateMovie(movie.getMovieID(),movie);
	}
	
	
	@Test
	public void testDeleteMovie () throws MovieNotFoundException{
		service.deleteMovie(movie.getMovieID());
		Mockito.verify(dao).deleteMovie(movie.getMovieID());
	}
	
}
