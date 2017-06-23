package com.niit.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.niit.backend.DAO.CommentDAO;
import com.niit.backend.model.Comment;
import com.niit.backend.model.Forum;
import com.niit.backend.model.User;

@RestController
public class ForumCommentController {

	@Autowired
	private CommentDAO commentDAO;

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	@GetMapping("/comments")
	public ResponseEntity<List> getComments(@RequestBody Forum forum) {
		System.out.println("haaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaai");
		System.out.println(forum.getForumTitle());
		List listcomment = commentDAO.getForumComments(forum.getForumId());
		return new ResponseEntity(listcomment, HttpStatus.OK);
	}

	@GetMapping("/comments/{forumId}")
	public ResponseEntity getForumId(@PathVariable("forumId") int forumId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("forumId", forumId);
		System.out.println("haaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaai");
		System.out.println(forumId);
		List listcomment = commentDAO.getForumComments(forumId);
		if (listcomment == null) {
			return new ResponseEntity("No Comment found for ID " + forumId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(listcomment, HttpStatus.OK);
	}

	@GetMapping("/comment/{id}")
	public ResponseEntity getCommentId(@PathVariable("id") int id) {

		Comment comment = commentDAO.getComment(id);
		if (comment == null) {
			return new ResponseEntity("No Comment found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(comment, HttpStatus.OK);
	}

	@PostMapping(value = "/comments")
	public ResponseEntity createComment(@RequestBody Comment comment, HttpSession session) {
		System.out.println("haaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaai");
		
		User user = (User) session.getAttribute("user");
		
		System.out.println(user.getEmail());
		System.out.println(user.getMobile());
		comment.setUserMail(user.getEmail());
		comment.setUserName(user.getName());
		comment.setUserId(user.getId());
		
		int forumId = (int) session.getAttribute("forumId");
		System.out.println(forumId);
		comment.setForumId(forumId);
		commentDAO.saveOrUpdate(comment);

		return new ResponseEntity(comment, HttpStatus.OK);
	}

	@DeleteMapping("/comments/{id}")
	public ResponseEntity deleteComment(@PathVariable int id) {
		Comment comment = commentDAO.getComment(id);
		if (comment == null) {
			return new ResponseEntity("No Comment found for ID " + id, HttpStatus.NOT_FOUND);
		} else {
			commentDAO.delete(id);

			return new ResponseEntity(id, HttpStatus.OK);
		}
	}

	@PutMapping("/comments/{id}")
	public ResponseEntity updateComment(@PathVariable String id, @RequestBody Comment comment) {

		comment = commentDAO.saveOrUpdate(comment);

		if (null == comment) {
			return new ResponseEntity("No Comment found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(comment, HttpStatus.OK);
	}
}
