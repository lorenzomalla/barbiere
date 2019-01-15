package it.lorenzo.app.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("userId")
	private int userId;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("role")
	private String role;
	@JsonProperty("country")
	private String country;
	@JsonProperty("enabled")
	private boolean enabled;
	@JsonProperty("email")
	private String email;
	@JsonProperty("dateRegister")
	private Date dateRegister;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}