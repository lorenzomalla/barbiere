package it.lorenzo.app.controller;

import java.io.IOException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.lorenzo.app.bean.UserInfoBean;
import it.lorenzo.app.repository.UserInfoRepository;

@Controller
public class IndexController {

	@Autowired
	UserInfoRepository userRepository;

	@Autowired
	public AuthenticationManager authenticationManager;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String homepage() {
		return "hello";
	}

	@RequestMapping(value = "/register/signup", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerUser(@ModelAttribute("user") UserInfoBean user, ModelMap map) throws IOException {
		try {
			HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			UserInfoBean activeUserInfo = userRepository.findByUsernameAndEnabled(user.getUsername(), true);
			authorities.add(new SimpleGrantedAuthority(activeUserInfo.getRole()));
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					activeUserInfo.getUsername(), user.getUsername(), authorities);
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			if (usernamePasswordAuthenticationToken.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				System.out.println((String.format("Auto login %s successfully!", user.getUsername())));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + e);
		}
		map.addAttribute("user", user);
		return "redirect:/hello";
	}
}