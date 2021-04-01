package com.PatchworkNovels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PatchworkNovels.dao.SnippetI;
import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;
import com.PatchworkNovels.repo.SnippetRepository;

@Service
public class SnippetService implements SnippetI {

	@Autowired
	SnippetRepository snr;
	
	@Autowired
	UserService us;

	@Override
	@Transactional
	public boolean addSnippet(Snippet snippet) {
		if(snippet == null) return false;
		snr.save(snippet);
		return true;
	}

	@Override
	public Snippet readSnippet(int snippetId) {
		if(snippetId < 0) return null;
		return snr.getBySnippetId(snippetId);
	}

	@Override
	@Transactional
	public boolean editSnippet(int snippetId, String newSnippetText) {
		if(snippetId < 0 || newSnippetText == null) return false;
		Snippet snippet = snr.getBySnippetId(snippetId);
		if(snippet != null) {
			snippet.setSnippetText(newSnippetText);
			snr.save(snippet);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean addStory(int snippetId, Story story) {
		if(snippetId < 0 || story == null) return false;
		boolean ret = false;
		Snippet snippet = snr.getBySnippetId(snippetId);
		if(snippet != null) {
			ret = snippet.getSnippetStories().add(story);
			snr.save(snippet);
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean deleteStory(int snippetId, Story story) {
		if(snippetId < 0 || story == null) return false;
		boolean ret = false;
		Snippet snippet = snr.getBySnippetId(snippetId);
		if(snippet != null) {
			ret = snippet.getSnippetStories().remove(story);
			snr.save(snippet);
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean addComment(int snippetId, Comment comment) {
		if(snippetId < 0 || comment == null) return false;
		boolean ret = false;
		Snippet snippet = snr.getBySnippetId(snippetId);
		if(snippet != null) {
			ret = snippet.getSnippetComments().add(comment);
			snr.save(snippet);
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean deleteComment(int snippetId, Comment comment) {
		if(snippetId < 0 || comment == null) return false;
		boolean ret = false;
		Snippet snippet = snr.getBySnippetId(snippetId);
		if(snippet != null) {
			ret = snippet.getSnippetComments().remove(comment);
			snr.save(snippet);
		}
		return ret;
	}

	@Override
	@Transactional
	public boolean deleteSnippet(int snippetId) {
		if(snippetId < 0) return false;
		Snippet snippet = snr.getBySnippetId(snippetId);
		if(snippet != null) {
			if(snippet.getSnippetStories().isEmpty()) {
				snr.delete(snippet);
			} else {
				snippet.setSnippetAuthor(us.getUser(-1));
			}
			return true;
		}
		return false;
	}

	@Override
	public List<Snippet> getAllSnippets() {
		return snr.findAll();
	}
	
}
