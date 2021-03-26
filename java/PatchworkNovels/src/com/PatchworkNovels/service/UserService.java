package com.PatchworkNovels.service;

import java.util.List;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.dao.UserI;
import com.PatchworkNovels.entities.User;

public class UserService extends AbstractDAO implements UserI {

	@Override
	public boolean addUser(User user) {
		if(user == null) return false;
		if(connect()) {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}
		dispose();
		return true;
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
	public boolean editUser(int userId, User newUserInfo) {
		if(userId < 0 || newUserInfo == null) return false;
		if(connect()) {
			User toEdit = em.find(User.class, userId);
			if(toEdit == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public boolean deleteUser(int userId) {
		if(userId < 0) return false;
		if(connect()) {
			User toRemove = em.find(User.class, userId);
			if(toRemove == null) return false;
			em.getTransaction().begin();
			em.remove(toRemove);
			em.getTransaction().commit();
		}
		dispose();
		return true;
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
			String query = "SELECT u FROM User u WHERE u.username = ? AND u.password = ?";
			ret = em.createQuery(query, User.class).setParameter(1, username).setParameter(2, password).getResultList().isEmpty();
		}
		dispose();
		return ret;
	}

	// database initializer function
	
	public boolean createTable() {
		if(connect()) {
			em.getTransaction().begin();
			em.getTransaction().commit();
		}
		dispose();
		return false;
	}
	
}
