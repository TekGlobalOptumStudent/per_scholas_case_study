package com.PatchworkNovels.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

@Controller
public class StoryController {

	@Autowired
	SnippetService snippetService;

	@Autowired
	StoryService storyService;

	@Autowired
	UserService userService;
	
	@PostMapping("/uploadStory")
	public String uploadStory(HttpServletRequest request) {
		String storyTitle = request.getParameter("storyTitle");
		String storyTextString = request.getParameter("storyText");
		User user = userService.getUser(request.getParameter("storyAuthor"));
		if (storyTitle == null || storyTitle.isBlank()) {
			request.getSession().setAttribute("story_message", "Story title cannot be empty.");
			return "redirect:/createStory";
		} else if(storyService.checkStoryTitle(storyTitle) && request.getSession().getAttribute("editStory") == null) {
			request.getSession().setAttribute("story_message", "That story title is taken.");
			return "redirect:/createStory";
		} else if (storyTitle.length() > 50) {
			request.getSession().setAttribute("story_message", "Story title is too long.");
			return "redirect:/createStory";
		} else if (storyTitle.matches("[^A-Za-z0-9]")) {
			request.getSession().setAttribute("story_message", "Story title cannot contain special characters.");
			return "redirect:/createStory";
		} else if (storyTextString == null || storyTextString.isBlank()) {
			if (request.getSession().getAttribute("editStory") != null) {
				request.getSession().setAttribute("editStory", null);
				return "redirect:/profile/" + user.getUsername();
			}
			request.getSession().setAttribute("story_message", "Story cannot be empty.");
			return "redirect:/createStory";
		}
		List<Snippet> storyText = new ArrayList<Snippet>();
		Arrays.asList(storyTextString.split(",")).forEach(s -> {
			storyText.add(snippetService.readSnippet(Integer.parseInt(s)));
		});
		if (request.getSession().getAttribute("editStory") != null) {
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
	
}
