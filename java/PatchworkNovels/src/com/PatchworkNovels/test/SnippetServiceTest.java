package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Snippet;
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
		Snippet toAdd = new Snippet();
		toAdd.setSnippetAuthor(userService.getUser(1));
		assertTrue(snippetService.addSnippet(toAdd));
	}

	@Test
	void testReadSnippet() {
		assertNotNull(snippetService.readSnippet(1));
	}

	@Test
	void testEditSnippet() {
		assertTrue(snippetService.editSnippet(1, "testSnippetText"));
	}

	@Test
	void testAddComment() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteComment() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteSnippet() {
		assertTrue(snippetService.deleteSnippet(1));
	}

	@Test
	void testGetAllSnippets() {
		assertTrue(!snippetService.getAllSnippets().isEmpty());
	}

}
