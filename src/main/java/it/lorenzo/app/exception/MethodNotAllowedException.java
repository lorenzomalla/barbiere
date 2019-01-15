package it.lorenzo.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends Exception {
	private static final long serialVersionUID = 1L;
	private Integer errorCode;
	private String description;
	
	
	public MethodNotAllowedException(String message, Integer errorCode){
		super(message);
		this.errorCode=errorCode;
	}
	
	public MethodNotAllowedException(String message, Integer errorCode, String description){
		super(message);
		this.errorCode=errorCode;
		this.description=description;
	}
	
	public Integer getErrorCode(){
		return this.errorCode;
	}

	public String getDescription() {
		return description;
	}
}
