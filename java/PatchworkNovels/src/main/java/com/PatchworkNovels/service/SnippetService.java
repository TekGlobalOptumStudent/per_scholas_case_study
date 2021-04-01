package com.PatchworkNovels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PatchworkNovels.repo.SnippetRepository;

@Service
public class SnippetService {

	@Autowired
	SnippetRepository snr;
	
}
