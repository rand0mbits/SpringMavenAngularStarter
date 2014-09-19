package com.company.myproject.messaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.myproject.servlet.EnhancedHttpRequest;
import com.company.validation.ErrorsCollection;

@Component
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessageCollectionImpl implements ErrorsCollection, MessageCollection, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3726557662382824986L;
	List<Message> messages;
	@Autowired private EnhancedHttpRequest request;

	@PostConstruct
	private void init() {
		messages = new ArrayList<Message>();
	}
	
	@Override
	public boolean add(String message) {
		// since this method adds an info message, check that there aren't any error messages
		if (hasErrors(getRequest().getRequestId())) {
			return false;
		}
		messages.add(new Message(Message.INFO, message, getRequest().getRequestId()));
		return true;
	}
	
	@Override
	public boolean add(String message, String type) {
		// check if the type being added is non-error and if collection has error messages with same request id
		if (!Message.ERROR.equals(type) && hasErrors(getRequest().getRequestId())) {
			return false;
		}
		messages.add(new Message(type, message, getRequest().getRequestId()));
		return true;
	}
	
	@Override
	public boolean addInfo(String message) {
		return addInfo(new Message(Message.INFO, message, getRequest().getRequestId()));
	}
	
	public boolean addInfo(Message message) {
		// since this method adds an info message, check that there aren't any error messages
		if (hasErrors(getRequest().getRequestId())) {
			return false;
		}
		messages.add(message);
		return true;
	}
	
	public boolean addWarning(String message) {
		return addWarning(new Message(Message.WARNING, message, getRequest().getRequestId()));
	}
	
	public boolean addWarning(Message message) {
		messages.add(message);
		return true;
	}
	
	@Override
	public boolean addPrompt(String message) {
		return addPrompt(new Message(Message.PROMPT, message, getRequest().getRequestId()));
	}
	
	public boolean addPrompt(Message message) {
		// since this method adds an prompt message, check that there aren't any error messages
		if (hasErrors(getRequest().getRequestId())) {
			return false;
		}
		messages.add(message);
		return true;
	}
	
	@Override
	public boolean addError(String message) {
		return addError(new Message(Message.ERROR, message, getRequest().getRequestId()));
	}
	
	public boolean addError(Message message) {
		messages.add(message);
		// if an error message is added to the collection, all non-error messages with the same request id should be removed
		Iterator<Message> i = messages.iterator();
		while (i.hasNext()) {
			Message m = i.next();
			if (!m.getLevel().equals(Message.ERROR) && m.getRequestId() == getRequest().getRequestId()) {
				i.remove();
			}
		}
		return true;
	}
	
	@Override
	public List<Message> getAll() {
		List<Message> out = messages;
		messages = new ArrayList<Message>();
		return out;
	}
	
	@Override
	public boolean hasErrors() {
		for (Message message : messages) {
			if (message.getLevel().equals(Message.ERROR)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean hasErrors(long requestId) {
		for (Message message : messages) {
			if (message.getLevel().equals(Message.ERROR) && message.getRequestId() == requestId) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean hasPrompts() {
		for (Message message : messages) {
			if (message.getLevel().equals(Message.PROMPT)) {
				return true;
			}
		}
		return false;
	}
	private EnhancedHttpRequest getRequest() {
		return (EnhancedHttpRequest) ((ServletRequestAttributes) 
						RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	@Override
	public boolean add(Message message) {
		switch (message.getLevel()) {
		case Message.INFO: return addInfo(message);
		case Message.WARNING: return addWarning(message);
		case Message.ERROR: return addError(message);
		case Message.PROMPT: return addPrompt(message);
		default: return false;
		}
	}
}
