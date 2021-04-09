package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

@SpringBootTest
class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
	StoryService storyService;
	
	@Autowired
	SnippetService snippetService;
	
	@Autowired
	CommentService commentService;

	@Test
	@Transactional
	void testAddUser() {
		assertTrue(userService.addUser(new User("test", "test")));
	}

	@Test
	@Transactional
	void testGetUser() {
		assertNotNull(userService.getUser("user1"));
	}

	@Test
	@Transactional
	void testEditPassword() {
		assertTrue(userService.editPassword("user1", "newPass1"));
	}

	@Test
	@Transactional
	void testAddProfileImage() {
		assertTrue(userService.addProfileImage("user1", ""));
	}

	@Test
	@Transactional
	void testDeleteProfileImage() {
		assertTrue(userService.deleteProfileImage("user1"));
	}

	@Test
	@Transactional
	void testAddPublishedStory() {
		User user = userService.getUser("user1");
		List<Snippet> storyContent = new ArrayList<Snippet>();
		storyContent.add(snippetService.readSnippet(11));
		storyContent.add(snippetService.readSnippet(12));
		storyContent.add(snippetService.readSnippet(13));
		storyService.addStory(new Story("testStory", user, storyContent));
		Story toAdd = storyService.readStory("testStory");
		assertTrue(userService.addPublishedStory("user1", toAdd));
	}

	@Test
	@Transactional
	void testDeletePublishedStory() {
		Story toDelete = storyService.readStory("user1story1");
		assertTrue(userService.deletePublishedStory("user1", toDelete));
	}

	@Test
	@Transactional
	void testAddPublishedSnippet() {
		User user = userService.getUser("user1");
		assertTrue(userService.addPublishedSnippet("user1", new Snippet(user, "testSnippet")));
	}

	@Test
	@Transactional
	void testDeletePublishedSnippet() {
		Snippet toDelete = snippetService.readSnippet(11);
		assertTrue(userService.deletePublishedSnippet("user1", toDelete));
	}

	@Test
	@Transactional
	void testAddFavoriteStory() {
		Story toAdd = storyService.readStory("user1story1");
		assertTrue(userService.addFavoriteStory("user1", toAdd));
	}

	@Test
	@Transactional
	void testDeleteFavoriteStory() {
		Story toDelete = storyService.readStory("user1story1");
		userService.addFavoriteStory("user1", toDelete);
		assertTrue(userService.deleteFavoriteStory("user1", toDelete));
	}

	@Test
	@Transactional
	void testDeleteUser() {
		assertTrue(userService.deleteUser("user1"));
	}

	@Test
	@Transactional
	void testGetAllUsers() {
		assertFalse(userService.getAllUsers().isEmpty());
	}

	@Test
	@Transactional
	void testCheckUsername() {
		assertTrue(userService.checkUsername("user1"));
	}

	@Test
	@Transactional
	void testValidateUser() {
		assertTrue(userService.validateUser("user1", "pass1"));
	}

}
