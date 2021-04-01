package com.PatchworkNovels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PatchworkNovels.repo.StoryRepository;

@Service
public class StoryService {

	@Autowired
	StoryRepository str;
	
}
