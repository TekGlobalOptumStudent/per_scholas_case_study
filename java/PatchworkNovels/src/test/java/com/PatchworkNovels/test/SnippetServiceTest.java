package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentServiceOld;
import com.PatchworkNovels.service.SnippetServiceOld;
import com.PatchworkNovels.service.UserServiceOld;

class SnippetServiceTest extends AbstractDAO {

	private CommentServiceOld commentService = null;
	private SnippetServiceOld snippetService = null;
	private UserServiceOld userService = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		startJDBC(1, "root", "password");
		dropDatabase();
		createDatabase();
		createTables();
		TimeUnit.SECONDS.sleep(5);
		runSQLFile("user.sql");
		runSQLFile("story.sql");
		runSQLFile("snippet.sql");
		runSQLFile("comment.sql");
		runSQLFile("relations.sql");
		TimeUnit.SECONDS.sleep(5);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		stopJDBC();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		commentService = new CommentServiceOld();
		snippetService = new SnippetServiceOld();
		userService = new UserServiceOld();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		commentService = null;
		snippetService = null;
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
		assertFalse(snippetService.getAllSnippets().isEmpty());
	}

}
