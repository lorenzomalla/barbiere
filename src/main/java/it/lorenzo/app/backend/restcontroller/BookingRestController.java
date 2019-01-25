package it.lorenzo.app.backend.restcontroller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.lorenzo.app.bean.UserBookingBean;
import it.lorenzo.app.bean.UserInfoBean;
import it.lorenzo.app.entity.UserBooking;
import it.lorenzo.app.exception.BadRequestException;
import it.lorenzo.app.repository.BookingInfoRepository;
import it.lorenzo.app.utils.Utils;

@RestController
@RequestMapping("/rest/booking")
public class BookingRestController {

	@Autowired
	BookingInfoRepository bookingRepository;

	/**
	 * Salva la prenotazione per il cliente
	 * @author lorenzo.mallardo
	 * @param body
	 * @param request
	 * @return
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/saveUserBookingInfo", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<UserBookingBean> registerUserInfo(@RequestBody UserBooking body, HttpServletRequest request)
			throws BadRequestException {
		UserInfoBean userLogged = (UserInfoBean) request.getSession().getAttribute("userSession");
		UserBookingBean bean = new UserBookingBean();
		bean.setEndDateBooking(Utils.convertStringToDateTimestamp(body.getEndDateBooking()));
		bean.setStartDateBooking(Utils.convertStringToDateTimestamp(body.getStartDateBooking()));
		bean.setTitoloPrenotazione(body.getTitoloPrenotazione());
		UserInfoBean infoBean = new UserInfoBean();
		infoBean.setUserId(userLogged.getUserId());
		bean.setUser(infoBean);
		bookingRepository.save(bean);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Recupera tutte le prenotazioni di tutti i clienti
	 * @author lorenzo.mallardo
	 * @return
	 */
	@RequestMapping(value = "/getAllBookingInfo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Iterable<UserBookingBean>> getAllBookingInfo() {
		Iterable<UserBookingBean> listUserBooking = bookingRepository.findAll();
		return new ResponseEntity<Iterable<UserBookingBean>>(listUserBooking, HttpStatus.OK);
	}

	/**
	 * Metodo per cercare tutte le prenotazioni presente dalla data attuale fino ad
	 * una settimana
	 * @author lorenzo.mallardo
	 * @return
	 */
	@RequestMapping(value = "/getBookingOfWeek", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<UserBookingBean>> getBookingInfoBetweenStartEndDate() {
		Date initialCurrentDate = Utils.getCurrentDateOfRegister();
		Date finalDate = Utils.addDays(initialCurrentDate, 7);
		List<UserBookingBean> listUserBooking = bookingRepository.findByStartDateBookingBetween(initialCurrentDate,
				finalDate);
		return new ResponseEntity<List<UserBookingBean>>(listUserBooking, HttpStatus.OK);
	}
}
