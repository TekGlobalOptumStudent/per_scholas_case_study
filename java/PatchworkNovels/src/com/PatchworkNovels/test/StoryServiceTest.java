package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

class StoryServiceTest extends AbstractDAO {

	static StoryService storyService = null;
	static UserService userService = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dropTable("Story");
		storyService = new StoryService();
		userService = new UserService();
		userService.createTable();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		storyService = null;
		userService = null;
		dropTable("User");
	}

	@BeforeEach
	void setUp() throws Exception {
		storyService.createTable();
	}

	@AfterEach
	void tearDown() throws Exception {
		dropTable("Story");
	}

	@Test
	void testAddStory() {
		Story toAdd = new Story();
		toAdd.setStoryAuthor(userService.getUser(1));
		assertTrue(storyService.addStory(toAdd));
	}

	@Test
	void testReadStory() {
		assertNotNull(storyService.readStory(1));
	}

	@Test
	void testEditStory() {
		Snippet snippet = new Snippet(userService.getUser(1), "testSnippetText");
		assertTrue(storyService.editStory(1, Arrays.asList(snippet)));
	}

	@Test
	void testRenameStory() {
		assertTrue(storyService.renameStory(1, "testStoryName"));
	}

	@Test
	void testLikeStory() {
		assertTrue(storyService.likeStory(1));
	}

	@Test
	void testDislikeStory() {
		assertTrue(storyService.dislikeStory(1));
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
	void testDeleteStory() {
		assertTrue(storyService.deleteStory(1));
	}

	@Test
	void testGetAllStories() {
		assertTrue(!storyService.getAllStories().isEmpty());
	}

}
