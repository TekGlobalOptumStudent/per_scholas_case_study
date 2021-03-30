package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

class StoryServiceTest extends AbstractDAO {

	static CommentService commentService = null;
	static SnippetService snippetService = null;
	static StoryService storyService = null;
	static UserService userService = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		createDatabase(1, "root", "password");
		commentService = new CommentService();
		snippetService = new SnippetService();
		storyService = new StoryService();
		userService = new UserService();
		createTables();
		runSQLFile("user.sql");
		runSQLFile("story.sql");
		runSQLFile("snippet.sql");
		runSQLFile("comment.sql");
		runSQLFile("relations.sql");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		commentService = null;
		snippetService = null;
		storyService = null;
		userService = null;
	}

	@Test
	void testAddStory() {
		User user = userService.getUser(11);
		Story toAdd = new Story(user, "testStory");
		assertTrue(storyService.addStory(toAdd));
	}

	@Test
	void testReadStory() {
		assertNotNull(storyService.readStory(11));
	}

	@Test
	void testEditStory() {
		assertTrue(storyService.editStory(12, Arrays.asList(snippetService.readSnippet(12))));
	}

	@Test
	void testRenameStory() {
		assertTrue(storyService.renameStory(13, "testStoryName"));
	}

	@Test
	void testLikeStory() {
		assertTrue(storyService.likeStory(11));
	}

	@Test
	void testDislikeStory() {
		assertTrue(storyService.dislikeStory(11));
	}

	@Test
	void testAddComment() {
		User user = userService.getUser(11);
		assertTrue(storyService.addComment(13, new Comment(user, "testComment")));
	}

	@Test
	void testDeleteComment() {
		User user = userService.getUser(11);
		Comment toRemove = new Comment(user, "testCommentToRemove");
		storyService.addComment(user.getUserId(), toRemove);
		assertTrue(storyService.deleteComment(11, toRemove));
	}

	@Test
	void testDeleteStory() {
		assertTrue(storyService.deleteStory(11));
	}

	@Test
	void testGetAllStories() {
		assertTrue(!storyService.getAllStories().isEmpty());
	}

}
