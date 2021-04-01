package com.PatchworkNovels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.dao.UserI;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.repo.UserRepository;

@Service
public class UserService implements UserI {

	@Autowired
	UserRepository ur;
	
	@Autowired
	SnippetService sns;
	
	@Autowired
	StoryService sts;

	@Override
	@Transactional
	public boolean addUser(User user) {
		if(user == null) return false;
		ur.save(user);
		return true;
	}

	@Override
	@Transactional
	public User getUser(int userId) {
		if(userId < 0) return null;
		return ur.getByUserId(userId);
	}

	@Override
	@Transactional
	public boolean editUser(int userId, String newUsername, String newPassword) {
		if(userId < 0 || newUsername == null || newPassword == null) return false;
		User user = ur.getByUserId(userId);
		if(user != null) {
			user.setUsername(newUsername);
			user.setPassword(newPassword);
			ur.save(user);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean addPublishedStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		User user = ur.getByUserId(userId);
		if(user != null) {
			ret = user.getPublishedStories().add(story);
			ur.save(user);
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean deletePublishedStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		User user = ur.getByUserId(userId);
		if(user != null) {
			getAllUsers().forEach(u -> deleteFavoriteStory(u.getUserId(), story));
			ret = user.getPublishedStories().remove(story);
			ur.save(user);
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean addPublishedSnippet(int userId, Snippet snippet) {
		if(userId < 0 || snippet == null) return false;
		boolean ret = false;
		User user = ur.getByUserId(userId);
		if(user != null) {
			ret = user.getPublishedSnippets().add(snippet);
			ur.save(user);
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean deletePublishedSnippet(int userId, Snippet snippet) {
		if(userId < 0 || snippet == null) return false;
		boolean ret = false;
		User user = ur.getByUserId(userId);
		if(user != null) {
			if(snippet.getSnippetStories().isEmpty()) {
				ret = user.getPublishedSnippets().remove(snippet);
				ur.save(user);
			} else {
				snippet.setSnippetAuthor(getUser(-1));
				ret = true;
			}
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean addFavoriteStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		User user = ur.getByUserId(userId);
		if(user != null) {
			ret = user.getFavoriteStories().add(story);
			ur.save(user);
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean deleteFavoriteStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		User user = ur.getByUserId(userId);
		if(user != null) {
			ret = user.getFavoriteStories().remove(story);
			ur.save(user);
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean deleteUser(int userId) {
		if(userId < 0) return false;
		User user = ur.getByUserId(userId);
		if(user != null) {
			ur.delete(user);
			return true;
		}
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		return ur.findAll();
	}

	@Override
	public boolean validateUser(String username, String password) {
		return ur.existsByUsernameAndPassword(username, password);
	}
	
}
