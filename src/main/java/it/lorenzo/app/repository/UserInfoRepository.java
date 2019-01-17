package it.lorenzo.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import it.lorenzo.app.bean.UserInfoBean;

@Service
public interface UserInfoRepository extends CrudRepository<UserInfoBean, String> {
	UserInfoBean findByUsernameAndEnabled(String username, boolean enabled);

	UserInfoBean findByUsername(String username);

	Optional<UserInfoBean> findByEmail(String email);

	UserInfoBean findByUsernameAndEmail(String username, String email);

}