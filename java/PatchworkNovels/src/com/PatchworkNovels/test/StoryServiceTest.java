package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

class StoryServiceTest extends AbstractDAO {

	static StoryService storyService = null;
	static UserService userService = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		createDatabase(1, "root", "password");
		truncateTable("Story");
		storyService = new StoryService();
		userService = new UserService();
		userService.createTable();
		runSQLFile("user.sql");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		storyService = null;
		userService = null;
		truncateTable("User");
	}

	@BeforeEach
	void setUp() throws Exception {
		storyService.createTable();
		runSQLFile("story.sql");
	}

	@AfterEach
	void tearDown() throws Exception {
		truncateTable("Story");
	}

	@Test
	void testAddStory() {
		Story toAdd = new Story();
		toAdd.setStoryAuthor(userService.getUser(11));
		assertTrue(storyService.addStory(toAdd));
	}

	@Test
	void testReadStory() {
		assertNotNull(storyService.readStory(11));
	}

	@Test
	void testEditStory() {
		Snippet snippet = new Snippet(userService.getUser(11), "testSnippetText");
		assertTrue(storyService.editStory(11, Arrays.asList(snippet)));
	}

	@Test
	void testRenameStory() {
		assertTrue(storyService.renameStory(11, "testStoryName"));
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
		assertTrue(storyService.addComment(user.getUserId(), new Comment(user, "testComment")));
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
