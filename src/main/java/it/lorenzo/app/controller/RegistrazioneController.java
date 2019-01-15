package it.lorenzo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
//@RequestMapping("/booking/barber")
public class RegistrazioneController {

	@Autowired
	private RestTemplate restTemplate;

	private String url = "http://localhost:8080/rest";

	@RequestMapping(value = "/registrazione", method = RequestMethod.GET)
	public String registerUser() {
		return "registrazione";
	}

}
