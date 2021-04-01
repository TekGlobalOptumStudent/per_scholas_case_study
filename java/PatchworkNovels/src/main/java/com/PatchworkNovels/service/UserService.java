package com.PatchworkNovels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PatchworkNovels.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
}
