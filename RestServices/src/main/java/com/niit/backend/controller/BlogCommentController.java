package com.niit.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.backend.DAO.BlogCommentDAO;
import com.niit.backend.model.BlogComment;
import com.niit.backend.model.Comment;
import com.niit.backend.model.User;

@RestController
public class BlogCommentController {
	
	@Autowired
	private BlogCommentDAO blogCommentDAO;

	public BlogCommentDAO getBlogCommentDAO() {
		return blogCommentDAO;
	}

	public void setBlogCommentDAO(BlogCommentDAO blogCommentDAO) {
		this.blogCommentDAO = blogCommentDAO;
	}
	
	@GetMapping("/blogcomment")
	public List<BlogComment> getComments() {
		return blogCommentDAO.list();
	}
	
	@GetMapping("/blogcomment/{blogId}")
	public ResponseEntity getBlogId(@PathVariable("blogId") int blogId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("blogId", blogId);
		System.out.println("haaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaai");
		System.out.println(blogId);
		List listcomment = blogCommentDAO.getBlogComments(blogId);
		if (listcomment == null) {
			return new ResponseEntity("No Comment found for ID " + blogId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(listcomment, HttpStatus.OK);
	}
	
	@GetMapping("/blogcomment/{id}")
	public ResponseEntity getBlogCommentId(@PathVariable("id") int id) {

		BlogComment blogComment = blogCommentDAO.getBlogComment(id);
		if (blogComment == null) {
			return new ResponseEntity("No BlogComment found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(blogComment, HttpStatus.OK);
	}
	
	@PostMapping(value = "/blogcomment")
	public ResponseEntity createBlogComment(@RequestBody BlogComment blogComment, HttpSession session) {
		System.out.println("haaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaai");
		
		User user = (User) session.getAttribute("user");
		
		System.out.println(user.getEmail());
		System.out.println(user.getMobile());
	
		
		int blogId = (int) session.getAttribute("forumId");
		System.out.println(blogId);
	

		return new ResponseEntity(blogComment, HttpStatus.OK);
	}
}
