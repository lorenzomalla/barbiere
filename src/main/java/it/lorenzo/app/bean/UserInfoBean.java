package it.lorenzo.app.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class UserInfoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	private String country;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_register")
	private Date dateRegister;

	private String email;

	private boolean enabled;

	private String password;

	private String role;

	private String username;

	// bi-directional many-to-one association to UserBooking
	@OneToMany(mappedBy = "user")

	private List<UserBookingBean> userBookings;

	public UserInfoBean() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateRegister() {
		return this.dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public List<UserBookingBean> getUserBookings() {
		return this.userBookings;
	}

	public void setUserBookings(List<UserBookingBean> userBookings) {
		this.userBookings = userBookings;
	}

	public UserBookingBean addUserBooking(UserBookingBean userBooking) {
		getUserBookings().add(userBooking);
		userBooking.setUser(this);

		return userBooking;
	}

	public UserBookingBean removeUserBooking(UserBookingBean userBooking) {
		getUserBookings().remove(userBooking);
		userBooking.setUser(null);

		return userBooking;
	}

}