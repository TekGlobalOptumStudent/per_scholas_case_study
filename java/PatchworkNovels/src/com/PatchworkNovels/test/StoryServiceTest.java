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
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

class StoryServiceTest extends AbstractDAO {

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
		assertTrue(storyService.addComment(11, new Comment(user, "testComment")));
	}

	@Test
	void testDeleteComment() {
		Comment toDelete = commentService.readComment(17);
		storyService.getAllStories().forEach(s -> s.getStoryComments().remove(toDelete));
		assertTrue(storyService.deleteComment(17, toDelete));
	}

	@Test
	void testDeleteStory() {
		Story toDelete = storyService.readStory(16);
		snippetService.getAllSnippets().forEach(s -> snippetService.deleteStory(s.getSnippetId(), toDelete));
		userService.getAllUsers().forEach(u -> {
			userService.deleteFavoriteStory(u.getUserId(), toDelete);
			userService.deletePublishedStory(u.getUserId(), toDelete);
		});
		assertTrue(storyService.deleteStory(16));
	}

	@Test
	void testGetAllStories() {
		assertFalse(storyService.getAllStories().isEmpty());
	}

}
