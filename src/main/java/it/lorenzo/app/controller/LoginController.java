package it.lorenzo.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Authentication : " + auth.getName());
		System.out.println(auth.getAuthorities());
		if (!(auth instanceof AnonymousAuthenticationToken)) {
//		request.getSession().setAttribute("userActive", activeUserInfo);
			return "redirect:/hello";
		}
		return "login";
	}

//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logout(HttpServletRequest request, HttpServletResponse response) {
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		if (auth != null) {
////			new SecurityContextLogoutHandler().logout(request, response, auth);
////			return "redirect:/login?logout=true";
////		}
//		return "redirect:/login?logout=true";
//	}

}