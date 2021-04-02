package com.PatchworkNovels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.repo.StoryRepository;

@Service
public class StoryService {

	@Autowired
	StoryRepository storyRepository;

	@Transactional
	public boolean addStory(Story story) {
		if(story == null) return false;
		storyRepository.save(story);
		return false;
	}

	public Story readStory(int storyId) {
		if(storyId < 0) return null;
		return storyRepository.getByStoryId(storyId);
	}

	@Transactional
	public boolean editStory(int storyId, List<Snippet> newStoryText) {
		if(storyId < 0 || newStoryText == null) return false;
		Story story = storyRepository.getByStoryId(storyId);
		if(story != null) {
			story.setStoryText(newStoryText);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean renameStory(int storyId, String newStoryTitle) {
		if(storyId < 0 || newStoryTitle == null) return false;
		Story story = storyRepository.getByStoryId(storyId);
		if(story != null) {
			story.setStoryTitle(newStoryTitle);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean likeStory(int storyId) {
		if(storyId < 0) return false;
		Story story = storyRepository.getByStoryId(storyId);
		if(story != null) {
			story.setStoryRating(story.getStoryRating() + 1);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean dislikeStory(int storyId) {
		if(storyId < 0) return false;
		Story story = storyRepository.getByStoryId(storyId);
		if(story != null) {
			story.setStoryRating(story.getStoryRating() - 1);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean deleteStory(int storyId) {
		if(storyId < 0) return false;
		Story story = storyRepository.getByStoryId(storyId);
		if(story != null) {
			storyRepository.delete(story);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean addComment(int storyId, Comment comment) {
		if(storyId < 0 || comment == null) return false;
		boolean ret = false;
		Story story = storyRepository.getByStoryId(storyId);
		if(story != null) {
			ret = story.getStoryComments().add(comment);
			storyRepository.save(story);
		}
		return ret;
	}

	@Transactional
	public boolean deleteComment(int storyId, Comment comment) {
		if(storyId < 0 || comment == null) return false;
		boolean ret = false;
		Story story = storyRepository.getByStoryId(storyId);
		if(story != null) {
			ret = story.getStoryComments().remove(comment);
			storyRepository.save(story);
		}
		return ret;
	}

	public List<Story> getAllStories() {
		return storyRepository.findAll();
	}
	
}
