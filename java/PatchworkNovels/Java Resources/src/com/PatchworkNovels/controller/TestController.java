package com.PatchworkNovels.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private SnippetService snippetService;
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private UserService userServices;

	@RequestMapping("/")
	public String indexHandler() {
		return "index";
	}

	@RequestMapping("/home")
	public ModelAndView homeHandler() {
		ModelAndView mav = new ModelAndView("home");
		//mav.addObject("popularStoryList", storyService.getAllStories());
		//mav.addObject("recentStoryList", storyService.getAllStories());
		return mav;
	}
	
	@RequestMapping("/create")
	public String createHandler() {
		return "create";
	}
	
	@RequestMapping("/list")
	public String listHandler() {
		return "list";
	}
	
	@RequestMapping("/profile")
	public String profileHandler() {
		return "profile";
	}
	
	@RequestMapping("/signup")
	public String signupHandler() {
		return "signup";
	}
	
	@RequestMapping("/snippets")
	public String snippetsHandler() {
		return "snippets";
	}
	
	@RequestMapping("/stories")
	public String storiesHandler() {
		return "stories";
	}
	
}
