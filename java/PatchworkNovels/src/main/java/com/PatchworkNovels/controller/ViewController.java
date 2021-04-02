package com.PatchworkNovels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

@Controller
public class ViewController {

	@Autowired
	CommentService commentService;
	
	@Autowired
	SnippetService snippetService;
	
	@Autowired
	StoryService storyService;
	
	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String indexHandler() {
		return "index";
	}

	@RequestMapping("/home")
	public ModelAndView homeHandler() {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("popularStoryList", storyService.getAllStories());
		mav.addObject("recentStoryList", storyService.getAllStories());
		return mav;
	}
	
	@RequestMapping("/create")
	public ModelAndView createHandler() {
		ModelAndView mav = new ModelAndView("create");
		return mav;
	}
	
	@RequestMapping("/list")
	public ModelAndView listHandler() {
		ModelAndView mav = new ModelAndView("list");
		mav.addObject("allStories", storyService.getAllStories());
		mav.addObject("allSnippets", snippetService.getAllSnippets());
		return mav;
	}
	
	@GetMapping("/profile")
	public ModelAndView profileHandler(@ModelAttribute User user) {
		ModelAndView mav = new ModelAndView("profile");
		return mav;
	}
	
	@GetMapping("/signup")
	public ModelAndView signupHandler() {
		ModelAndView mav = new ModelAndView("signup", "user", new User());
		return mav;
	}
	
	@PostMapping("/signup")
	public String submitUser(@ModelAttribute User user, ModelMap model) {
		if(userService.checkUsername(user.getUsername())) return "signup";
		model.addAttribute("username", user.getUsername());
		return "redirect:/profile";
	}
	
	@RequestMapping("/snippets")
	public ModelAndView snippetsHandler() {
		ModelAndView mav = new ModelAndView("snippets");
		return mav;
	}
	
	@RequestMapping("/stories")
	public ModelAndView storiesHandler() {
		ModelAndView mav = new ModelAndView("stories");
		return mav;
	}
	
}
