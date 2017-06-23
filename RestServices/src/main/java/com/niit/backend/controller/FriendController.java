package com.niit.backend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.backend.DAO.FriendDAO;
import com.niit.backend.model.Blog;
import com.niit.backend.model.Friend;
import com.niit.backend.model.Job;
import com.niit.backend.model.User;

@RestController
public class FriendController {

	
	@Autowired(required = true)
	private FriendDAO friendDAO;
	
	@Autowired
	private Friend friend;

	public FriendDAO getFriendDAO() {
		return friendDAO;
	}

	public void setFriendDAO(FriendDAO friendDAO) {
		this.friendDAO = friendDAO;
	}
	
	@GetMapping("/friends")
	public List<Friend> getCustomers() {
		return friendDAO.list();
	}
	
	@GetMapping("/friend/{userId}")
	public List<Friend> getByUser(@PathVariable("userId") int userId) {
		return friendDAO.list(userId);
	}
	
	@GetMapping("/friends/{name}")  
	public ResponseEntity<Friend> geByID(@PathVariable("name") String name) {

		Friend friend = friendDAO.getByFriendName(name);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	@PostMapping("/friends")
	public ResponseEntity createFriend(@RequestBody User friendUser, HttpSession session) {
		User user = (User) session.getAttribute("user");   
		friend.setUserId(user.getId());
		friend.setUserName(user.getName());
		friend.setStatus("P");
		friend.setFriendId(friendUser.getId());
		friend.setFriendName(friendUser.getName());
		friend.setIsOnline("TRUE");
	
		friendDAO.save(friend);

		return new ResponseEntity(friend, HttpStatus.OK);
	}
	
	@PutMapping("/friends/{id}")
	public ResponseEntity acceptFriend(@PathVariable String id,@RequestBody Friend friend){
		
		friendDAO.saveOrUpdate(friend);
		return new ResponseEntity(friend, HttpStatus.OK);
	}
	
	@DeleteMapping("/friends/{id}")
	public ResponseEntity deleteFriend(@PathVariable int id) {
		Friend friend=friendDAO.getByFriendId(id);
 		if (friend==null) {
			return new ResponseEntity("No friend found for ID " + id, HttpStatus.NOT_FOUND);
		}
 		friendDAO.delete(id);
		return new ResponseEntity(id, HttpStatus.OK);

	}
}
