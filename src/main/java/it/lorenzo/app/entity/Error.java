package it.lorenzo.app.entity;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error
 */
@JsonInclude(Include.NON_NULL)
public class Error {
	@JsonProperty("code")
	private Integer code = null;

	@JsonProperty("reason")
	private String message = null;

	@JsonProperty("message")
	private String description = null;

	@JsonProperty("infoURL")
	private String infoURL = null;

	public Error() {

	}

	public Error(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public Error(int code, String message, String description) {
		this.code = code;
		this.message = message;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInfoURL() {
		return infoURL;
	}

	public void setInfoURL(String infoURL) {
		this.infoURL = infoURL;
	}

}