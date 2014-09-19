package com.company.myproject.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.myproject.messaging.MessageCollection;
import com.company.security.PasswordHash;
import com.company.validation.ErrorsCollection;
import com.company.validation.Validator;

@Service
public class UserManagementService {
	private static final Logger logger = Logger.getLogger(UserManagementService.class);
	
	private String generateNewPassword() {
		String newPassword = "";
		for(int i = 0; i < 12; i++) {
			Double dubs = Math.random();
			int result = 48;
			if(dubs < .333) {
				//random number
				 result = 48 + (int) ( Math.random()*(57 - 48) + 0.5);
			} else if(dubs >= .333 && dubs < .667) {
				//random uppercase
				result = 65 + (int) ( Math.random()*(90 - 65) + 0.5);
				if(result == 73) {
					result++;
				}
			} else {
				//random lowercase
				result = 97 + (int) ( Math.random()*(122 - 97) + 0.5);
				if(result == 108) {
					result++;
				}
			}
			Character ch = (char) result;
			newPassword = newPassword + ch.toString();
		}
		return newPassword;
	}
}
