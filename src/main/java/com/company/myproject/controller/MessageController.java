package com.company.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.myproject.messaging.Message;
import com.company.myproject.messaging.MessageCollection;

@Controller
@RequestMapping(value="/messages")
public class MessageController {
	@Autowired private MessageCollection messages;
	
	@RequestMapping(value = "get-all")
	public @ResponseBody List<Message> getAllMessages() {
		return messages.getAll();
	}
}
