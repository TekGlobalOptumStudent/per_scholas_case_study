package com.PatchworkNovels.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.service.CommentService;
import com.PatchworkNovels.service.UserService;

@SpringBootTest
class CommentServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
	CommentService commentService;

	@Test
	@Transactional
	void testAddComment() {
		User user = userService.getUser("user1");
		assertTrue(commentService.addComment(new Comment(user, "testComment")));
	}

	@Test
	@Transactional
	void testReadComment() {
		assertNotNull(commentService.readComment(11));
	}

	@Test
	@Transactional
	void testEditComment() {
		assertTrue(commentService.editComment(11, "testNewCommentText"));
	}

	@Test
	@Transactional
	void testLikeComment() {
		assertTrue(commentService.likeComment(11));
	}

	@Test
	@Transactional
	void testDislikeComment() {
		assertTrue(commentService.dislikeComment(11));
	}

	@Test
	@Transactional
	void testDeleteComment() {
		assertTrue(commentService.deleteComment(11));
	}

	@Test
	@Transactional
	void testGetAllComments() {
		assertFalse(commentService.getAllComments().isEmpty());
	}

	@Test
	@Transactional
	void testUpdateAllComments() {
		User user = userService.getUser("user1");
		assertTrue(commentService.updateAllComments(user));
	}

}
