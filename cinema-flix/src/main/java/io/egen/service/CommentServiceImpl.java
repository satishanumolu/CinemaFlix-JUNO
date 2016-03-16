package io.egen.service;

import io.egen.dao.CommentDao;
import io.egen.entity.Comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<Comment> findAllComments() {
		return commentDao.findAllComments();
	}

	@Override
	public Comment addComment(Comment comment) {
		return commentDao.addComment(comment);
	}
	
	
}
