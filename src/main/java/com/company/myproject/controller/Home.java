package com.company.myproject.controller;

import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.myproject.service.MailService;

@Controller
@RequestMapping(value = "/")
public class Home {
	@Autowired private MailService mailService;
	private static final Logger LOGGER = Logger.getLogger(Home.class);
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String index(Model model, HttpSession session, Principal principal,
                       HttpServletRequest request){
		LOGGER.info("Loading homepage");
        return "index";
    }
	
	@RequestMapping(value = { "/tmpl/{name}" }, method = RequestMethod.GET)
    public String home(Model model, HttpSession session, Principal principal,
                       HttpServletRequest request, @PathVariable("name") String name){
		String out = name.replace("|", "/");
		LOGGER.info("Loading template: " + out);
        
        return out;
    }
	
	@RequestMapping(value = { "/sendmail" }, method = RequestMethod.GET)
    public @ResponseBody String sendMail(){
		mailService.sendMail("vschuman@company.com", "test", "testing");
        
        return "done";
    }
	
	/**
	 * Used for pinging the server, no advanced functionality here.
	 * @return
	 */
	@RequestMapping(value = { "/ping" }, method = RequestMethod.GET)
    public @ResponseBody String ping(){
        return "pong";
    }
}
