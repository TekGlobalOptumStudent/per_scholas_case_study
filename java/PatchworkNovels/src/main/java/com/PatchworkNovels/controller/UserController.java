package com.PatchworkNovels.controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public String submitUser(@ModelAttribute User user, HttpServletRequest request) {
		String check = (String) request.getSession().getAttribute("login_username");
		String password = user.getPassword(), username = user.getUsername();
		if (userService.checkUsername(username) && check == null) {
			request.getSession().setAttribute("message", "That username is taken.");
			return "signup";
		} else if (checkSpecialCharacter(username) || checkSpecialCharacter(password)) {
			request.getSession().setAttribute("message", "You cannot have special characters in your username or password.");
			return "signup";
		} else if (username.length() < 4 || username.length() > 20) {
			request.getSession().setAttribute("message", "Your username is either too long or too short.");
			return "signup";
		} else if (password.length() < 4 || password.length() > 20) {
			request.getSession().setAttribute("message", "Your password is either too long or too short.");
			return "signup";
		} else if (!password.equals(user.getConfirmPassword())) {
			request.getSession().setAttribute("message", "Please make sure your passwords match.");
			return "signup";
		} else {
			if (check != null) {
				userService.editPassword(username, user.getPassword());
			} else {
				userService.addUser(user);
				request.getSession().setAttribute("login_username", username);
			}
		}
		request.getSession().setAttribute("message", null);
		return "redirect:/profile/" + username;
	}

	private boolean checkSpecialCharacter(String s) {
		if (s == null || s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if ("/-@#$%^&_-+=()[];\"\'\\|<>?!*{}:.-+=~".contains(s.charAt(i) + ""))
				return true;
		}
		return false;
	}
	
	@RequestMapping("changePassword")
	public ModelAndView changePassword(HttpServletRequest request) {
		request.getSession().setAttribute("message", null);
		ModelAndView mav = new ModelAndView("signup");
		return mav;
	}
	
	@PostMapping("deleteUser")
	public String deleteUser(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("login_username");
		request.getSession().setAttribute("login_username", null);
		userService.deleteUser(username);
		return "redirect:/home";
	}
	
	@PostMapping("uploadImage")
	public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("login_username");
		try {
			String imageData = new String(Base64.getEncoder().encode(file.getBytes()), "UTF8");
			userService.addProfileImage(username, imageData);
			request.getSession().setAttribute("login_profile", imageData);
		} catch (Exception e) {
			System.out.println("Error trying to read file");
			e.printStackTrace();
		}
		return "redirect:/profile/" + username;
	}

	@PostMapping("deleteImage")
	public String deleteImage(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("login_username");
		userService.deleteProfileImage(username);
		return "redirect:/profile/" + username;
	}
	
}
