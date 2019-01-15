package it.lorenzo.app.securityconf;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.lorenzo.app.bean.UserInfoBean;
import it.lorenzo.app.repository.UserInfoRepository;


@Service
public class MyAppUserDetailsService implements UserDetailsService {
	@Autowired
	private UserInfoRepository userInfoDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfoBean activeUserInfo = userInfoDAO.findByUsernameAndEnabled(username, true);
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
		UserDetails userDetails = (UserDetails) new User(activeUserInfo.getUsername(), activeUserInfo.getPassword(),
				Arrays.asList(authority));
		return userDetails;
	}
}