package io.egen.dao;

import io.egen.entity.Movie;
import io.egen.exception.MovieNotFoundException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<Movie> findAllMovies() {
		TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
    	List<Movie> movies = query.getResultList();
		return movies;
	}
	
	@Override
	@Transactional
	public Movie findMovieByName(String name) {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findByName", Movie.class);
    	query.setParameter("pTitle", name);
    	List<Movie> movies = query.getResultList();
    	if(movies != null && movies.size() == 1) {
    		return movies.get(0);
    	}
    	else {
			return null;
		}
	}
	
	@Override
	public Movie findMovieById(String id) {
		return em.find(Movie.class, id);
	}
	
	@Override
	@Transactional
	public Movie createMovie(Movie movie) {
		
		em.persist(movie);
		return movie;
	}

	@Override
	@Transactional
	public Movie updateMovie(Movie movie) {
		
		return em.merge(movie);
	}

	@Override
	@Transactional
	public void deleteMovie(String movieID) throws MovieNotFoundException{
		
		Movie existing = findMovieById(movieID);
		
		if(existing != null)
		{
			commentDao.deleteComment(existing);
			em.remove(existing);
		}
		else {
			throw new MovieNotFoundException();
		}
	}

	@Override
	@Transactional
	public void updateRating(Movie movie, int rating) {
		
		rating = (movie.getRating()+rating)/2;
		
		Query query = em.createQuery("UPDATE Movie m set m.rating =:pRating WHERE m.movieID = :pMovieID");
    	query.setParameter("pRating", rating);
		query.setParameter("pMovieID", movie.getMovieID());
		query.executeUpdate();
	}
	
	
}
