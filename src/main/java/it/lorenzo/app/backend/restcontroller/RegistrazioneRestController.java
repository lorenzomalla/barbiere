package it.lorenzo.app.backend.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.lorenzo.app.bean.UserInfoBean;
import it.lorenzo.app.entity.UserInfo;
import it.lorenzo.app.exception.BadRequestException;
import it.lorenzo.app.repository.UserInfoRepository;
import it.lorenzo.app.utils.Constant;
import it.lorenzo.app.utils.Regex;
import it.lorenzo.app.utils.Utils;

@RestController
@RequestMapping("/rest")
public class RegistrazioneRestController {

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	PasswordEncoder encoder;

	@RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<UserInfoBean> registerUserInfo(@RequestBody UserInfo body) throws BadRequestException {
		checkParameterBody(body);
		UserInfoBean bean = new UserInfoBean();
		bean.setCountry(body.getCountry() != null ? body.getCountry() : null);
		bean.setDateRegister(Utils.getCurrentDateOfRegister());
		bean.setEnabled(true);
		bean.setEmail(body.getEmail() != null ? body.getEmail() : null);
		bean.setRole(Constant.ROLE_USER);
		bean.setPassword(encoder.encode(body.getPassword()));
		bean.setUsername(body.getUsername());
		ResponseEntity<UserInfoBean> responseEntity = new ResponseEntity<UserInfoBean>(userInfoRepository.save(bean),
				HttpStatus.OK);
		return responseEntity;
	}

	private void checkParameterBody(UserInfo body) throws BadRequestException {
		if (!body.getPassword().matches(Regex.REGEX_PASSWORD)) {
			throw new BadRequestException(Constant.ERR_610_MSG, Constant.ERR_610_CODE);
		}
	}

	@RequestMapping(value = "/email/check", method = RequestMethod.POST, consumes = "application/json")
	public int checkEmail(@RequestBody UserInfo body) throws BadRequestException {
		if (body.getEmail() != null && !body.getEmail().isEmpty()) {
			Optional<UserInfoBean> user = userInfoRepository.findByEmail(body.getEmail());
			if (user.isPresent()) {
				throw new BadRequestException(Constant.MAIL_USED, Constant.ERR_610_CODE);
			}
		}
		return 0;
	}

}
