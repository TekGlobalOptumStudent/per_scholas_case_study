package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.UserService;

class UserServiceTest extends AbstractDAO {

	static UserService userService = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dropTable("User");
		userService = new UserService();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		userService = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		userService.createTable();
	}

	@AfterEach
	void tearDown() throws Exception {
		dropTable("User");
	}

	@Test
	void testAddUser() {
		User toAdd = new User("testUsername", "testPassword");
		assertTrue(userService.addUser(toAdd));
	}

	@Test
	void testGetUser() {
		User expected = new User("user1", "pass1");
		try {
			expected.setDateJoined(new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"));
		} catch(Exception e) {
			fail("ParseException");
		}
		User actual = userService.getUser(1);
		assertEquals(expected.getUsername(), actual.getUsername());
	}

	@Test
	void testEditUser() {
		fail("Not yet implemented");
	}

	@Test
	void testAddPublishedStory() {
		assertTrue(userService.addPublishedStory(1, new Story()));
	}

	@Test
	void testDeletePublishedStory() {
		Story toRemove = new Story();
		userService.addPublishedStory(1, toRemove);
		assertTrue(userService.deletePublishedStory(1, 1));
	}

	@Test
	void testAddPublishedSnippet() {
		assertTrue(userService.addPublishedSnippet(1, new Snippet()));
	}

	@Test
	void testDeletePublishedSnippet() {
		Snippet toRemove = new Snippet();
		userService.addPublishedSnippet(1, toRemove);
		assertTrue(userService.deletePublishedSnippet(1, 1));
	}

	@Test
	void testAddFavoriteStory() {
		assertTrue(userService.addFavoriteStory(1, new Story()));
	}

	@Test
	void testDeleteFavoriteStory() {
		Story toRemove = new Story();
		userService.addPublishedStory(1, toRemove);
		assertTrue(userService.deleteFavoriteStory(1, 1));
	}

	@Test
	void testDeleteUser() {
		assertTrue(userService.deleteUser(1));
	}

	@Test
	void testGetAllUsers() {
		assertTrue(!userService.getAllUsers().isEmpty());
	}

	@Test
	void testValidateUser() {
		assertTrue(userService.validateUser("test1", "pass1"));
	}

}
