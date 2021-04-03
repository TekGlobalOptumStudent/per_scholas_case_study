package com.PatchworkNovels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
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
		return "redirect:/home";
	}

	// home
	
	@RequestMapping("/home")
	public ModelAndView homeHandler() {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("popularStoryList", storyService.getAllStories());
		mav.addObject("recentStoryList", storyService.getAllStories());
		return mav;
	}
	
	// create
	
	@RequestMapping("/create")
	public ModelAndView createHandler() {
		ModelAndView mav = new ModelAndView("create");
		return mav;
	}
	
	// list
	
	@RequestMapping("/list")
	public ModelAndView listHandler() {
		ModelAndView mav = new ModelAndView("list");
		mav.addObject("allStories", storyService.getAllStories());
		mav.addObject("allSnippets", snippetService.getAllSnippets());
		return mav;
	}
	
	// profile
	
	@RequestMapping("/profile/{username}")
	public ModelAndView profileHandler(@PathVariable(required = true) String username) {
		User user = userService.getUser(username);
		if(user != null) {
			ModelAndView mav = new ModelAndView("profile");
			mav.addObject("username", username);
			// TODO: add picture
			mav.addObject("userDateJoined", user.getDateJoined());
			mav.addObject("userPublishedStories", user.getPublishedStories());
			mav.addObject("userPublishedSnippets", user.getPublishedSnippets());
			mav.addObject("userFavoriteStories", user.getFavoriteStories());
			return mav;
		}
		return new ModelAndView("error"); // TODO: make error page
	}
	
	// signup
	
	@GetMapping("/signup")
	public ModelAndView signupHandler() {
		return new ModelAndView("signup");
	}
	
	@PostMapping("/signup")
	public String submitUser(@ModelAttribute User user) {
		if(userService.checkUsername(user.getUsername())) return "signup";
		// TODO: validation, authentication, and authorization
		userService.addUser(user);		
		return "redirect:/profile/" + user.getUsername();
	}
	
	@RequestMapping("login")
	public String login(@ModelAttribute User user) {
		if(userService.validateUser(user.getPassword(), user.getPassword()))
			return "redirect:/profile/" + user.getUsername();
		return "#";
	}
	
	// snippets
	
	@RequestMapping("/snippets/{snippetId}")
	public ModelAndView snippetsHandler(@PathVariable int snippetId) {
		Snippet snippet = snippetService.readSnippet(snippetId);
		if(snippet != null) {
			ModelAndView mav = new ModelAndView("snippets");
			return mav;
		}
		return new ModelAndView("error"); // TODO: make error page
	}
	
	// stories
	
	@RequestMapping("/stories/{storyTitle}")
	public ModelAndView storiesHandler(@PathVariable String storyTitle) {
		Story story = storyService.readStory(storyTitle);
		if(story != null) {
			ModelAndView mav = new ModelAndView("stories");
			return mav;
		}
		return new ModelAndView("error"); // TODO: make error page
	}
	
}
