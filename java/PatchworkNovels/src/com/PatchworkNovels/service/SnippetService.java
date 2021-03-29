package com.PatchworkNovels.service;

import java.util.List;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.dao.SnippetI;
import com.PatchworkNovels.entities.Comment;
import com.PatchworkNovels.entities.Snippet;
import com.PatchworkNovels.entities.User;

public class SnippetService extends AbstractDAO implements SnippetI {

	@Override
	public boolean addSnippet(Snippet snippet) {
		if(snippet == null) return false;
		if(connect()) {
			em.getTransaction().begin();
			em.persist(snippet);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public Snippet readSnippet(int snippetId) {
		if(snippetId < 0) return null;
		Snippet ret = null;
		if(connect()) ret = em.find(Snippet.class, snippetId);
		dispose();
		return ret;
	}

	@Override
	public boolean editSnippet(int snippetId, String newSnippetText) {
		if(snippetId < 0 || newSnippetText == null) return false;
		if(connect()) {
			Snippet toEdit = em.find(Snippet.class, snippetId);
			if(toEdit == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			toEdit.setSnippetText(newSnippetText);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public boolean addComment(int snippetId, Comment comment) {
		if(snippetId < 0 || comment == null) return false;
		if(connect()) {
			Snippet snippet = em.find(Snippet.class, snippetId);
			if(snippet == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			snippet.getSnippetComments().add(comment);
			em.getTransaction().commit();
		}
		dispose();
		return true;
	}

	@Override
	public boolean deleteComment(int snippetId, Comment comment) {
		if(snippetId < 0 || comment == null) return false;
		if(connect()) {
			Snippet snippet = em.find(Snippet.class, snippetId);
			if(snippet == null) {
				dispose();
				return false;
			}
			em.getTransaction().begin();
			snippet.getSnippetComments().remove(comment);
			em.getTransaction().commit();
			dispose();
			return true;
		}
		dispose();
		return false;
	}

	@Override
	public boolean deleteSnippet(int snippetId) {
		if(snippetId < 0) return false;
		if(connect()) {
			Snippet toRemove = em.find(Snippet.class, snippetId);
			if(toRemove == null) {
				dispose();
				return false;
			}
			Snippet replacement = new Snippet(em.find(User.class, -1), toRemove.getSnippetText());
			em.getTransaction().begin();
			em.persist(replacement);
			toRemove.getSnippetStories().forEach(s -> {
				s.getStoryText().set(s.getStoryText().indexOf(toRemove), replacement);
			});
			em.remove(toRemove);
			em.getTransaction().commit();
			dispose();
			return true;
		}
		dispose();
		return false;
	}

	@Override
	public List<Snippet> getAllSnippets() {
		List<Snippet> ret = null;
		if(connect()) ret = em.createQuery("SELECT s FROM Snippet s", Snippet.class).getResultList();
		dispose();
		return ret;
	}

	// database initializer function
	
	public boolean createTable() {
		if(connect()) {
			em.getTransaction().begin();
			em.getTransaction().commit();
			dispose();
			return true;
		}
		dispose();
		return false;
	}
	
}
