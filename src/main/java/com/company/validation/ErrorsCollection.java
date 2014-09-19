package com.company.validation;

import com.company.myproject.messaging.Message;

public interface ErrorsCollection {
	public boolean addError(String error);
	public boolean addError(Message error);
}
