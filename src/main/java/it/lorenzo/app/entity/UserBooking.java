package it.lorenzo.app.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserBooking implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("endDateBooking")
	private String endDateBooking;

	@JsonProperty("startDateBooking")
	private String startDateBooking;

	@JsonProperty("title")
	private String titoloPrenotazione;

	@JsonProperty("userInfo")
	private UserInfo userInfo;

	public UserBooking() {
	}

	public String getTitoloPrenotazione() {
		return titoloPrenotazione;
	}

	public void setTitoloPrenotazione(String titoloPrenotazione) {
		this.titoloPrenotazione = titoloPrenotazione;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndDateBooking() {
		return endDateBooking;
	}

	public void setEndDateBooking(String endDateBooking) {
		this.endDateBooking = endDateBooking;
	}

	public String getStartDateBooking() {
		return startDateBooking;
	}

	public void setStartDateBooking(String startDateBooking) {
		this.startDateBooking = startDateBooking;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserInfo getUser() {
		return userInfo;
	}

	public void setUser(UserInfo user) {
		this.userInfo = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}