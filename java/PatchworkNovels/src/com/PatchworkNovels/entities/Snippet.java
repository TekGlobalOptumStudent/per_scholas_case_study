package com.PatchworkNovels.entities;

import java.util.ArrayList;
import java.util.List;

public class Snippet {
	
	// private fields
	
	private int snippetId;
	private int authorId;
	private String snippetText;
	private List<Story> snippetStories;
	private List<Comment> snippetComments;
	
	// constructors
	
	public Snippet() { }
	
	public Snippet(int authorId, String snippetText) {
		this.authorId = authorId;
		this.snippetText = snippetText;
		this.snippetStories = new ArrayList<Story>();
		this.snippetComments = new ArrayList<Comment>();
	}
	
	// getters
	
	public int getSnippetId() {
		return this.snippetId;
	}
	
	public int getAuthorId() {
		return this.authorId;
	}
	
	public String getSnippetText() {
		return this.snippetText;
	}
	
	public List<Story> getSnippetStories() {
		return this.snippetStories;
	}
	
	public List<Comment> getSnippetComments() {
		return this.snippetComments;
	}
	
	// setters
	
	public void setSnippetId(int snippetId) {
		this.snippetId = snippetId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public void setSnippetText(String snippetText) {
		this.snippetText = snippetText;
	}
	
	public void setSnippetStories(List<Story> snippetStories) {
		this.snippetStories = snippetStories;
	}
	
	public void setSnippetComments(List<Comment> snippetComments) {
		this.snippetComments = snippetComments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authorId;
		result = prime * result + ((snippetComments == null) ? 0 : snippetComments.hashCode());
		result = prime * result + snippetId;
		result = prime * result + ((snippetStories == null) ? 0 : snippetStories.hashCode());
		result = prime * result + ((snippetText == null) ? 0 : snippetText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Snippet other = (Snippet) obj;
		if (authorId != other.authorId)
			return false;
		if (snippetComments == null) {
			if (other.snippetComments != null)
				return false;
		} else if (!snippetComments.equals(other.snippetComments))
			return false;
		if (snippetId != other.snippetId)
			return false;
		if (snippetStories == null) {
			if (other.snippetStories != null)
				return false;
		} else if (!snippetStories.equals(other.snippetStories))
			return false;
		if (snippetText == null) {
			if (other.snippetText != null)
				return false;
		} else if (!snippetText.equals(other.snippetText))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Snippet [snippetId=" + snippetId + ", authorId=" + authorId + ", snippetText=" + snippetText
				+ ", snippetStories=" + snippetStories + ", snippetComments=" + snippetComments + "]";
	}
	
}
