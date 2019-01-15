package it.lorenzo.app.exceptionhandler;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.lorenzo.app.exception.BadRequestException;
import it.lorenzo.app.utils.Constant;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(CustomizedResponseEntityExceptionHandler.class.getName());

	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		it.lorenzo.app.entity.Error error = new it.lorenzo.app.entity.Error();
		String exMessage = ex.getMessage();
		Pattern p = Pattern.compile("Unrecognized field \"([^\"]*)\"");
		Matcher m = p.matcher(exMessage);
		String unrecognizedField = null;
		if (m.find()) {
			unrecognizedField = m.group(1);
			error.setDescription(Constant.ERR_693_DESC.replace("#parameter-name#", unrecognizedField));
			error.setCode(Constant.ERR_693_CODE);
			error.setMessage(Constant.ERR_693_MSG);
			LOGGER.log(Level.INFO, " - BadRequestException(400):" + error.toString());
			LOGGER.log(Level.INFO, " - BadRequestException(400):" + ex.getMessage());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		LOGGER.log(Level.INFO, " - BadRequestException(400):" + error.toString());
		LOGGER.log(Level.INFO, " - BadRequestException(400):" + ex.getMessage());
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<it.lorenzo.app.entity.Error> handleBadRequestException(BadRequestException ex,
			WebRequest request) {
		it.lorenzo.app.entity.Error error = new it.lorenzo.app.entity.Error();
		error.setCode(ex.getErrorCode());
		error.setMessage(ex.getMessage());
		error.setDescription(ex.getDescription());
		LOGGER.log(Level.INFO, " - BadRequestException(400):" + error.toString());
		LOGGER.log(Level.INFO, " - BadRequestException(400):" + ex.getMessage().toString());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
