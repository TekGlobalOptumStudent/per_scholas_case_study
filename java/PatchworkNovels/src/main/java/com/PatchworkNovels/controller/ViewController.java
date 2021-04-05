package com.PatchworkNovels.controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	
	@RequestMapping("/createSnippet")
	public ModelAndView createSnippetHandler() {
		ModelAndView mav = new ModelAndView("createSnippet");
		return mav;
	}
	
	@RequestMapping("/createStory")
	public ModelAndView createStoryHandler() {
		ModelAndView mav = new ModelAndView("createStory");
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
			mav.addObject("userDateJoined", user.getDateJoined());
			mav.addObject("userPublishedStories", user.getPublishedStories());
			mav.addObject("userPublishedSnippets", user.getPublishedSnippets());
			mav.addObject("userFavoriteStories", user.getFavoriteStories());
			if(user.getProfileImage() != null) {
				try {
					String encoding = "data:image/png;base64," + new String(user.getProfileImage(), "UTF8");
					mav.addObject("userProfileImage", encoding);
				} catch(Exception e) {
					System.out.println("Error getting image");
				}
			}
			return mav;
		}
		return new ModelAndView("error"); // TODO: make error page
	}
	
	@RequestMapping("changePassword")
	public ModelAndView changePassword(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("signup");
		return mav;
	}
	
	@RequestMapping("deleteUser")
	public String deleteUser(HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("login_username");
		request.getSession().setAttribute("login_username", null);
		userService.deleteUser(username);
		return "redirect:/home";
	}
	
	@PostMapping("uploadImage")
	public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("login_username");
		try {
			userService.addProfileImage(username, Base64.getEncoder().encode(file.getBytes()));
		} catch (Exception e) {
			System.out.println("Error trying to read file");
			e.printStackTrace();
		}
		return "redirect:/profile/" + username;
	}
	
	@PostMapping("deleteImage")
	public String deleteImage(HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("login_username");
		userService.deleteProfileImage(username);
		return "redirect:/profile/" + username;
	}
	
	// signup
	
	@GetMapping("/signup")
	public ModelAndView signupHandler() {
		return new ModelAndView("signup");
	}
	
	@PostMapping("/signup")
	public String submitUser(@ModelAttribute User user, HttpServletRequest request) {
		if(userService.checkUsername(user.getUsername())) return "signup";
		// TODO: validation, authentication, and authorization
		userService.addUser(user);
		request.getSession().setAttribute("login_username", user.getUsername());
		return "redirect:/profile/" + user.getUsername();
	}
	
	@RequestMapping("login")
	public String login(@ModelAttribute User user, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("login_username");
		if(username != null) {
			userService.editUser(username, username, user.getPassword());
			return "redirect:/profile/" + user.getUsername();
		} else if(userService.validateUser(user.getUsername(), user.getPassword())) {
			request.getSession().setAttribute("login_username", user.getUsername());
			return "redirect:/profile/" + user.getUsername();
		}
		return "signup";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute("login_username", null);
		return "redirect:/home";
	}
	
	// snippets
	
	@RequestMapping("/snippet/{snippetId}")
	public ModelAndView snippetsHandler(@PathVariable int snippetId) {
		Snippet snippet = snippetService.readSnippet(snippetId);
		if(snippet != null) {
			ModelAndView mav = new ModelAndView("snippet");
			mav.addObject("snippetAuthor", snippet.getSnippetAuthor());
			mav.addObject("snippetText", snippet.getSnippetText());
			mav.addObject("snippetTimePosted", snippet.getSnippetTimePosted());
			mav.addObject("snippetStories", snippet.getSnippetStories());
			mav.addObject("snippetComments", snippet.getSnippetComments());
			return mav;
		}
		return new ModelAndView("error"); // TODO: make error page
	}
	
	// stories
	
	@RequestMapping("/story/{storyTitle}")
	public ModelAndView storiesHandler(@PathVariable String storyTitle) {
		Story story = storyService.readStory(storyTitle);
		if(story != null) {
			ModelAndView mav = new ModelAndView("story");
			mav.addObject("storyAuthor", story.getStoryAuthor());
			mav.addObject("storyComments", story.getStoryComments());
			mav.addObject("storyRating", story.getStoryRating());
			mav.addObject("storyTimePosted", story.getStoryTimePosted());
			mav.addObject("storyText", story.getStoryText());
			return mav;
		}
		return new ModelAndView("error"); // TODO: make error page
	}
	
}
