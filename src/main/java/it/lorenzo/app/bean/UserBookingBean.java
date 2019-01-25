package it.lorenzo.app.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_booking")
public class UserBookingBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date_booking")
	private Date endDateBooking;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date_booking")
	private Date startDateBooking;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "id_user")
	private UserInfoBean user;

	@Column(name = "title_booking")
	private String titoloPrenotazione;

	public UserBookingBean() {
	}

	public String getTitoloPrenotazione() {
		return titoloPrenotazione;
	}

	public void setTitoloPrenotazione(String titoloPrenotazione) {
		this.titoloPrenotazione = titoloPrenotazione;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndDateBooking() {
		return this.endDateBooking;
	}

	public void setEndDateBooking(Date endDateBooking) {
		this.endDateBooking = endDateBooking;
	}

	public Date getStartDateBooking() {
		return this.startDateBooking;
	}

	public void setStartDateBooking(Date startDateBooking) {
		this.startDateBooking = startDateBooking;
	}

	public UserInfoBean getUser() {
		return this.user;
	}

	@JsonIgnore
	public void setUser(UserInfoBean user) {
		this.user = user;
	}

}