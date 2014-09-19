package com.company.myproject.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
		private static final Logger LOGGER = Logger.getLogger(LoginController.class);
		public static final String LOGIN_VIEW = "login";

		@RequestMapping(value="/login", method = RequestMethod.GET)
		public String login(ModelMap model, HttpServletResponse response) {
			response.setHeader("loginpage", "true");
			LOGGER.info("loading login page.");
			return LOGIN_VIEW;
		}
		
		@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
		public String loginerror(ModelMap model) {
			model.addAttribute("error", "true");
			return LOGIN_VIEW;
		}
		
		@RequestMapping(value="/logout", method = RequestMethod.GET)
		public String logout(ModelMap model) {
			return LOGIN_VIEW;
		}
}
