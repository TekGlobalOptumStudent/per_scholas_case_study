package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

class SnippetServiceTest extends AbstractDAO {

	static CommentService commentService = null;
	static SnippetService snippetService = null;
	static StoryService storyService = null;
	static UserService userService = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		createDatabase(1, "root", "password");
		createTables();
		runSQLFile("user.sql");
		runSQLFile("story.sql");
		runSQLFile("snippet.sql");
		runSQLFile("comment.sql");
		runSQLFile("relations.sql");
		commentService = new CommentService();
		snippetService = new SnippetService();
		storyService = new StoryService();
		userService = new UserService();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		commentService = null;
		snippetService = null;
		storyService = null;
		userService = null;
	}

	@Test
	void testAddSnippet() {
		User user = userService.getUser(11);
		Snippet toAdd = new Snippet(user, "testSnippet");
		assertTrue(snippetService.addSnippet(toAdd));
	}

	@Test
	void testReadSnippet() {
		assertNotNull(snippetService.readSnippet(11));
	}

	@Test
	void testEditSnippet() {
		assertTrue(snippetService.editSnippet(12, "testSnippetText"));
	}

	@Test
	void testAddComment() {
		User user = userService.getUser(11);
		assertTrue(snippetService.addComment(13, new Comment(user, "testComment")));
	}

	@Test
	void testDeleteComment() {
		User user = userService.getUser(11);
		Comment toRemove = new Comment(user, "testCommentToRemove");
		snippetService.addComment(user.getUserId(), toRemove);
		assertTrue(snippetService.deleteComment(11, toRemove));
	}

	@Test
	void testDeleteSnippet() {
		assertTrue(snippetService.deleteSnippet(11));
	}

	@Test
	void testGetAllSnippets() {
		assertTrue(!snippetService.getAllSnippets().isEmpty());
	}

}
