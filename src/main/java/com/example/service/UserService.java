package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ユーザー情報の追加
	 * @param user
	 */
	public void userInsert(User user) {
		userRepository.userInsert(user);
	}
	
	/**
	 * ユーザー情報の確保
	 * @param mail
	 * @param password
	 * @return
	 */
	public User userLogin(String mail,String password) {
		return userRepository.userLogin(mail, password);		
	}

}
