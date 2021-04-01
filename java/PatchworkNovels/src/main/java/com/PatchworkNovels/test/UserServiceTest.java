package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentServiceOld;
import com.PatchworkNovels.service.SnippetServiceOld;
import com.PatchworkNovels.service.StoryServiceOld;
import com.PatchworkNovels.service.UserServiceOld;

class UserServiceTest extends AbstractDAO {

	private CommentServiceOld commentService = null;
	private SnippetServiceOld snippetService = null;
	private StoryServiceOld storyService = null;
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
		storyService = new StoryServiceOld();
		userService = new UserServiceOld();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		commentService = null;
		snippetService = null;
		storyService = null;
		userService = null;
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
		assertTrue(userService.editUser(12, "testUsername", "testPassword"));
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
		userService.addPublishedStory(user.getUserId(), toRemove);
		assertTrue(userService.deletePublishedStory(user.getUserId(), toRemove));
	}

	@Test
	void testAddPublishedSnippet() {
		User user = userService.getUser(11);
		assertTrue(userService.addPublishedSnippet(11, new Snippet(user, "testPublishedSnippet")));
	}

	@Test
	void testDeletePublishedSnippet() {
		User user = userService.getUser(16);
		Snippet toRemove = new Snippet(user, "testPublishedSnippetToRemove");
		userService.addPublishedSnippet(16, toRemove);
		assertTrue(userService.deletePublishedSnippet(16, toRemove));
	}

	@Test
	void testAddFavoriteStory() {
		assertTrue(userService.addFavoriteStory(11, storyService.readStory(11)));
	}

	@Test
	void testDeleteFavoriteStory() {
		Story toRemove = storyService.readStory(12);
		userService.addFavoriteStory(11, toRemove);
		assertTrue(userService.deleteFavoriteStory(11, toRemove));
	}

	@Test
	void testDeleteUser() {
		User toDelete = userService.getUser(13);
		commentService.updateAllComments(toDelete.getUserId());
		toDelete.getPublishedSnippets().forEach(ps -> {
			userService.deletePublishedSnippet(toDelete.getUserId(), ps);
			snippetService.deleteSnippet(ps.getSnippetId());
		});
		toDelete.getPublishedStories().forEach(ps -> {
			userService.deletePublishedStory(toDelete.getUserId(), ps);
			storyService.deleteStory(ps.getStoryId());
		});
		assertTrue(userService.deleteUser(13));
	}

	@Test
	void testGetAllUsers() {
		assertFalse(userService.getAllUsers().isEmpty());
	}

	@Test
	void testValidateUser() {
		assertTrue(userService.validateUser("user1", "pass1"));
	}

}
