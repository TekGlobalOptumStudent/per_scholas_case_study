package com.PatchworkNovels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.repo.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	UserService userService;

	@Transactional
	public boolean addComment(Comment comment) {
		if(comment == null) return false;
		commentRepository.save(comment);
		return true;
	}

	public Comment readComment(int commentId) {
		if(commentId < 0) return null;
		return commentRepository.getByCommentId(commentId);
	}

	@Transactional
	public boolean editComment(int commentId, String newCommentText) {
		if(commentId < 0 || newCommentText == null) return false;
		Comment comment = commentRepository.getByCommentId(commentId);
		if(comment != null) {
			comment.setCommentText(newCommentText);
			commentRepository.save(comment);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean likeComment(int commentId) {
		if(commentId < 0) return false;
		Comment comment = commentRepository.getByCommentId(commentId);
		if(comment != null) {
			comment.setCommentRating(comment.getCommentRating() + 1);
			commentRepository.save(comment);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean dislikeComment(int commentId) {
		if(commentId < 0) return false;
		Comment comment = commentRepository.getByCommentId(commentId);
		if(comment != null) {
			comment.setCommentRating(comment.getCommentRating() - 1);
			commentRepository.save(comment);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean deleteComment(int commentId) {
		if(commentId < 0) return false;
		Comment comment = commentRepository.getByCommentId(commentId);
		if(comment != null) {
			commentRepository.delete(comment);
			return true;
		}
		return false;
	}

	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	@Transactional
	public boolean updateAllComments(User user) {
		if(user == null) return false;
		List<Comment> userComments = commentRepository.findAllByCommentAuthor(user);
		if(userComments != null && !userComments.isEmpty())
			userComments.forEach(c -> {
				c.setCommentAuthor(userService.getUser(""));
				commentRepository.save(c);
			});
		return !userComments.isEmpty();
	}
	
}
