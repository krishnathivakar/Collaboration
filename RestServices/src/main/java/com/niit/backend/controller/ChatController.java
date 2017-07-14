package com.niit.backend.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.backend.model.Message;
import com.niit.backend.model.OutputMessage;

@Controller
public class ChatController {
	
	 @MessageMapping("/chat_forum")   ///sendMessage
	  @SendTo("/topic/message")        //receiveMessage
	  public OutputMessage sendMessage(Message message) {
		
		
	  
		//  logger.debug(" Message ID : ",message.getId());
		 return new OutputMessage(message, new Date());	  }
	  

}
