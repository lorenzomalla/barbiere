package it.lorenzo.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.lorenzo.app.bean.UserInfoBean;

@Controller
//@RequestMapping("")
public class BookingController {

//	@RequestMapping(value = "booking", method = RequestMethod.GET)
//	public String booking() {
//		return "booking";
//	}
	@RequestMapping(value = "booking/user={username}", method = RequestMethod.GET)
	public String booking(@PathVariable("username") String username) {
		return "booking";
	}
//	@RequestMapping(value = "booking/user={username}", method = RequestMethod.GET)
//	public String booking(@PathVariable("username") String username, @ModelAttribute("user") UserInfoBean user,
//			ModelMap model) {
//		System.out.println("User : " + user.getUsername());
//		model.addAttribute("user", user);
//		return "booking";
//	}

}
