package com.niit.collaboration.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.DAO.UserDAO;
import com.niit.collaboration.model.User;

@RestController
public class UserController {
	
	
	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	/*
	@Autowired
	HttpSession session;
*/
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> listuser = userDAO.list();
		return new ResponseEntity<List<User>>(listuser,HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<List<User>> deleteUser(@PathVariable int id) {
		User user=userDAO.getById(id);
 		if (user==null) {
			return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
		}
 		userDAO.delete(id);
		return new ResponseEntity(id, HttpStatus.OK);
	}
	
	//http://localhost:8080/CollaborationResetService/user/niit
		@GetMapping("/users/{id}")
		public ResponseEntity<User> getUserByID(@PathVariable("id") int id)
		{


			 User user = userDAO.getById(id);
		  return	new ResponseEntity<User>(user , HttpStatus.OK);
		}
		
		@GetMapping("/users/{name}")
		public ResponseEntity<User> getUserByID(@PathVariable("name") String name)
		{


			 User user = userDAO.getByName(name);
		  return	new ResponseEntity<User>(user , HttpStatus.OK);
		}
		
		
		@GetMapping("/users/{email}")
		public ResponseEntity<User> getByEmail(@PathVariable("email") String email) {

			User user = userDAO.getByEmail(email);
			if (user == null) {
				return new ResponseEntity("No User found for email " + email, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
}
