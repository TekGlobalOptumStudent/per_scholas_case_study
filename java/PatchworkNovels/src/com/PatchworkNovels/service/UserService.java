package com.PatchworkNovels.service;

import java.util.List;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.dao.UserI;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.entities.User;

public class UserService extends AbstractDAO implements UserI {

	@Override
	public boolean addUser(User user) {
		if(user == null) return false;
		boolean ret = false;
		if(connect()) {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			ret = true;
		}
		dispose();
		return ret;
	}

	@Override
	public User getUser(int userId) {
		if(userId < 0) return null;
		User ret = null;
		if(connect()) ret = em.find(User.class, userId);
		dispose();
		return ret;
	}

	@Override
	public boolean editUser(int userId, String newUsername, String newPassword) {
		if(userId < 0 || newUsername == null || newPassword == null) return false;
		boolean ret = false;
		if(connect()) {
			User toEdit = em.find(User.class, userId);
			if(toEdit != null) {
				em.getTransaction().begin();
				toEdit.setUsername(newUsername);
				toEdit.setPassword(newPassword);
				em.getTransaction().commit();
				ret = true;
			}
		}
		dispose();
		return ret;
	}

	@Override
	public boolean addPublishedStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		if(connect()) {
			User user = em.find(User.class, userId);
			if(user != null) {
				em.getTransaction().begin();
				ret = user.getPublishedStories().add(story);
				em.getTransaction().commit();
			}
		}
		dispose();
		return ret;
	}

	@Override
	public boolean deletePublishedStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		if(connect()) {
			User user = em.find(User.class, userId);
			if(user != null) {
				em.getTransaction().begin();
				ret = user.getPublishedStories().remove(story);
				em.getTransaction().commit();
			}
		}
		dispose();
		return ret;
	}

	@Override
	public boolean addPublishedSnippet(int userId, Snippet snippet) {
		if(userId < 0 || snippet == null) return false;
		boolean ret = false;
		if(connect()) {
			User user = em.find(User.class, userId);
			if(user != null) {
				em.getTransaction().begin();
				ret = user.getPublishedSnippets().add(snippet);
				em.getTransaction().commit();
			}
		}
		dispose();
		return ret;
	}

	@Override
	public boolean deletePublishedSnippet(int userId, Snippet snippet) {
		if(userId < 0 || snippet == null) return false;
		boolean ret = false;
		if(connect()) {
			User user = em.find(User.class, userId);
			if(user != null) {
				em.getTransaction().begin();
				ret = user.getPublishedSnippets().remove(snippet);
				em.getTransaction().commit();
			}
		}
		dispose();
		return ret;
	}

	@Override
	public boolean addFavoriteStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		if(connect()) {
			User user = em.find(User.class, userId);
			if(user != null) {
				em.getTransaction().begin();
				ret = user.getFavoriteStories().add(story);
				em.getTransaction().commit();
			}
		}
		dispose();
		return ret;
	}

	@Override
	public boolean deleteFavoriteStory(int userId, Story story) {
		if(userId < 0 || story == null) return false;
		boolean ret = false;
		if(connect()) {
			User user = em.find(User.class, userId);
			if(user != null) {
				em.getTransaction().begin();
				ret = user.getFavoriteStories().remove(story);
				em.getTransaction().commit();
			}
		}
		dispose();
		return ret;
	}

	@Override
	public boolean deleteUser(int userId) {
		if(userId < 0) return false;
		boolean ret = false;
		if(connect()) {
			User toRemove = em.find(User.class, userId);
			if(toRemove != null) {
				em.getTransaction().begin();
				em.remove(toRemove);
				em.getTransaction().commit();
				ret = true;
			}
		}
		dispose();
		return ret;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> ret = null;
		if(connect()) ret = em.createQuery("SELECT u FROM User u", User.class).getResultList();
		dispose();
		return ret;
	}

	@Override
	public boolean validateUser(String username, String password) {
		if(username == null || password == null) return false;
		boolean ret = false;
		if(connect()) {
			String query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
			ret = !em.createQuery(query, User.class).setParameter("username", username).setParameter("password", password).getResultList().isEmpty();
		}
		dispose();
		return ret;
	}

	// database initializer function
	
	public boolean createTable() {
		if(connect()) {
			em.getTransaction().begin();
			em.getTransaction().commit();
			dispose();
			return true;
		}
		dispose();
		return false;
	}

}
