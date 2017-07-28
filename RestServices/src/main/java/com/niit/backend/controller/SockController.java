package com.niit.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.niit.backend.model.Chat;

@Controller
public class SockController {
	

	private final SimpMessagingTemplate messagingTemplate;

	private List<String> users = new ArrayList<String>();


	@Autowired

	public SockController(SimpMessagingTemplate messagingTemplate) {

		this.messagingTemplate = messagingTemplate;

	}

	@SubscribeMapping("/join/{name}")

	public List<String> join(@DestinationVariable("name") String name) {
        

		 System.out.println("username in sockcontroller" + name);
		 
		 if(!users.contains(name)) {
				users.add(name);
			}


		System.out.println("====JOIN==== " + name);

		// notify all subscribers of new user

		messagingTemplate.convertAndSend("/topic/join", name);

		return users;

	}

	@MessageMapping(value = "/chat")

	public void chatReveived(Chat chat) {


		if ("all".equals(chat.getTo())) {

			System.out.println("IN CHAT REVEIVED " + chat.getMessage() + " " + chat.getFrom() + " to " + chat.getTo());

			messagingTemplate.convertAndSend("/queue/chats", chat);

		}

		else {

			System.out.println("CHAT TO " + chat.getTo() + " From " + chat.getFrom() + " Message " + chat.getMessage());

			messagingTemplate.convertAndSend("/queue/chats/" + chat.getTo(), chat);

			messagingTemplate.convertAndSend("/queue/chats/" + chat.getFrom(), chat);

		}

	}

}