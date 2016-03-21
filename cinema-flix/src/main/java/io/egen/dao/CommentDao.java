package io.egen.dao;

import java.util.List;

import io.egen.entity.Comment;
import io.egen.entity.Movie;

public interface CommentDao {
	
	List<Comment> findAllComments();
	Comment addComment(Comment comment);
	void deleteComment(Movie movie);
}
