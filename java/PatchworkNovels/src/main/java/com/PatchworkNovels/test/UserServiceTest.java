package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class UserServiceTest extends AbstractDAO {

	@Autowired(required = true)
	CommentService commentService;
	
	@Autowired(required = true)
	SnippetService snippetService;
	
	@Autowired(required = true)
	StoryService storyService;
	
	@Autowired(required = true)
	UserService userService;
	
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
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Transactional
	void testAddUser() {
		User toAdd = new User("testUsername", "testPassword");
		assertTrue(userService.addUser(toAdd));
	}

	@Test
	@Transactional
	void testGetUser() {
		User expected = new User("user1", "pass1");
		User actual = userService.getUser(11);
		assertEquals(expected.getUsername(), actual.getUsername());
	}

	@Test
	@Transactional
	void testEditUser() {
		assertTrue(userService.editUser(12, "testUsername", "testPassword"));
	}

	@Test
	@Transactional
	void testAddPublishedStory() {
		User user = userService.getUser(11);
		assertTrue(userService.addPublishedStory(11, new Story(user, "testPublishedStory")));
	}

	@Test
	@Transactional
	void testDeletePublishedStory() {
		User user = userService.getUser(11);
		Story toRemove = new Story(user, "testPublishedStoryToRemove");
		userService.addPublishedStory(user.getUserId(), toRemove);
		assertTrue(userService.deletePublishedStory(user.getUserId(), toRemove));
	}

	@Test
	@Transactional
	void testAddPublishedSnippet() {
		User user = userService.getUser(11);
		assertTrue(userService.addPublishedSnippet(11, new Snippet(user, "testPublishedSnippet")));
	}

	@Test
	@Transactional
	void testDeletePublishedSnippet() {
		User user = userService.getUser(16);
		Snippet toRemove = new Snippet(user, "testPublishedSnippetToRemove");
		userService.addPublishedSnippet(16, toRemove);
		assertTrue(userService.deletePublishedSnippet(16, toRemove));
	}

	@Test
	@Transactional
	void testAddFavoriteStory() {
		assertTrue(userService.addFavoriteStory(11, storyService.readStory(11)));
	}

	@Test
	@Transactional
	void testDeleteFavoriteStory() {
		Story toRemove = storyService.readStory(12);
		userService.addFavoriteStory(11, toRemove);
		assertTrue(userService.deleteFavoriteStory(11, toRemove));
	}

	@Test
	@Transactional
	void testDeleteUser() {
		/*
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
		*/
		assertTrue(userService.deleteUser(13));
	}

	@Test
	@Transactional
	void testGetAllUsers() {
		assertFalse(userService.getAllUsers().isEmpty());
	}

	@Test
	@Transactional
	void testValidateUser() {
		assertTrue(userService.validateUser("user1", "pass1"));
	}

}
