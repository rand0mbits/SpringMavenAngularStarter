package com.company.myproject.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.myproject.messaging.Message;
import com.company.myproject.messaging.MessageCollection;

@Controller
@RequestMapping(value="/utilities")
public class UtilitiesController {
	@Autowired private ApplicationContext applicationContext;
	
	private static final Logger logger = Logger.getLogger(UtilitiesController.class);
	@Autowired private MessageCollection messages;
	
	@RequestMapping(value = "messages", method=RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public String addMessages(@RequestBody List<Message> messages, @RequestParam("override") Boolean override) {
		if (override == null || !override) {
			for (Message message : messages) {
				this.messages.add(message);
			}
		}
		else {
			this.messages.addInfo("Override processed!");
		}
		
		return null;
	}
}
