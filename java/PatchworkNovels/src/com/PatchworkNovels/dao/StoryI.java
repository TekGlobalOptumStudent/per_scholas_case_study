package com.PatchworkNovels.dao;

import java.util.List;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;

public interface StoryI {
	
	public boolean addStory(Story story);
	public Story readStory(int storyId);
	public boolean editStory(int storyId, List<Snippet> newStoryText);
	public boolean renameStory(int storyId, String newStoryTitle);
	public boolean likeStory(int storyId);
	public boolean dislikeStory(int storyId);
	public boolean deleteStory(int storyId);
	public boolean addComment(int storyId, Comment comment);
	public boolean deleteComment(int storyId, int commentId);
	public List<Story> getAllStories();
	
}
