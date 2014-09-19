package com.company.myproject.messaging;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;





import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.validation.ErrorsCollection;

@Component
public class MessagesProxy implements ErrorsCollection, Serializable {
	private static final Logger LOGGER = Logger.getLogger(MessagesProxy.class);
	@Autowired private MessageCollection messages;
	private static final String ERROR_STR = "Error interfacing with MessageCollection";

	public boolean add(String message) {
		try {
			return messages.add(message);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
	
	public boolean add(Message message) {
		try {
			return messages.add(message);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
	

	public boolean add(String message, String type) {
		try {
			return messages.add(message, type);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
	

	public boolean addInfo(String message) {
		try {
			return messages.addInfo(message);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
	
	public boolean addInfo(Message message) {
		try {
			return messages.addInfo(message);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
	
	public boolean addWarning(String message) {
		try {
			return messages.addWarning(message);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
	
	public boolean addWarning(Message message) {
		try {
			return messages.addWarning(message);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
	

	public boolean addPrompt(String message) {
		try {
			return messages.addPrompt(message);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
	
	public boolean addPrompt(Message message) {
		try {
			return messages.addPrompt(message);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
	

	public boolean addError(String message) {
		try {
			return messages.addError(message);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
	

	public List<Message> getAll() {
		try {
			return messages.getAll();
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
		}
		return Collections.<Message>emptyList();
	}
	

	public boolean hasErrors() {
		try {
			return messages.hasErrors();
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
		}
		return false;
	}
	
	public boolean hasErrors(long requestId) {
		try {
			return messages.hasErrors(requestId);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
		}
		return false;
	}
	

	public boolean hasPrompts() {
		try {
			return messages.hasPrompts();
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
		}
		return false;
	}


	@Override
	public boolean addError(Message error) {
		try {
			return messages.addError(error);
		} catch (Exception e) {
			LOGGER.debug(ERROR_STR, e);
			return false;
		}
	}
}
