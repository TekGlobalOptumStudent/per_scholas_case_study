package com.PatchworkNovels.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	// default
	
	@RequestMapping("/")
	public String indexHandler() {
		return "redirect:/home";
	}

	// home

	@RequestMapping("/home")
	public ModelAndView homeHandler(HttpServletRequest request) {
		request.getSession().setAttribute("message", null);
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("popularStoryList", storyService.getAllStories());
		mav.addObject("recentStoryList", storyService.getAllStories());
		return mav;
	}

	@RequestMapping("/list")
	public ModelAndView listHandler(HttpServletRequest request) {
		request.getSession().setAttribute("message", null);
		ModelAndView mav = new ModelAndView("list");
		mav.addObject("allStories", storyService.getAllStories());
		mav.addObject("allSnippets", snippetService.getAllSnippets());
		return mav;
	}

	// profile

	@RequestMapping("/profile/{username}")
	public ModelAndView profileHandler(@PathVariable(required = true) String username, HttpServletRequest request) {
		request.getSession().setAttribute("message", null);
		if (username.equals(request.getSession().getAttribute("login_username"))) {
			request.getSession().setAttribute("isLoggedIn", true);
		} else {
			request.getSession().setAttribute("isLoggedIn", false);
		}
		User user = userService.getUser(username);
		if (user != null) {
			ModelAndView mav = new ModelAndView("profile");
			mav.addObject("username", username);
			mav.addObject("userDateJoined", user.getDateJoined());
			mav.addObject("userPublishedStories", user.getPublishedStories());
			mav.addObject("userPublishedSnippets", user.getPublishedSnippets());
			mav.addObject("userFavoriteStories", user.getFavoriteStories());
			mav.addObject("userProfileImage", user.getProfileImage());
			return mav;
		}
		return new ModelAndView("error");
	}

	// signup

	@GetMapping("/signup")
	public ModelAndView signupHandler(HttpServletRequest request) {
		request.getSession().setAttribute("message", null);
		return new ModelAndView("signup");
	}

	@RequestMapping("login")
	public String login(@ModelAttribute User user, HttpServletRequest request) {
		if (userService.validateUser(user.getUsername(), user.getPassword())) {
			User dbUser = userService.getUser(user.getUsername());
			request.getSession().setAttribute("login_username", dbUser.getUsername());
			request.getSession().setAttribute("login_profile", dbUser.getProfileImage());
			request.getSession().setAttribute("message", null);
			return "redirect:/profile/" + dbUser.getUsername();
		}
		request.getSession().setAttribute("message", "Those credentials were not found in our database,"
				+ " please create a new account with those credentials.");
		return "signup";
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute("login_username", null);
		request.getSession().setAttribute("login_profile", null);
		request.getSession().setAttribute("message", null);
		request.getSession().setAttribute("editStory", null);
		request.getSession().setAttribute("editSnippet", null);
		return "redirect:/home";
	}

	// snippets

	@RequestMapping("/createSnippet")
	public ModelAndView createSnippetHandler(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("createSnippet");
		return mav;
	}
	
	@RequestMapping("/snippet/{snippetId}")
	public ModelAndView snippetsHandler(@PathVariable int snippetId) {
		Snippet snippet = snippetService.readSnippet(snippetId);
		if (snippet != null) {
			ModelAndView mav = new ModelAndView("snippet");
			mav.addObject("snippetId", snippet.getSnippetId());
			mav.addObject("snippetAuthor", snippet.getSnippetAuthor());
			mav.addObject("snippetText", snippet.getSnippetText());
			mav.addObject("snippetTimePosted", snippet.getSnippetTimePosted());
			mav.addObject("snippetStories", snippet.getSnippetStories());
			mav.addObject("snippetComments", snippet.getSnippetComments());
			return mav;
		}
		return new ModelAndView("error");
	}
	
	// stories

	@RequestMapping("/createStory")
	public ModelAndView createStoryHandler(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("createStory");
		mav.addObject("allSnippets", snippetService.getAllSnippets());
		return mav;
	}

	@RequestMapping("/story/{storyTitle}")
	public ModelAndView storiesHandler(@PathVariable String storyTitle) {
		Story story = storyService.readStory(storyTitle);
		if (story != null) {
			ModelAndView mav = new ModelAndView("story");
			mav.addObject("storyTitle", story.getStoryTitle());
			mav.addObject("storyAuthor", story.getStoryAuthor());
			mav.addObject("storyComments", story.getStoryComments());
			mav.addObject("storyRating", story.getStoryRating());
			mav.addObject("storyTimePosted", story.getStoryTimePosted());
			mav.addObject("storyText", story.getStoryText());
			return mav;
		}
		return new ModelAndView("/error");
	}
}
