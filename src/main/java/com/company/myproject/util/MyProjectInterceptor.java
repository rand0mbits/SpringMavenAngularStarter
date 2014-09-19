package com.company.myproject.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

public class MyProjectInterceptor implements org.springframework.web.servlet.HandlerInterceptor {
	private static final Logger LOGGER = Logger.getLogger(MyProjectInterceptor.class);
	@Autowired
	Lookups lookups;

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null){
			LOGGER.debug("postHandle interceptor for: " + request.getRequestURI());
			LOGGER.debug("Adding lookups to model.");
			modelAndView.addObject("lookups", lookups);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			LOGGER.debug("Adding authentication to model.");
			modelAndView.addObject("auth", auth);
		}
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		return true;
	}
}