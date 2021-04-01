package com.PatchworkNovels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.dao.CommentI;
import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.repo.CommentRepository;

@Service
public class CommentService implements CommentI {

	@Autowired
	CommentRepository cr;
	
	@Autowired
	UserService us;

	@Override
	@Transactional
	public boolean addComment(Comment comment) {
		if(comment == null) return false;
		cr.save(comment);
		return true;
	}

	@Override
	public Comment readComment(int commentId) {
		if(commentId < 0) return null;
		return cr.getByCommentId(commentId);
	}

	@Override
	@Transactional
	public boolean editComment(int commentId, String newCommentText) {
		if(commentId < 0 || newCommentText == null) return false;
		Comment comment = cr.getByCommentId(commentId);
		if(comment != null) {
			comment.setCommentText(newCommentText);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean likeComment(int commentId) {
		if(commentId < 0) return false;
		Comment comment = cr.getByCommentId(commentId);
		if(comment != null) {
			comment.setCommentRating(comment.getCommentId() + 1);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean dislikeComment(int commentId) {
		if(commentId < 0) return false;
		Comment comment = cr.getByCommentId(commentId);
		if(comment != null) {
			comment.setCommentRating(comment.getCommentId() - 1);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean deleteComment(int commentId) {
		if(commentId < 0) return false;
		Comment comment = cr.getByCommentId(commentId);
		if(comment != null) {
			cr.delete(comment);
			return true;
		}
		return false;
	}

	@Override
	public List<Comment> getAllComments() {
		return cr.findAll();
	}

	@Override
	@Transactional
	public boolean updateAllComments(int userId) {
		if(userId < 0) return false;
		List<Comment> userComments = cr.findAllByUserId(userId);
		if(userComments != null && !userComments.isEmpty())
			userComments.forEach(c -> c.setCommentAuthor(us.getUser(-1)));
		return false;
	}
	
}
