package vn.van.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.van.entity.UserInfo;
import vn.van.repository.UserInfoRepository;

@Service
public record UserService(UserInfoRepository repository, PasswordEncoder passwordEncoder){
	
	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "thanh cong";
	}

}
