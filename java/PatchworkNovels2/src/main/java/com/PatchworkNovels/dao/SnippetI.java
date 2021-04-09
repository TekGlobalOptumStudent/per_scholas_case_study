package com.PatchworkNovels.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.Story;

@Service
public interface SnippetI {
	
	public boolean addSnippet(Snippet snippet);
	public Snippet readSnippet(int snippetId);
	public boolean editSnippet(int snippetId, String newSnippetText);
	public boolean addStory(int snippetId, Story story);
	public boolean deleteStory(int snippetId, Story story);
	public boolean addComment(int snippetId, Comment comment);
	public boolean deleteComment(int snippetId, Comment comment);
	public boolean deleteSnippet(int snippetId);
	public List<Snippet> getAllSnippets();
	
}
