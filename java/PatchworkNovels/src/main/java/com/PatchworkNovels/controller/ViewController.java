package com.PatchworkNovels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	UserService userServices;

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
		return mav;
	}
	
	@RequestMapping("/profile")
	public ModelAndView profileHandler() {
		ModelAndView mav = new ModelAndView("profile");
		return mav;
	}
	
	@RequestMapping("/signup")
	public ModelAndView signupHandler() {
		ModelAndView mav = new ModelAndView("signup");
		return mav;
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
