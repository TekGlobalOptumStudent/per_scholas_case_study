package com.PatchworkNovels.dao;

import java.util.List;

import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;

public class StoryService extends AbstractDAO implements StoryI {

	@Override
	public boolean addStory(Story story) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Story readStory(int storyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editStory(int storyId, List<Snippet> newStoryText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean likeStory(int storyId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dislikeStory(int storyId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSnippet(int storyId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Story> getAllStories() {
		// TODO Auto-generated method stub
		return null;
	}

}
