/*package com.niit.backend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.DAO.ChatDAO;
import com.niit.backend.model.chat;

public class ChatTest {
	public static void main (String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.backend");
		context.refresh();
		chat chat = (chat) context.getBean("chat");
		ChatDAO chatDAO = (ChatDAO) context.getBean("chatDAO");
		
		chat.setMessage("hey stupid");
		chat.setReceiver_id(56);
		chat.setReceiver_name("karthik");
		chat.setSender_name("krishna");
		chat.setUser_id(15);
	
		chatDAO.saveOrUpdate(chat);
	}
}
*/