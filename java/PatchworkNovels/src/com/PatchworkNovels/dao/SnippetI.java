package com.PatchworkNovels.dao;

import java.util.List;

import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;

public interface SnippetI {
	
	public boolean addSnippet(Snippet snippet);
	public Snippet readSnippet(int snippetId);
	public boolean editSnippet(int snippetId, String newSnippetText);
	public boolean addComment(int snippetId, Comment comment);
	public boolean deleteComment(int snippetId, int commentId);
	public boolean deleteSnippet(int snippetId);
	public List<Snippet> getAllSnippets();
	
}
