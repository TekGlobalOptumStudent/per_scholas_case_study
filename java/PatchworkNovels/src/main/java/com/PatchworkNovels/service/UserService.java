package com.PatchworkNovels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;
import com.PatchworkNovels.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	SnippetService snippetService;
	
	@Autowired
	StoryService storyService;

	@Transactional
	public boolean addUser(User user) {
		if(user == null) return false;
		userRepository.save(user);
		return true;
	}

	@Transactional
	public User getUser(int userId) {
		if(userId < 0) return null;
		return userRepository.getByUserId(userId);
	}

	@Transactional
	public boolean editUser(int userId, String newUsername, String newPassword) {
		if(userId < 0 || newUsername == null || newPassword == null) return false;
		User user = userRepository.getByUserId(userId);
		if(user != null) {
			user.setUsername(newUsername);
			user.setPassword(newPassword);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean addPublishedStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		User user = userRepository.getByUserId(userId);
		if(user != null) {
			ret = user.getPublishedStories().add(story);
			userRepository.save(user);
		}
		return ret;
	}

	@Transactional
	public boolean deletePublishedStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		User user = userRepository.getByUserId(userId);
		if(user != null) {
			getAllUsers().forEach(u -> deleteFavoriteStory(u.getUserId(), story));
			ret = user.getPublishedStories().remove(story);
			userRepository.save(user);
		}
		return ret;
	}

	@Transactional
	public boolean addPublishedSnippet(int userId, Snippet snippet) {
		if(userId < 0 || snippet == null) return false;
		boolean ret = false;
		User user = userRepository.getByUserId(userId);
		if(user != null) {
			ret = user.getPublishedSnippets().add(snippet);
			userRepository.save(user);
		}
		return ret;
	}

	@Transactional
	public boolean deletePublishedSnippet(int userId, Snippet snippet) {
		if(userId < 0 || snippet == null) return false;
		boolean ret = false;
		User user = userRepository.getByUserId(userId);
		if(user != null) {
			if(snippet.getSnippetStories().isEmpty()) {
				ret = user.getPublishedSnippets().remove(snippet);
				userRepository.save(user);
			} else {
				snippet.setSnippetAuthor(getUser(-1));
				ret = true;
			}
		}
		return ret;
	}

	@Transactional
	public boolean addFavoriteStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		User user = userRepository.getByUserId(userId);
		if(user != null) {
			ret = user.getFavoriteStories().add(story);
			userRepository.save(user);
		}
		return ret;
	}

	@Transactional
	public boolean deleteFavoriteStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		User user = userRepository.getByUserId(userId);
		if(user != null) {
			ret = user.getFavoriteStories().remove(story);
			userRepository.save(user);
		}
		return ret;
	}

	@Transactional
	public boolean deleteUser(int userId) {
		if(userId < 0) return false;
		User user = userRepository.getByUserId(userId);
		if(user != null) {
			userRepository.delete(user);
			return true;
		}
		return false;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public boolean checkUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public boolean validateUser(String username, String password) {
		return userRepository.existsByUsernameAndPassword(username, password);
	}
	
}
