package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

@SpringBootTest
class SnippetServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
	StoryService storyService;
	
	@Autowired
	SnippetService snippetService;
	
	@Autowired
	CommentService commentService;

	@Test
	@Transactional
	void testAddSnippet() {
		User user = userService.getUser("user1");
		assertTrue(snippetService.addSnippet(new Snippet(user, "testSnippet")));
	}

	@Test
	@Transactional
	void testReadSnippet() {
		assertNotNull(snippetService.readSnippet(11));
	}

	@Test
	@Transactional
	void testEditSnippet() {
		assertTrue(snippetService.editSnippet(11, "testSnippet"));
	}

	@Test
	@Transactional
	void testEditSnippetAuthor() {
		User user = userService.getUser("user2");
		assertTrue(snippetService.editSnippetAuthor(11, user));
	}

	@Test
	@Transactional
	void testAddStory() {
		Story story = storyService.readStory("user1story1");
		assertTrue(snippetService.addStory(15, story));
	}

	@Test
	@Transactional
	void testDeleteStory() {
		Story story = storyService.readStory("user1story1");
		assertTrue(snippetService.deleteStory(11, story));
	}

	@Test
	@Transactional
	void testAddComment() {
		User user = userService.getUser("user1");
		assertTrue(snippetService.addComment(11, new Comment(user, "testComment")));
	}

	@Test
	@Transactional
	void testDeleteComment() {
		Comment toDelete = commentService.readComment(11);
		assertTrue(snippetService.deleteComment(11, toDelete));
	}

	@Test
	@Transactional
	void testDeleteSnippet() {
		assertTrue(snippetService.deleteSnippet(11));
	}

	@Test
	@Transactional
	void testGetAllSnippets() {
		assertFalse(snippetService.getAllSnippets().isEmpty());
	}

}
