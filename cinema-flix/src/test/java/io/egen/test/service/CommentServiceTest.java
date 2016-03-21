package io.egen.test.service;

import java.util.UUID;

import io.egen.dao.CommentDao;
import io.egen.entity.Comment;
import io.egen.entity.Movie;
import io.egen.entity.User;
import io.egen.service.CommentService;
import io.egen.service.CommentServiceImpl;
import io.egen.test.TestConfig;

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
public class CommentServiceTest {
	
	@Mock
	private CommentDao dao;
	
	@InjectMocks
	private CommentService service = new CommentServiceImpl();
	
	private Movie movie;
	
	private User user;
	
	private Comment comment;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setEmail("test@test.com");
		user.setFirstName("test");
		user.setLastName("user");
		user.setPassword("test");
		user.setId(UUID.randomUUID().toString());
		
		movie = new Movie();
		movie.setTitle("test");
		movie.setMovieID(UUID.randomUUID().toString());
		
		comment = new Comment();
		comment.setCommentID(UUID.randomUUID().toString());
		comment.setComment("test");
		comment.setMovie(movie);
		comment.setUser(user);
	}
	
	@Test
	public void testFindAllComments () {
		service.findAllComments();
		Mockito.verify(dao).findAllComments();
	}
	
	@Test
	public void testAddComment () {
		service.addComment(comment);
		Mockito.verify(dao).addComment(comment);
	}
	
}
