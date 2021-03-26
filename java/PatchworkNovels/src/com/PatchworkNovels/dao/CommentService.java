package com.PatchworkNovels.dao;

import java.util.List;

import com.PatchworkNovels.entities.Comment;

public class CommentService extends AbstractDAO implements CommentI {

	@Override
	public boolean addComment(Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Comment readComment(int commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editComment(int commentId, String newCommentText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean likeComment(int commentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dislikeComment(int commentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteComment(int commentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Comment> getAllComments() {
		// TODO Auto-generated method stub
		return null;
	}

}
