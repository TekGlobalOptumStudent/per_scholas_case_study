package com.PatchworkNovels.service;

import java.util.List;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.dao.StoryI;
import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;

public class StoryService extends AbstractDAO implements StoryI {

	@Override
	public boolean addStory(Story story) {
		if(story == null) return false;
		if(connect()) {
			em.getTransaction().begin();
			em.persist(story);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public Story readStory(int storyId) {
		if(storyId < 0) return null;
		Story ret = null;
		if(connect()) ret = em.find(Story.class, storyId);
		dispose();
		return ret;
	}

	@Override
	public boolean editStory(int storyId, List<Snippet> newStoryText) {
		if(storyId < 0 || newStoryText == null) return false;
		if(connect()) {
			Story toEdit = em.find(Story.class, storyId);
			if(toEdit == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			toEdit.setStoryText(newStoryText);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public boolean renameStory(int storyId, String newStoryTitle) {
		if(storyId < 0 || newStoryTitle == null) return false;
		if(connect()) {
			Story toEdit = em.find(Story.class, storyId);
			if(toEdit == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			toEdit.setStoryTitle(newStoryTitle);
			em.getTransaction().commit();
		}
		dispose();
		return false;
	}

	@Override
	public boolean likeStory(int storyId) {
		if(storyId < 0) return false;
		if(connect()) {
			Story toLike = em.find(Story.class, storyId);
			if(toLike == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			toLike.setStoryRating(toLike.getStoryRating() + 1);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public boolean dislikeStory(int storyId) {
		if(storyId < 0) return false;
		if(connect()) {
			Story toLike = em.find(Story.class, storyId);
			if(toLike == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			toLike.setStoryRating(toLike.getStoryRating() - 1);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public boolean addComment(int storyId, Comment comment) {
		if(storyId < 0 || comment == null) return false;
		if(connect()) {
			Story story = em.find(Story.class, storyId);
			if(story == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			story.getStoryComments().add(comment);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public boolean deleteComment(int storyId, int commentId) {
		if(storyId < 0 || commentId < 0) return false;
		if(connect()) {
			Story story = em.find(Story.class, storyId);
			if(story == null) {
				dispose();
				return false;
			}
			for(Comment c : story.getStoryComments()) {
				if(c.getCommentId() == commentId) {
					em.getTransaction().begin();
					story.getStoryComments().remove(c);
					em.getTransaction().commit();
					dispose();
					return true;
				}
			}
		}
		dispose();
		return false;
	}

	@Override
	public boolean deleteStory(int storyId) {
		if(storyId < 0) return false;
		if(connect()) {
			Story toRemove = em.find(Story.class, storyId);
			if(toRemove == null) return false;
			em.getTransaction().begin();
			em.remove(toRemove);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public List<Story> getAllStories() {
		List<Story> ret = null;
		if(connect()) ret = em.createQuery("SELECT s FROM Story s", Story.class).getResultList();
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
