package com.company.myproject.messaging;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


public interface MessageCollection {
	
	/**
	 * Adds a message
	 * @param message
	 * @return
	 */
	public abstract boolean add(Message message);

	/**
	 * Adds a message of the default message type (info)
	 * @param message
	 * @return 
	 */
	public abstract boolean add(String message);

	/**
	 * Adds a message of the specified type
	 * @param message
	 * @param type
	 * @return 
	 */
	public abstract boolean add(String message, String type);

	/**
	 * Adds an info message
	 * @param message
	 * @return 
	 */
	public abstract boolean addInfo(String message);

	/**
	 * Adds an info message
	 * @param message
	 * @return 
	 */
	public boolean addInfo(Message message);

	/**
	 * Adds a prompt message
	 * @param message
	 */
	public abstract boolean addPrompt(String message);
	/**
	 * Adds a prompt message
	 * @param message
	 */
	public boolean addPrompt(Message message);
	
	/**
	 * Adds a warning message
	 * @param message
	 */
	public abstract boolean addWarning(String message);
	/**
	 * Adds a warning message
	 * @param message
	 */
	public boolean addWarning(Message message);

	/**
	 * Adds an error message
	 * @param message
	 */
	public abstract boolean addError(String message);
	
	/**
	 * Adds an error message
	 * @param message
	 */
	public abstract boolean addError(Message message);

	public abstract List<Message> getAll();

	/**
	 * Returns true if the message collection has at least one error
	 * @return
	 */
	public abstract boolean hasErrors();
	
	/**
	 * Returns true if the message collection has at least one error with the specified request id
	 * @return
	 */
	public boolean hasErrors(long requestId);

	/**
	 * Returns true if the message collection has at least one prompt
	 * @return
	 */
	public abstract boolean hasPrompts();

}