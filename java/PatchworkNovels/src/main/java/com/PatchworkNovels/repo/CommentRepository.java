package com.PatchworkNovels.repo;


import com.PatchworkNovels.entities.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	public Comment getByCommentId(int commentId);
	public List<Comment> findAllByUserId(int userId);
	
}
