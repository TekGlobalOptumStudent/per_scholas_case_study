package com.PatchworkNovels.dao;

import java.util.List;

import com.PatchworkNovels.entities.Comment;

public interface CommentI {
	
	public boolean createComment();
	public Comment readComment();
	public boolean updateComment();
	public boolean deleteComment();
	public List<Comment> getAllComments();
	
}
