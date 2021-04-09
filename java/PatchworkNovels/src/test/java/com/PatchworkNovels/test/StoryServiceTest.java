package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.SnippetService;
import com.PatchworkNovels.service.StoryService;
import com.PatchworkNovels.service.UserService;

@SpringBootTest
class StoryServiceTest {

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
	void testAddStory() {
		User user = userService.getUser("user1");
		List<Snippet> storyContent = new ArrayList<Snippet>();
		storyContent.add(snippetService.readSnippet(11));
		storyContent.add(snippetService.readSnippet(12));
		storyContent.add(snippetService.readSnippet(13));
		assertTrue(storyService.addStory(new Story("testStory", user, storyContent)));
	}

	@Test
	@Transactional
	void testReadStory() {
		assertNotNull(storyService.readStory("user1story1"));
	}

	@Test
	@Transactional
	void testEditStory() {
		List<Snippet> storyContent = new ArrayList<Snippet>();
		storyContent.add(snippetService.readSnippet(14));
		storyContent.add(snippetService.readSnippet(15));
		storyContent.add(snippetService.readSnippet(16));
		assertTrue(storyService.editStory("user1story1", storyContent));
	}

	@Test
	@Transactional
	void testRenameStory() {
		assertTrue(storyService.renameStory("user1story1", "user1title"));
	}

	@Test
	@Transactional
	void testDeleteStoryText() {
		Snippet toDelete = snippetService.readSnippet(11);
		assertTrue(storyService.deleteStoryText("user1story1", toDelete));
	}

	@Test
	@Transactional
	void testLikeStory() {
		assertTrue(storyService.likeStory("user1story1"));
	}

	@Test
	@Transactional
	void testDislikeStory() {
		assertTrue(storyService.dislikeStory("user1story1"));
	}

	@Test
	@Transactional
	void testDeleteStory() {
		assertTrue(storyService.deleteStory("user1story1"));
	}

	@Test
	@Transactional
	void testAddComment() {
		User user = userService.getUser("user1");
		assertTrue(storyService.addComment("user1story1", new Comment(user, "test")));
	}

	@Test
	@Transactional
	void testDeleteComment() {
		Comment toDelete = commentService.readComment(15);
		assertTrue(storyService.deleteComment("user2story2", toDelete));
	}

	@Test
	void testGetAllStories() {
		assertFalse(storyService.getAllStories().isEmpty());
	}

}
