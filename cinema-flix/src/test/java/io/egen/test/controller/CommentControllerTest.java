package io.egen.test.controller;

import java.util.UUID;

import io.egen.controller.CommentController;
import io.egen.entity.Comment;
import io.egen.entity.Movie;
import io.egen.entity.User;
import io.egen.service.CommentService;
import io.egen.test.TestConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class CommentControllerTest {
		
			//mock the dependencies of the controller
			@Mock
			private CommentService service;

			//controller that needs to be tested
			@InjectMocks
			private CommentController controller;
				
			private MockMvc mockMvc;

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
				
				//init mockMvc with standalone setup for this controller test (used for unit test)
				//look at WebApplicationContext setup as well (mostly used for integration tests)
				mockMvc = MockMvcBuilders
								.standaloneSetup(controller)
								.build();
			}
			
			@Test
			public void testFindAllComments() throws Exception {
				mockMvc.perform(MockMvcRequestBuilders.get("/comments"))
				.andExpect(MockMvcResultMatchers.status().isOk());

				Mockito.verify(service).findAllComments();
			}
			
			@Test
			public void testAddComment() throws Exception {
				//serialize to JSON string.
				//using Jackson's ObjectMapper to do that
				String requestBody = new ObjectMapper().writeValueAsString(comment);
				
				mockMvc.perform(MockMvcRequestBuilders.post("/comments").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(requestBody))
						.andExpect(MockMvcResultMatchers.status().isOk());
				
				Mockito.verify(service).addComment(Mockito.any(Comment.class));
			}
}
