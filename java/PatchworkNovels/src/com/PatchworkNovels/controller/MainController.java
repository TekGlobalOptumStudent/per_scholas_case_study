package com.PatchworkNovels.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String indexHandler() {
		return "index";
	}
	
	@RequestMapping("/home")
	public String homeHandler() {
		return "home";
	}
	
}
