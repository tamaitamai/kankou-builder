package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	/**
	 * ログインページへ遷移
	 * @param userForm
	 * @return
	 */
	@GetMapping("/toLogin")
	public String toLogin(UserForm userForm) {
		return "/user/login.html";
	}
	
	/**
	 * ログイン情報を調べる
	 * @param userForm
	 * @param result
	 * @return
	 */
	@PostMapping("/login")
	public String login(@Validated UserForm userForm,
			BindingResult result){
		if(result.hasErrors()) {
			return toLogin(userForm);
		}
		User user=userService.userLogin(userForm.getMail(), userForm.getPassword());
		session.setAttribute("userLogin", user);
		return "/main/tourism.html";
	}
	
	/**
	 * 新規会員登録ページへ遷移
	 * @param userForm
	 * @return
	 */
	@GetMapping("/toInsert")
	public String toInsert(UserForm userForm) {
		return "/user/insert.html";
	}
	
	/**
	 * 新しい会員情報の追加
	 * @param userForm
	 * @param result
	 * @return
	 */
	@PostMapping("/insert")
	public String insert(@Validated UserForm userForm,BindingResult result) {
		if(result.hasErrors()) {
			return toLogin(userForm);
		}
		
		User user=new User();
		BeanUtils.copyProperties(userForm, user);
		userService.userInsert(user);				
		return "redirect:/user/toLogin";
	}
	
	@PostMapping("/logOut")
	public String logOut() {
		session.invalidate();
		return "redirect:/user/toLogin";
	}
}
