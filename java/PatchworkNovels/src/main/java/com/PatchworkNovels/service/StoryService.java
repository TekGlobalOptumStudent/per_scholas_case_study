package com.PatchworkNovels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.dao.StoryI;
import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.repo.StoryRepository;

@Service
public class StoryService implements StoryI {

	@Autowired
	StoryRepository sts;

	@Override
	@Transactional
	public boolean addStory(Story story) {
		if(story == null) return false;
		sts.save(story);
		return false;
	}

	@Override
	public Story readStory(int storyId) {
		if(storyId < 0) return null;
		return sts.getByStoryId(storyId);
	}

	@Override
	@Transactional
	public boolean editStory(int storyId, List<Snippet> newStoryText) {
		if(storyId < 0 || newStoryText == null) return false;
		Story story = sts.getByStoryId(storyId);
		if(story != null) {
			story.setStoryText(newStoryText);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean renameStory(int storyId, String newStoryTitle) {
		if(storyId < 0 || newStoryTitle == null) return false;
		Story story = sts.getByStoryId(storyId);
		if(story != null) {
			story.setStoryTitle(newStoryTitle);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean likeStory(int storyId) {
		if(storyId < 0) return false;
		Story story = sts.getByStoryId(storyId);
		if(story != null) {
			story.setStoryRating(story.getStoryRating() + 1);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean dislikeStory(int storyId) {
		if(storyId < 0) return false;
		Story story = sts.getByStoryId(storyId);
		if(story != null) {
			story.setStoryRating(story.getStoryRating() - 1);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean deleteStory(int storyId) {
		if(storyId < 0) return false;
		Story story = sts.getByStoryId(storyId);
		if(story != null) {
			sts.delete(story);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean addComment(int storyId, Comment comment) {
		if(storyId < 0 || comment == null) return false;
		boolean ret = false;
		Story story = sts.getByStoryId(storyId);
		if(story != null) {
			ret = story.getStoryComments().add(comment);
			sts.save(story);
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean deleteComment(int storyId, Comment comment) {
		if(storyId < 0 || comment == null) return false;
		boolean ret = false;
		Story story = sts.getByStoryId(storyId);
		if(story != null) {
			ret = story.getStoryComments().remove(comment);
			sts.save(story);
		}
		return ret;
	}

	@Override
	public List<Story> getAllStories() {
		return sts.findAll();
	}
	
}
