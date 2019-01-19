package it.lorenzo.app.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.lorenzo.app.bean.UserInfoBean;
import it.lorenzo.app.repository.UserInfoRepository;

@Controller
public class AutologinController {

	@Autowired
	UserInfoRepository userRepository;

	@Autowired
	public AuthenticationManager authenticationManager;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String homepage(Model model, HttpServletRequest request, HttpSession session) {
		Principal principal = request.getUserPrincipal();
		System.out.println("Utente loggato : " + principal.getName());
		String usernameLogged = principal.getName();
		model.addAttribute("username", usernameLogged);
//		System.out.println(session.getId()); // Il sessionId è uguale per lo stesso utente loggato
		session.setMaxInactiveInterval(60 * 24 * 24);// 24 H di Login
		// refreshato piu volte no)
		return "homepage";
	}

	@RequestMapping(value = "/register/signup", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerUser(@ModelAttribute("user") UserInfoBean user) throws IOException {
		try {
			HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			UserInfoBean activeUserInfo = userRepository.findByUsernameAndEnabled(user.getUsername(), true);
			authorities.add(new SimpleGrantedAuthority(activeUserInfo.getRole()));
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					activeUserInfo.getUsername(), user.getPassword(), authorities);
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			if (usernamePasswordAuthenticationToken.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				System.out.println((String.format("Auto login %s successfully!", user.getUsername())));
			}
		} catch (Exception e) {
			// TODO: Da rilanciare eccezione customizzata quando l'auto login va in errore
			System.out.println("Autologin fallito." + e.getMessage() + e);
		}
		return "redirect:/hello";
	}
}