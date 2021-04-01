package com.PatchworkNovels.dao;

import java.util.List;

import com.PatchworkNovels.entities.Comment;

public interface CommentI {
	
	public boolean addComment(Comment comment);
	public Comment readComment(int commentId);
	public boolean editComment(int commentId, String newCommentText);
	public boolean likeComment(int commentId);
	public boolean dislikeComment(int commentId);
	public boolean deleteComment(int commentId);
	public List<Comment> getAllComments();
	public boolean updateAllComments(int userId);
	
}
