package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

class UserServiceTest extends AbstractDAO {

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
		User user = userService.getUser(11);
		Snippet toRemove = new Snippet(user, "testPublishedSnippetToRemove");
		userService.addPublishedSnippet(user.getUserId(), toRemove);
		assertTrue(userService.deletePublishedSnippet(user.getUserId(), toRemove));
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
		commentService.updateAllComments(toDelete);
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
		assertTrue(!userService.getAllUsers().isEmpty());
	}

	@Test
	void testValidateUser() {
		assertTrue(userService.validateUser("user1", "pass1"));
	}

}
