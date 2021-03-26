package com.PatchworkNovels.dao;

import java.util.List;

import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;

public interface UserI {
	
	public boolean addUser(User user);
	public User getUser(int userId);
	public boolean editUser(int userId, User newUserInfo);
	public boolean addPublishedStory(int userId, Story story);
	public boolean deletePublishedStory(int userId, int storyId);
	public boolean addPublishedSnippet(int userId, Snippet snippet);
	public boolean deletePublishedSnippet(int userId, int snippetId);
	public boolean addFavoriteStory(int userId, Story story);
	public boolean deleteFavoriteStory(int userId, int storyId);
	public boolean deleteUser(int userId);
	public List<User> getAllUsers();
	public boolean validateUser(String username, String password);
	
}
