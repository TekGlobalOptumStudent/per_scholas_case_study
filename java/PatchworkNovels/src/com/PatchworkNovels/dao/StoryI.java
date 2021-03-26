package com.PatchworkNovels.dao;

import java.util.List;

import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;

public interface StoryI {
	
	public boolean addStory(Story story);
	public Story readStory(int storyId);
	public boolean editStory(int storyId, List<Snippet> newStoryText);
	public boolean likeStory(int storyId);
	public boolean dislikeStory(int storyId);
	public boolean deleteSnippet(int storyId);
	public List<Story> getAllStories();
	
}
