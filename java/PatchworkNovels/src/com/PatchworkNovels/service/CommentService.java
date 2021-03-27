package com.PatchworkNovels.service;

import java.util.List;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.dao.CommentI;
import com.PatchworkNovels.entities.Comment;

public class CommentService extends AbstractDAO implements CommentI {

	@Override
	public boolean addComment(Comment comment) {
		if(comment == null) return false;
		if(connect()) {
			em.getTransaction().begin();
			em.persist(comment);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public Comment readComment(int commentId) {
		if(commentId < 0) return null;
		Comment ret = null;
		if(connect()) ret = em.find(Comment.class, commentId);
		dispose();
		return ret;
	}

	@Override
	public boolean editComment(int commentId, String newCommentText) {
		if(commentId < 0 || newCommentText == null) return false;
		if(connect()) {
			Comment toEdit = em.find(Comment.class, commentId);
			if(toEdit == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			toEdit.setCommenText(newCommentText);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public boolean likeComment(int commentId) {
		if(commentId < 0) return false;
		if(connect()) {
			Comment toLike = em.find(Comment.class, commentId);
			if(toLike == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			toLike.setCommentRating(toLike.getCommentRating() + 1);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public boolean dislikeComment(int commentId) {
		if(commentId < 0) return false;
		if(connect()) {
			Comment toLike = em.find(Comment.class, commentId);
			if(toLike == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			toLike.setCommentRating(toLike.getCommentRating() - 1);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public boolean deleteComment(int commentId) {
		if(commentId < 0) return false;
		if(connect()) {
			Comment toRemove = em.find(Comment.class, commentId);
			if(toRemove == null) return false;
			em.getTransaction().begin();
			em.remove(toRemove);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public List<Comment> getAllComments() {
		List<Comment> ret = null;
		if(connect()) ret = em.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
		dispose();
		return ret;
	}
	
	// database initializer function
	
	public boolean createTable() {
		if(connect()) {
			em.getTransaction().begin();
			em.getTransaction().commit();
			dispose();
			return true;
		}
		dispose();
		return false;
	}
	
}
