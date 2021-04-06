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
	public User getUser(String username) {
		if(username == null) return null;
		return userRepository.getByUsername(username);
	}

	@Transactional
	public boolean editUser(String username, String newUsername, String newPassword) {
		if(username == null || newUsername == null || newPassword == null) return false;
		User user = userRepository.getByUsername(username);
		if(user != null) {
			user.setUsername(newUsername);
			user.setPassword(newPassword);
			userRepository.save(user);
			return true;
		}
		return false;
	}
	
	@Transactional
	public boolean addProfileImage(String username, String profileImage) {
		if(username == null || profileImage == null) return false;
		User user = userRepository.getByUsername(username);
		if(user != null) {
			user.setProfileImage(profileImage);
			userRepository.save(user);
			return true;
		}
		return false;
	}
	
	@Transactional
	public boolean deleteProfileImage(String username) {
		if(username == null) return false;
		User user = userRepository.getByUsername(username);
		if(user != null) {
			user.setProfileImage(null);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean addPublishedStory(String username, Story story) {
		if(username == null || story == null) return false;
		boolean ret = false;
		User user = userRepository.getByUsername(username);
		if(user != null) {
			ret = user.getPublishedStories().add(story);
			userRepository.save(user);
		}
		return ret;
	}

	@Transactional
	public boolean deletePublishedStory(String username, Story story) {
		if(username == null || story == null) return false;
		boolean ret = false;
		User user = userRepository.getByUsername(username);
		if(user != null) {
			getAllUsers().forEach(u -> deleteFavoriteStory(u.getUsername(), story));
			ret = user.getPublishedStories().remove(story);
			userRepository.save(user);
		}
		return ret;
	}

	@Transactional
	public boolean addPublishedSnippet(String username, Snippet snippet) {
		if(username == null || snippet == null) return false;
		boolean ret = false;
		User user = userRepository.getByUsername(username);
		if(user != null) {
			ret = user.getPublishedSnippets().add(snippet);
			userRepository.save(user);
		}
		return ret;
	}

	@Transactional
	public boolean deletePublishedSnippet(String username, Snippet snippet) {
		if(username == null || snippet == null) return false;
		boolean ret = false;
		User user = userRepository.getByUsername(username);
		if(user != null) {
			if(snippet.getSnippetStories().isEmpty()) {
				ret = user.getPublishedSnippets().remove(snippet);
				userRepository.save(user);
			} else {
				snippet.setSnippetAuthor(getUser(""));
				ret = true;
			}
		}
		return ret;
	}

	@Transactional
	public boolean addFavoriteStory(String username, Story story) {
		if(username == null || story == null) return false;
		boolean ret = false;
		User user = userRepository.getByUsername(username);
		if(user != null) {
			ret = user.getFavoriteStories().add(story);
			userRepository.save(user);
		}
		return ret;
	}

	@Transactional
	public boolean deleteFavoriteStory(String username, Story story) {
		if(username == null || story == null) return false;
		boolean ret = false;
		User user = userRepository.getByUsername(username);
		if(user != null) {
			ret = user.getFavoriteStories().remove(story);
			userRepository.save(user);
		}
		return ret;
	}

	@Transactional
	public boolean deleteUser(String username) {
		if(username == null) return false;
		User user = userRepository.getByUsername(username);
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
		if(username == null) return false;
		return userRepository.existsByUsername(username);
	}

	public boolean validateUser(String username, String password) {
		if(username == null || password == null) return false;
		return userRepository.existsByUsernameAndPassword(username, password);
	}
	
}
