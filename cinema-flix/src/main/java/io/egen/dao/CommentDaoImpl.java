package io.egen.dao;

import io.egen.entity.Comment;
import io.egen.entity.Movie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommentDaoImpl implements CommentDao{
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Comment> findAllComments() {
		TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
		List<Comment> comments = query.getResultList();
		
		return comments;
	}

	@Override
	@Transactional
	public Comment addComment(Comment comment) {
		em.persist(comment);
		return null;
	}
	
	@Override
	@Transactional
	public void deleteComment(Movie movie) {
		Query query = em.createQuery("DELETE FROM Comment c WHERE c.movie = :pMovie");
    	query.setParameter("pMovie", movie).executeUpdate();
	}
	
	
}
