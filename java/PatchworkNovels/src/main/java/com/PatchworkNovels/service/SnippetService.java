package com.PatchworkNovels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.repo.SnippetRepository;

@Service
public class SnippetService {

	@Autowired
	SnippetRepository snippetRepository;
	
	@Autowired
	UserService userService;

	@Transactional
	public boolean addSnippet(Snippet snippet) {
		if(snippet == null) return false;
		snippetRepository.save(snippet);
		return true;
	}

	public Snippet readSnippet(int snippetId) {
		if(snippetId < 0) return null;
		return snippetRepository.getBySnippetId(snippetId);
	}

	@Transactional
	public boolean editSnippet(int snippetId, String newSnippetText) {
		if(snippetId < 0 || newSnippetText == null) return false;
		Snippet snippet = snippetRepository.getBySnippetId(snippetId);
		if(snippet != null) {
			snippet.setSnippetText(newSnippetText);
			snippetRepository.save(snippet);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean addStory(int snippetId, Story story) {
		if(snippetId < 0 || story == null) return false;
		boolean ret = false;
		Snippet snippet = snippetRepository.getBySnippetId(snippetId);
		if(snippet != null) {
			ret = snippet.getSnippetStories().add(story);
			snippetRepository.save(snippet);
		}
		return ret;
	}

	@Transactional
	public boolean deleteStory(int snippetId, Story story) {
		if(snippetId < 0 || story == null) return false;
		boolean ret = false;
		Snippet snippet = snippetRepository.getBySnippetId(snippetId);
		if(snippet != null) {
			ret = snippet.getSnippetStories().remove(story);
			snippetRepository.save(snippet);
		}
		return ret;
	}

	@Transactional
	public boolean addComment(int snippetId, Comment comment) {
		if(snippetId < 0 || comment == null) return false;
		boolean ret = false;
		Snippet snippet = snippetRepository.getBySnippetId(snippetId);
		if(snippet != null) {
			ret = snippet.getSnippetComments().add(comment);
			snippetRepository.save(snippet);
		}
		return ret;
	}
	
	@Transactional
	public boolean deleteComment(int snippetId, Comment comment) {
		if(snippetId < 0 || comment == null) return false;
		boolean ret = false;
		Snippet snippet = snippetRepository.getBySnippetId(snippetId);
		if(snippet != null) {
			ret = snippet.getSnippetComments().remove(comment);
			snippetRepository.save(snippet);
		}
		return ret;
	}

	@Transactional
	public boolean deleteSnippet(int snippetId) {
		if(snippetId < 0) return false;
		Snippet snippet = snippetRepository.getBySnippetId(snippetId);
		if(snippet != null) {
			if(snippet.getSnippetStories().isEmpty()) {
				snippetRepository.delete(snippet);
			} else {
				snippet.setSnippetAuthor(userService.getUser(-1));
			}
			return true;
		}
		return false;
	}

	public List<Snippet> getAllSnippets() {
		return snippetRepository.findAll();
	}
	
}
