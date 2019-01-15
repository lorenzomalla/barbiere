package it.lorenzo.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/booking/barber")
public class UserInfoController {

	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public String login2() {
		return "login2";
	}

}