package it.lorenzo.app.exception;

import org.springframework.http.HttpStatus;

public class GeneralizedRuntimeReturnException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	private Object body;
	
	public GeneralizedRuntimeReturnException(String message, HttpStatus httpStatus){
		super(message);
		this.httpStatus=httpStatus;
	}
	
	public GeneralizedRuntimeReturnException(String message, HttpStatus httpStatus, Object body){
		super(message);
		this.httpStatus=httpStatus;
		this.body=body;
	}
	
	public GeneralizedRuntimeReturnException(String message, HttpStatus httpStatus,it.lorenzo.app.entity.Error error){
		super(message);
		this.httpStatus=httpStatus;
		this.body=error;
	}
	
	public GeneralizedRuntimeReturnException(String message, HttpStatus httpStatus, Integer code){
		super(message);
		this.httpStatus=httpStatus;
		it.lorenzo.app.entity.Error error=new it.lorenzo.app.entity.Error();
		error.setCode(code);
		error.setMessage(message);
		this.body=error;
	}
	
	public GeneralizedRuntimeReturnException(String message, HttpStatus httpStatus, Integer code, String description){
		super(message);
		this.httpStatus=httpStatus;
		it.lorenzo.app.entity.Error error=new it.lorenzo.app.entity.Error();
		error.setCode(code);
		error.setMessage(message);
		error.setDescription(description);
		this.body=error;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public Object getBody() {
		return body;
	}
	
}
