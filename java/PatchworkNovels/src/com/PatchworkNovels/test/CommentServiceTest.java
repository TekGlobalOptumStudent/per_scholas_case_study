package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

class CommentServiceTest extends AbstractDAO {

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
	void testAddComment() {
		Comment toAdd = new Comment(userService.getUser(11) ,"testComment");
		assertTrue(commentService.addComment(toAdd));
	}

	@Test
	void testReadComment() {
		Comment expected = new Comment(userService.getUser(11), "user1comment1");
		Comment actual = commentService.readComment(11);
		assertEquals(expected.getCommentText(), actual.getCommentText());
	}

	@Test
	void testEditComment() {
		assertTrue(commentService.editComment(14, "testCommentText"));
	}

	@Test
	void testLikeComment() {
		assertTrue(commentService.likeComment(12));
	}

	@Test
	void testDislikeComment() {
		assertTrue(commentService.dislikeComment(12));
	}

	@Test
	void testDeleteComment() {
		Comment toDelete = commentService.readComment(13);
		snippetService.getAllSnippets().forEach(s -> snippetService.deleteComment(s.getSnippetId(), toDelete));
		storyService.getAllStories().forEach(s -> storyService.deleteComment(s.getStoryId(), toDelete));
		assertTrue(commentService.deleteComment(13));
	}

	@Test
	void testGetAllComments() {
		assertTrue(!commentService.getAllComments().isEmpty());
	}
	
	@Test
	void testUpdateAllComments() {
		assertTrue(commentService.updateAllComments(19));
	}

}
