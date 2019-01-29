package it.lorenzo.app.backend.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.lorenzo.app.service.MyEmailService;
import it.lorenzo.app.service.OtpService;

@RestController
public class OTPRestController {

	@Autowired
	public OtpService otpService;

	@Autowired
	public MyEmailService myEmailService;

	/**
	 * Genera il codice per la registrazione inviando una mail al barbiere
	 * @author lorenzo.mallardo
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/generateOTP", method = RequestMethod.GET)
	public ResponseEntity<String> generateOtp(@RequestParam("username") String username) {
		int otp = otpService.generateOTP(username);
		System.out.println("OTP : " + otp);
		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("user", username);
		replacements.put("otpnum", String.valueOf(otp));
		String message = replacements.get("otpnum");
		myEmailService.sendOtpMessage("lorenzomalla2@gmail.com", "OTP BookingBarber", "Ciao Castrese, l'utente "
				+ username
				+ " si è registrato al tuo sistema.\nPer confermare la sua registrazione fornisci lui il codice per registrarsi. Codice: "
				+ message);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * Valida il codice generato e fornito dal barbiere per la registrazione
	 * @author lorenzo.mallardo
	 * @param otpnum
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/validateOTP", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> validateOtp(@RequestParam("otpnum") String otpnum,
			@RequestParam("username") String username) {
		System.out.println(" Otp Number : " + otpnum); // TODO : Da commentare in PROD
		if (Integer.parseInt(otpnum) >= 0) {
			int serverOTP = otpService.getOtp(username);
			if (serverOTP > 0 && (Integer.parseInt(otpnum) == serverOTP)) {
				otpService.clearOTP(username);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
