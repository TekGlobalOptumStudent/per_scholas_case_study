package com.PatchworkNovels.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

public class SnippetController {

	@Autowired
	CommentService commentService;

	@Autowired
	SnippetService snippetService;

	@Autowired
	StoryService storyService;

	@Autowired
	UserService userService;
	
}
