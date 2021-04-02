package com.PatchworkNovels.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;

@Service
public interface UserI {
	
	public boolean addUser(User user);
	public User getUser(int userId);
	public boolean editUser(int userId, String newUsername, String newPassword);
	public boolean addPublishedStory(int userId, Story story);
	public boolean deletePublishedStory(int userId, Story story);
	public boolean addPublishedSnippet(int userId, Snippet snippet);
	public boolean deletePublishedSnippet(int userId, Snippet snippet);
	public boolean addFavoriteStory(int userId, Story story);
	public boolean deleteFavoriteStory(int userId, Story story);
	public boolean deleteUser(int userId);
	public List<User> getAllUsers();
	public boolean validateUser(String username, String password);
	
}
