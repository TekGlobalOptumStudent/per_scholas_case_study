package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

	private CommentService commentService = null;
	private SnippetService snippetService = null;
	private StoryService storyService = null;
	private UserService userService = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		startJDBC(1, "root", "password");
		dropDatabase();
		createDatabase();
		createTables();
		runSQLFile("user.sql");
		runSQLFile("story.sql");
		runSQLFile("snippet.sql");
		runSQLFile("comment.sql");
		runSQLFile("relations.sql");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		stopJDBC();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		commentService = new CommentService();
		snippetService = new SnippetService();
		storyService = new StoryService();
		userService = new UserService();
	}
	
	@AfterEach
	void tearDown() throws Exception {
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
		assertTrue(snippetService.addComment(11, new Comment(user, "testComment")));
	}

	@Test
	void testDeleteComment() {
		Comment toDelete = commentService.readComment(13);
		snippetService.getAllSnippets().forEach(s -> s.getSnippetComments().remove(toDelete));
		assertTrue(snippetService.deleteComment(13, toDelete));
	}

	@Test
	void testDeleteSnippet() {
		Snippet toDelete = snippetService.readSnippet(14);
		userService.getAllUsers().forEach(u -> userService.deletePublishedSnippet(u.getUserId(), toDelete));
		assertTrue(snippetService.deleteSnippet(14));
	}

	@Test
	void testGetAllSnippets() {
		assertTrue(!snippetService.getAllSnippets().isEmpty());
	}

}
