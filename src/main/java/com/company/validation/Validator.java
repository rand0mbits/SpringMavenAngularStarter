package com.company.validation;


public class Validator {
	private ErrorsCollection errors;
	private boolean valid = true;
	private String nextErrorMessage = null;
	
	public Validator (ErrorsCollection errors) {
		this.errors = errors;
	}
	/**
	 * Validate an argument, adding an error if the argument String is empty (null or zero length).
	 * @param field
	 * @param fieldName
	 * @return
	 */
	public Validator notEmpty(String field, String fieldName) {
		fieldName = checkFieldName(fieldName);
		if (field == null || field.isEmpty()) {
			addError(fieldName + " cannot be empty.");
		}

		clearNextErrorMessage();
		return this;
	}
	
	public Validator notEmpty(Object field, String fieldName) {
		fieldName = checkFieldName(fieldName);
		if (field == null) {
			addError(fieldName + " cannot be empty.");
		}
		clearNextErrorMessage();
		return this;
	}
	
	/**
	 * Validate an argument, adding an error if the argument String is empty (null or zero length).
	 * @param field
	 * @return
	 */
	public Validator notEmpty(String field) {
		return notEmpty(field, null);
	}
	
	/**
	 * Validate that field value is greater than value1 (not inclusive) and less than value2 (not inclusive).
	 * @param field
	 * @param value1
	 * @param value2
	 * @param fieldName
	 * @return
	 */
	public <T extends Comparable<T>> Validator isBetween(T field, T value1,
			T value2, String fieldName) {
		fieldName = checkFieldName(fieldName);
		if (field != null && !(field.compareTo(value1) > 0 && field.compareTo(value2) < 0)) {
			addError(fieldName + " must be between " + value1 + " and " + value2 + ".");
		}
		clearNextErrorMessage();
		return this;
	}
	
	/**
	 * Validate an argument, adding an error if the test result is false.
	 * @param expression a boolean expression
	 * @param message the error message you would like to see if the expression is false
	 * @return
	 */
	public Validator isTrue(boolean expression, String message) {
		if (expression) {
			addError(message);
		}
		clearNextErrorMessage();
		return this;
	}
	
	/**
	 * New password validate.
	 * @param password1
	 * @param password1
	 * @return
	 */
	public Validator passwordValidate(String password1, String password2) {
		if (password1 == null || password2 == null || "".equals(password1.trim()) || "".equals(password2.trim()) || !password1.trim().equals(password2.trim())) {
			addError("Your new passwords must match.");
		}
//		else if() {
//		OTHER PASSWORD RULES COULD GO HERE	
//		}
		clearNextErrorMessage();
		return this;
	}
	
	/**
	 * Adds an error to the errors collection and sets the validator valid state to false.
	 * If {@link #nextErrorMessage} is not null, uses that instead of the passed in error string.
	 * @param error
	 */
	public void addError(String error) {
		if (nextErrorMessage == null) errors.addError(error);
		else {
			errors.addError(nextErrorMessage);
			clearNextErrorMessage();
		}
		valid = false;
	}
	
	/**
	 * Returns false if the validator instance has been used to validate at least one invalid argument. Returns false otherwise.
	 * @return
	 */
	public boolean isValid() {
		return valid;
	}
	
	private String checkFieldName(String fieldName) {
		if (fieldName == null || fieldName.isEmpty()) {
			fieldName = "Field";
		}
		return fieldName;
	}
	
	private void clearNextErrorMessage(){
		nextErrorMessage = null;
	}
	
	/**
	 * @return the nextInvalidMessage
	 */
	public String getNextErrorMessage() {
		return nextErrorMessage;
	}
	/**
	 * If not set to null, if the following validation method encounters an invalid value, 
	 * the nextErrorMessage value will be used as the error for that validation.
	 * The nextErrorMessage will be cleared after each validation call.
	 * @param nextErrorMessage the nextInvalidMessage to set
	 */
	public void setNextErrorMessage(String nextErrorMessage) {
		this.nextErrorMessage = nextErrorMessage;
	}
	
	/**
	 * Sets the current validator to invalid status
	 * @return the validator instance
	 */
	public Validator setInvalid() {
		valid = false;
		return this;
	}
}
