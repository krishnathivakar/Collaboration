package com.niit.backend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.DAO.BlogDAO;
import com.niit.backend.DAO.ChatDAO;
import com.niit.backend.DAO.EventDAO;
import com.niit.backend.DAO.ForumDAO;
import com.niit.backend.DAO.FriendDAO;
import com.niit.backend.DAO.JobDAO;
import com.niit.backend.DAOImpl.UserDAO;
import com.niit.backend.model.Blog;
import com.niit.backend.model.Chat;
import com.niit.backend.model.Event;
import com.niit.backend.model.Forum;
import com.niit.backend.model.Friend;
import com.niit.backend.model.Job;
import com.niit.backend.model.User;

public class UserTest {

	public static void main (String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.backend");
		context.refresh();
		
		
		Blog blog = (Blog) context.getBean("blog");
		Chat chat = (Chat) context.getBean("chat");
		Event event = (Event) context.getBean("event");
		Forum forum = (Forum) context.getBean("forum");
		Friend friend = (Friend) context.getBean("friend");
		Job job = (Job) context.getBean("job");
		User user = (User) context.getBean("user");
		
		BlogDAO blogDAO = (BlogDAO) context.getBean("blogDAO");
		ChatDAO chatDAO = (ChatDAO) context.getBean("chatDAO");
		EventDAO eventDAO = (EventDAO) context.getBean("eventDAO");
		ForumDAO forumDAO = (ForumDAO) context.getBean("forumDAO");
		FriendDAO friendDAO = (FriendDAO) context.getBean("friendDAO");
		JobDAO jobDAO = (JobDAO) context.getBean("jobDAO");
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
	
		
		blog.setTitle("krish");
		blog.setDescription("Hyper");
		blog.setStatus("Active");
		
		chat.setUserName("thiva");
		chat.setFriendId(101);
		chat.setFriendname("siva");
		chat.setMessage("hi bro");
		chat.setUserId(10);
		chat.setUserName("krishna");
		
		event.setDescription("holidayCelebration");
		event.setName("trip Advisor");
		event.setVenue("cbe");
		
		forum.setForumTitle("host");
		forum.setForumContent("topic");
		forum.setUserId(101);
		forum.setUserName("krish");
		

		friend.setFriendName("Gopi");
		friend.setIsOnline("true");
		friend.setStatus("friends");
		friend.setUserId(103);
		friend.setUserName("thiva");
		
		job.setTitle("Accenture Interview");
		job.setCompanyName("CTS");
		job.setQualification("B.e/B.tech");
		job.setStatus("valid");
		
		user.setAddress("theni");
		//user.setId("1");
			user.setEmail("krishnaa@email.com");
			user.setMobile("9876543211");
			user.setName("krishnaa");
			user.setPassword("krishnaa");
			user.setRole("ROLE_USE");
			user.setZipcode("625524");
			
		
		blogDAO.save(blog);
		chatDAO.save(chat);
		eventDAO.save(event);
		forumDAO.save(forum);
		friendDAO.save(friend);
		jobDAO.save(job);
		userDAO.save(user);

		
	}

}
