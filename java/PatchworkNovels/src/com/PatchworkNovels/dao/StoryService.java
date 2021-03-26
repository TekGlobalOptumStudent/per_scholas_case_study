package com.PatchworkNovels.dao;

import java.util.List;

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
	public boolean deleteSnippet(int storyId) {
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

}
