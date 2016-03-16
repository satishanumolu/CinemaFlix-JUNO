package io.egen.service;

import io.egen.entity.Comment;

import java.util.List;

public interface CommentService {
	
	List<Comment> findAllComments();
	Comment addComment(Comment comment);
	
}
