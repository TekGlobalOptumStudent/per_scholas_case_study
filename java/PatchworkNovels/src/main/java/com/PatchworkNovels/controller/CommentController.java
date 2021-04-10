package com.PatchworkNovels.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;

	@Autowired
	SnippetService snippetService;

	@Autowired
	StoryService storyService;

	@Autowired
	UserService userService;
	
	// snippet comments
	
	@RequestMapping("/addCommentToSnippet")
	public String addCommentToSnippet(HttpServletRequest request) {
		int snippetId = Integer.parseInt(request.getParameter("snippetId"));
		if (request.getParameter("commentText").isBlank()) {
			request.getSession().setAttribute("comment_message", "Comment cannot be blank.");
			return "redirect:/snippet/" + snippetId;
		}
		User commentAuthor = userService.getUser(request.getParameter("commentAuthor"));
		snippetService.addComment(snippetId, new Comment(commentAuthor, request.getParameter("commentText")));
		return "redirect:/snippet/" + snippetId;
	}

	@PostMapping("/editSnippetComment")
	public String editSnippetComment(HttpServletRequest request) {
		int snippetId = Integer.parseInt(request.getParameter("snippetId"));
		if (request.getParameter("commentText").isBlank()) {
			request.getSession().setAttribute("comment_message", "Comment cannot be blank.");
			return "redirect:/snippet/" + snippetId;
		}
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
	
	// story comments

	@RequestMapping("/addCommentToStory")
	public String addCommentToStory(HttpServletRequest request) {
		String storyTitle = request.getParameter("storyTitle");
		if (request.getParameter("commentText").isBlank()) {
			request.getSession().setAttribute("comment_message", "Comment cannot be blank.");
			return "redirect:/story/" + storyTitle;
		}
		User commentAuthor = userService.getUser(request.getParameter("commentAuthor"));
		storyService.addComment(storyTitle, new Comment(commentAuthor, request.getParameter("commentText")));
		return "redirect:/story/" + storyTitle;
	}

	@PostMapping("/editStoryComment")
	public String editStoryComment(HttpServletRequest request) {
		String storyTitle = request.getParameter("storyTitle");
		if (request.getParameter("commentText").isBlank()) {
			request.getSession().setAttribute("comment_message", "Comment cannot be blank.");
			return "redirect:/story/" + storyTitle;
		}
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
