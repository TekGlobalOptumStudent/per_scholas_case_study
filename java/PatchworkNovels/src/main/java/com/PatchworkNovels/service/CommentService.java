package com.PatchworkNovels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PatchworkNovels.repo.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository cr;
	
}
