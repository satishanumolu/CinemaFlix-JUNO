package io.egen.dao;

import io.egen.entity.User;
import io.egen.exception.UserNotFoundException;

import java.util.List;

public interface UserDao {

	public List<User> findAllUsers ();
	public User findUserById(String id);
	public User findUser(String email,String password);
	public User findUserByEmail(String email);
	public User createUser(User user);
	public User updateUser(User user);
	public void deleteUser(String id) throws UserNotFoundException;
}
