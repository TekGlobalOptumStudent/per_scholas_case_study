package com.PatchworkNovels.repo;


import com.PatchworkNovels.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	public User getByUsername(String username);
	public boolean existsByUsername(String username);
	public boolean existsByUsernameAndPassword(String username, String password);

}
