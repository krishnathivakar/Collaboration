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

import com.niit.backend.DAO.ForumDAO;
import com.niit.backend.model.Blog;
import com.niit.backend.model.Forum;
import com.niit.backend.model.User;

@RestController
public class ForumController {
	
	@Autowired
	private ForumDAO forumDAO;
	
	public ForumDAO getForumDAO() {
		return forumDAO;
	}

	public void setForumDAO(ForumDAO forumDAO) {
		this.forumDAO = forumDAO;
	}

	@GetMapping("/forums")
	public ResponseEntity<List> getForums() {
		List listforum = forumDAO.list();
		return new ResponseEntity(listforum,HttpStatus.OK);
	}
	
	@GetMapping("/forums/{id}")
	public ResponseEntity getForum(@PathVariable("forumId") int forumId) {

		Forum forum = forumDAO.get(forumId);
		if (forum == null) {
			return new ResponseEntity("No Forum found for ID " + forumId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(forum, HttpStatus.OK);
	}
	@GetMapping("/acceptedforum")
	public ResponseEntity<List<Forum>> acceptedForumsList() {
		List<Forum> listforum = forumDAO.getAcceptedList();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	@GetMapping("/notAcceptedforum")
	public ResponseEntity<List<Forum>> notAcceptedForumList() {
		List<Forum> listforum = forumDAO.getNotAcceptedList();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	@PostMapping(value = "/forums")
	public ResponseEntity createForum(@RequestBody Forum forum, HttpSession session) {
		User user = (User) session.getAttribute("user");  
		forum.setUserId(user.getId());
		forum.setUserName(user.getName());
		forumDAO.saveOrUpdate(forum);

		return new ResponseEntity(forum, HttpStatus.OK);
	}

	@DeleteMapping("/forums/{id}")
	public ResponseEntity deleteForum(@PathVariable int forumId) {
		Forum forum = forumDAO.get(forumId);
 		if (forum==null) {
			return new ResponseEntity("No Forum found for ID " + forumId, HttpStatus.NOT_FOUND);
		}
 		forumDAO.delete(forumId);
		return new ResponseEntity(forumId, HttpStatus.OK);

	}

	@PutMapping("/forums/{id}")
	public ResponseEntity updateForum(@PathVariable int id, @RequestBody Forum forum) {

		forum = forumDAO.saveOrUpdate(forum);

		if (null == forum) {
			return new ResponseEntity("No Forum found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(forum, HttpStatus.OK);
	}
	@PutMapping("/acceptForum")
	public ResponseEntity acceptForum(@RequestBody Forum forum){
		forum.setStatus("A");
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity(forum, HttpStatus.OK);
	}

}