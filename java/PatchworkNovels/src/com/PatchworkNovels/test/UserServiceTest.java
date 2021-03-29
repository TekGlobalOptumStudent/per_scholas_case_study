package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

class UserServiceTest extends AbstractDAO {

	static SnippetService snippetService = null;
	static StoryService storyService = null;
	static UserService userService = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		createDatabase(1, "root", "password");
		snippetService = new SnippetService();
		storyService = new StoryService();
		userService = new UserService();
		snippetService.createTable();
		storyService.createTable();
		userService.createTable();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		snippetService = null;
		storyService = null;
		userService = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		runSQLFile("user.sql");
		runSQLFile("story.sql");
		runSQLFile("snippet.sql");
	}

	@AfterEach
	void tearDown() throws Exception {
		truncateTable("User");
		truncateTable("Story");
		truncateTable("Snippet");
	}

	@Test
	void testAddUser() {
		User toAdd = new User("testUsername", "testPassword");
		assertTrue(userService.addUser(toAdd));
	}

	@Test
	void testGetUser() {
		User expected = new User("user1", "pass1");
		User actual = userService.getUser(11);
		assertEquals(expected.getUsername(), actual.getUsername());
	}

	@Test
	void testEditUser() {
		assertTrue(userService.editUser(11, "testUsername", "testPassword"));
	}

	@Test
	void testAddPublishedStory() {
		User user = userService.getUser(11);
		assertTrue(userService.addPublishedStory(11, new Story(user, "testPublishedStory")));
	}

	@Test
	void testDeletePublishedStory() {
		User user = userService.getUser(11);
		Story toRemove = new Story(user, "testPublishedStoryToRemove");
		userService.addPublishedStory(11, toRemove);
		assertTrue(userService.deletePublishedStory(11, toRemove));
	}

	@Test
	void testAddPublishedSnippet() {
		User user = userService.getUser(11);
		assertTrue(userService.addPublishedSnippet(11, new Snippet(user, "testPublishedSnippet")));
	}

	@Test
	void testDeletePublishedSnippet() {
		User user = userService.getUser(11);
		Snippet toRemove = new Snippet(user, "testPublishedSnippetToRemove");
		userService.addPublishedSnippet(11, toRemove);
		assertTrue(userService.deletePublishedSnippet(11, toRemove));
	}

	@Test
	void testAddFavoriteStory() {
		User user = userService.getUser(11);
		assertTrue(userService.addFavoriteStory(11, new Story(user, "testFavoriteStory")));
	}

	@Test
	void testDeleteFavoriteStory() {
		User user = userService.getUser(11);
		Story toRemove = new Story(user, "testFavoriteStoryToRemove");
		userService.addPublishedStory(11, toRemove);
		assertTrue(userService.deleteFavoriteStory(11, toRemove));
	}

	@Test
	void testDeleteUser() {
		assertTrue(userService.deleteUser(11));
	}

	@Test
	void testGetAllUsers() {
		assertTrue(!userService.getAllUsers().isEmpty());
	}

	@Test
	void testValidateUser() {
		assertTrue(userService.validateUser("user1", "pass1"));
	}

}
