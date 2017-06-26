package com.niit.backend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.DAO.ChatDAO;
import com.niit.backend.model.Chat;

public class ChatTest {
	public static void main (String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.backend");
		context.refresh();
		Chat chat = (Chat) context.getBean("chat");
		ChatDAO chatDAO = (ChatDAO) context.getBean("chatDAO");
		chat.setDestinationId(45);
		chat.setFrom("hi");
		chat.setMessage("what er uo dion");
		chat.setSourceId(96);
		chat.setTo("bye");
		chatDAO.saveOrUpdate(chat);
	}
}
