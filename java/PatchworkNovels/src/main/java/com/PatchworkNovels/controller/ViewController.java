package com.PatchworkNovels.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

@RestController
@RequestMapping("/")
public class ViewController {

	@Autowired
	CommentService commentService;

	@Autowired
	SnippetService snippetService;

	@Autowired
	StoryService storyService;

	@Autowired
	UserService userService;

	//@RequestMapping("/")
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

	// create

	@RequestMapping("/createSnippet")
	public ModelAndView createSnippetHandler(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("createSnippet");
		return mav;
	}

	@RequestMapping("/createStory")
	public ModelAndView createStoryHandler(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("createStory");
		mav.addObject("allSnippets", snippetService.getAllSnippets());
		return mav;
	}

	// list

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
		return new ModelAndView("error"); // TODO: make error page
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

	// signup

	@GetMapping("/signup")
	public ModelAndView signupHandler(HttpServletRequest request) {
		request.getSession().setAttribute("message", null);
		return new ModelAndView("signup");
	}

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
		return new ModelAndView("error"); // TODO: make error page
	}

	@PostMapping("/uploadSnippet")
	public String uploadSnippet(HttpServletRequest request) {
		String snippetText = request.getParameter("snippetText");
		User user = userService.getUser(request.getParameter("snippetAuthor"));
		if (snippetText == null || snippetText.isBlank()) {
			if (request.getSession().getAttribute("editSnippet") != null)
				return "redirect:/profile/" + user.getUsername();
			request.getSession().setAttribute("message", "Snippet cannot be empty.");
			return "redirect:/createSnippet";
		}
		request.getSession().setAttribute("message", null);
		if (request.getSession().getAttribute("editSnippet") != null) {
			request.getSession().setAttribute("editSnippet", null);
			snippetService.editSnippet(Integer.parseInt(request.getParameter("snippetId")), snippetText);
			return "redirect:/profile/" + user.getUsername();
		}
		userService.addPublishedSnippet(user.getUsername(), new Snippet(user, snippetText));
		return "redirect:/profile/" + user.getUsername();
	}

	@PostMapping("/editSnippet")
	public ModelAndView editSnippet(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("createSnippet");
		Snippet snippet = snippetService.readSnippet(Integer.parseInt(request.getParameter("snippetId")));
		mav.addObject("snippetText", snippet.getSnippetText());
		mav.addObject("snippetId", snippet.getSnippetId());
		request.getSession().setAttribute("editSnippet", "true");
		return mav;
	}

	@RequestMapping("/deleteSnippet")
	public String deleteSnippet(HttpServletRequest request) {
		User user = userService.getUser(request.getParameter("username"));
		Snippet snippet = snippetService.readSnippet(Integer.parseInt(request.getParameter("snippetId")));
		userService.deletePublishedSnippet(user.getUsername(), snippet);
		return "redirect:/profile/" + user.getUsername();
	}

	@RequestMapping("/addCommentToSnippet")
	public String addCommentToSnippet(HttpServletRequest request) {
		int snippetId = Integer.parseInt(request.getParameter("snippetId"));
		if (request.getParameter("commentText").isBlank()) {
			request.getSession().setAttribute("message", "Comment cannot be blank.");
			return "redirect:/snippet/" + snippetId;
		}
		request.getSession().setAttribute("message", null);
		User commentAuthor = userService.getUser(request.getParameter("commentAuthor"));
		snippetService.addComment(snippetId, new Comment(commentAuthor, request.getParameter("commentText")));
		return "redirect:/snippet/" + snippetId;
	}

	@PostMapping("/editSnippetComment")
	public String editSnippetComment(HttpServletRequest request) {
		int snippetId = Integer.parseInt(request.getParameter("snippetId"));
		if (request.getParameter("commentText").isBlank()) {
			request.getSession().setAttribute("message", "Comment cannot be blank.");
			return "redirect:/snippet/" + snippetId;
		}
		request.getSession().setAttribute("message", null);
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		commentService.editComment(commentId, request.getParameter("commentText"));
		return "redirect:/snippet/" + snippetId;
	}

	@PostMapping("/deleteSnippetComment")
	public String deleteSnippetComment(HttpServletRequest request) {
		int snippetId = Integer.parseInt(request.getParameter("snippetId"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		snippetService.deleteComment(snippetId, commentService.readComment(commentId));
		return "redirect:/snippet/" + snippetId;
	}

	@RequestMapping("/likeSnippetComment")
	public String likeSnippetComment(HttpServletRequest request) {
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int snippetId = Integer.parseInt(request.getParameter("snippetId"));
		commentService.likeComment(commentId);
		return "redirect:/snippet/" + snippetId;
	}

	@RequestMapping("/dislikeSnippetComment")
	public String dislikeSnippetComment(HttpServletRequest request) {
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int snippetId = Integer.parseInt(request.getParameter("snippetId"));
		commentService.dislikeComment(commentId);
		return "redirect:/snippet/" + snippetId;
	}

	// stories

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
		return new ModelAndView("error"); // TODO: make error page
	}

	@PostMapping("/uploadStory")
	public String uploadStory(HttpServletRequest request) {
		String storyTitle = request.getParameter("storyTitle");
		String storyTextString = request.getParameter("storyText");
		User user = userService.getUser(request.getParameter("storyAuthor"));
		if (storyTitle == null || storyTitle.isBlank()) {
			request.getSession().setAttribute("message", "Story title cannot be empty.");
			return "redirect:/createStory";
		} else if (storyTitle.length() > 50) {
			request.getSession().setAttribute("message", "Story title is too long.");
			return "redirect:/createStory";
		} else if (storyTitle.matches("[^A-Za-z0-9]")) {
			request.getSession().setAttribute("message", "Story title cannot contain special characters.");
			return "redirect:/createStory";
		} else if (storyTextString == null || storyTextString.isBlank()) {
			if (request.getSession().getAttribute("editStory") != null)
				return "redirect:/profile/" + user.getUsername();
			request.getSession().setAttribute("message", "Story cannot be empty.");
			return "redirect:/createStory";
		}
		request.getSession().setAttribute("message", null);
		List<Snippet> storyText = new ArrayList<Snippet>();
		Arrays.asList(storyTextString.split(",")).forEach(s -> {
			storyText.add(snippetService.readSnippet(Integer.parseInt(s)));
		});
		if (request.getSession().getAttribute("editStory") != null) {
			request.getSession().setAttribute("editStory", null);
			storyService.editStory(storyTitle, storyText);
			return "redirect:/profile/" + user.getUsername();
		}
		Story toAdd = new Story(storyTitle, user, storyText);
		userService.addPublishedStory(user.getUsername(), toAdd);
		return "redirect:/profile/" + user.getUsername();
	}

	@PostMapping("/editStory")
	public ModelAndView editStory(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("createStory");
		Story story = storyService.readStory(request.getParameter("storyTitle"));
		List<Snippet> restOfSnippets = snippetService.getAllSnippets();
		restOfSnippets.removeAll(story.getStoryText());
		mav.addObject("storySnippets", story.getStoryText());
		mav.addObject("allSnippets", restOfSnippets);
		mav.addObject("storyTitle", story.getStoryTitle());
		request.getSession().setAttribute("editStory", "true");
		return mav;
	}

	@RequestMapping("/deleteStory")
	public String deleteStory(HttpServletRequest request) {
		User user = userService.getUser(request.getParameter("username"));
		Story story = storyService.readStory(request.getParameter("storyTitle"));
		userService.deletePublishedStory(user.getUsername(), story);
		return "redirect:/profile/" + user.getUsername();
	}

	@RequestMapping("/addCommentToStory")
	public String addCommentToStory(HttpServletRequest request) {
		String storyTitle = request.getParameter("storyTitle");
		if (request.getParameter("commentText").isBlank()) {
			request.getSession().setAttribute("message", "Comment cannot be blank.");
			return "redirect:/story/" + storyTitle;
		}
		request.getSession().setAttribute("message", null);
		User commentAuthor = userService.getUser(request.getParameter("commentAuthor"));
		storyService.addComment(storyTitle, new Comment(commentAuthor, request.getParameter("commentText")));
		return "redirect:/story/" + storyTitle;
	}

	@RequestMapping("/likeStory")
	public String likeStory(HttpServletRequest request) {
		String storyTitle = request.getParameter("storyTitle");
		String username = request.getParameter("username");
		userService.addFavoriteStory(username, storyService.readStory(storyTitle));
		storyService.likeStory(storyTitle);
		return "redirect:/story/" + storyTitle;
	}

	@RequestMapping("/dislikeStory")
	public String dislikeStory(HttpServletRequest request) {
		String storyTitle = request.getParameter("storyTitle");
		String username = request.getParameter("username");
		userService.deleteFavoriteStory(username, storyService.readStory(storyTitle));
		storyService.dislikeStory(storyTitle);
		return "redirect:/story/" + storyTitle;
	}

	@PostMapping("/editStoryComment")
	public String editStoryComment(HttpServletRequest request) {
		String storyTitle = request.getParameter("storyTitle");
		if (request.getParameter("commentText").isBlank()) {
			request.getSession().setAttribute("message", "Comment cannot be blank.");
			return "redirect:/story/" + storyTitle;
		}
		request.getSession().setAttribute("message", null);
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		commentService.editComment(commentId, request.getParameter("commentText"));
		return "redirect:/story/" + storyTitle;
	}

	@PostMapping("/deleteStoryComment")
	public String deleteStoryComment(HttpServletRequest request) {
		String storyTitle = request.getParameter("storyTitle");
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		storyService.deleteComment(storyTitle, commentService.readComment(commentId));
		return "redirect:/story/" + storyTitle;
	}

	@RequestMapping("/likeStoryComment")
	public String likeStoryComment(HttpServletRequest request) {
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String storyTitle = request.getParameter("storyTitle");
		commentService.likeComment(commentId);
		return "redirect:/story/" + storyTitle;
	}

	@RequestMapping("/dislikeStoryComment")
	public String dislikeStoryComment(HttpServletRequest request) {
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String storyTitle = request.getParameter("storyTitle");
		commentService.dislikeComment(commentId);
		return "redirect:/story/" + storyTitle;
	}
}
