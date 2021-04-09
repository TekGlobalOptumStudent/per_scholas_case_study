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
		return true;
	}

	public Story readStory(String storyTitle) {
		if(storyTitle == null) return null;
		return storyRepository.getByStoryTitle(storyTitle);
	}

	@Transactional
	public boolean editStory(String storyTitle, List<Snippet> newStoryText) {
		if(storyTitle == null || newStoryText == null) return false;
		Story story = storyRepository.getByStoryTitle(storyTitle);
		if(story != null) {
			story.setStoryText(newStoryText);
			storyRepository.save(story);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean renameStory(String storyTitle, String newStoryTitle) {
		if(storyTitle == null || newStoryTitle == null) return false;
		Story story = storyRepository.getByStoryTitle(storyTitle);
		if(story != null) {
			story.setStoryTitle(newStoryTitle);
			storyRepository.save(story);
			return true;
		}
		return false;
	}
	
	@Transactional
	public boolean deleteStoryText(String storyTitle, Snippet snippet) {
		if(storyTitle == null || snippet == null) return false;
		Story story = storyRepository.getByStoryTitle(storyTitle);
		if(story != null) {
			story.getStoryText().remove(snippet);
			storyRepository.save(story);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean likeStory(String storyTitle) {
		if(storyTitle == null) return false;
		Story story = storyRepository.getByStoryTitle(storyTitle);
		if(story != null) {
			story.setStoryRating(story.getStoryRating() + 1);
			storyRepository.save(story);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean dislikeStory(String storyTitle) {
		if(storyTitle == null) return false;
		Story story = storyRepository.getByStoryTitle(storyTitle);
		if(story != null) {
			story.setStoryRating(story.getStoryRating() - 1);
			storyRepository.save(story);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean deleteStory(String storyTitle) {
		if(storyTitle == null) return false;
		Story story = storyRepository.getByStoryTitle(storyTitle);
		if(story != null) {
			storyRepository.delete(story);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean addComment(String storyTitle, Comment comment) {
		if(storyTitle == null || comment == null) return false;
		boolean ret = false;
		Story story = storyRepository.getByStoryTitle(storyTitle);
		if(story != null) {
			ret = story.getStoryComments().add(comment);
			storyRepository.save(story);
		}
		return ret;
	}

	@Transactional
	public boolean deleteComment(String storyTitle, Comment comment) {
		if(storyTitle == null || comment == null) return false;
		boolean ret = false;
		Story story = storyRepository.getByStoryTitle(storyTitle);
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
