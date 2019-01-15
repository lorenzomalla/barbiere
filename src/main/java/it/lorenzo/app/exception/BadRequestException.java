package it.lorenzo.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {
	private static final long serialVersionUID = 1L;
	private Integer errorCode;
	private String description;

	public BadRequestException(String message, Integer errorCode){
		super(message);
		this.errorCode=errorCode;
	}
	
	public BadRequestException(String message, Integer errorCode, String description){
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
