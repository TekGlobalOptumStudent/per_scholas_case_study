package com.PatchworkNovels.dao;

import java.util.List;

import com.PatchworkNovels.entities.User;

public interface UserI {
	
	public boolean addUser(User user);
	public User getUser(int userId);
	public boolean editUser(int userId, User newUserInfo);
	public boolean deleteUser(int userId);
	public List<User> getAllUsers();
	public boolean validateUser(String username, String password);
	
}
