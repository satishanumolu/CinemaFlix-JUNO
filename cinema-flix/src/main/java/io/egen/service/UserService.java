package io.egen.service;

import io.egen.entity.User;
import io.egen.exception.UserAlreadyExistsException;
import io.egen.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

	List<User> findAllUsers();

	User findUserById(String id) throws UserNotFoundException;

	User findUser(String email,String password) throws UserNotFoundException;
	
	User findUserByEmail(String email) throws UserNotFoundException;

	User createUser(User user) throws UserAlreadyExistsException;

	User updateUser(String id, User user) throws UserNotFoundException;

	void deleteUser(String id) throws UserNotFoundException;

}
