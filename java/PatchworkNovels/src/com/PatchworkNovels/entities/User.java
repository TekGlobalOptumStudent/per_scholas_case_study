package com.PatchworkNovels.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	
	private int userId;
	private Date dateJoined;
	private List<Story> publishedStories;
	private List<Snippet> publishedSnippets;
	private List<Story> favoriteStories;
	
	// constructors
	
	public User() { 
		this.dateJoined = new Date();
		this.publishedStories = new ArrayList<Story>();
		this.publishedSnippets = new ArrayList<Snippet>();
		this.favoriteStories = new ArrayList<Story>();
	}
	
	// getters
	
	public int getUserId() {
		return this.userId;
	}
	
	public Date getDateJoined() {
		return this.dateJoined;
	}
	
	public List<Story> getPublishedStories() {
		return this.publishedStories;
	}
	
	public List<Snippet> getPublishedSnippets() {
		return this.publishedSnippets;
	}
	
	public List<Story> getFavoriteStories() {
		return this.favoriteStories;
	}
	
	// setters
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}
	
	public void setPublishedStories(List<Story> publishedStories) {
		this.publishedStories = publishedStories;
	}
	
	public void setPublishedSnippets(List<Snippet> publishedSnippets) {
		this.publishedSnippets = publishedSnippets;
	}
	
	public void setFavoriteStories(List<Story> favoriteStories) {
		this.favoriteStories = favoriteStories;
	}
	
	// standard methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateJoined == null) ? 0 : dateJoined.hashCode());
		result = prime * result + ((favoriteStories == null) ? 0 : favoriteStories.hashCode());
		result = prime * result + ((publishedSnippets == null) ? 0 : publishedSnippets.hashCode());
		result = prime * result + ((publishedStories == null) ? 0 : publishedStories.hashCode());
		result = prime * result + userId;
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
		User other = (User) obj;
		if (dateJoined == null) {
			if (other.dateJoined != null)
				return false;
		} else if (!dateJoined.equals(other.dateJoined))
			return false;
		if (favoriteStories == null) {
			if (other.favoriteStories != null)
				return false;
		} else if (!favoriteStories.equals(other.favoriteStories))
			return false;
		if (publishedSnippets == null) {
			if (other.publishedSnippets != null)
				return false;
		} else if (!publishedSnippets.equals(other.publishedSnippets))
			return false;
		if (publishedStories == null) {
			if (other.publishedStories != null)
				return false;
		} else if (!publishedStories.equals(other.publishedStories))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", dateJoined=" + dateJoined + ", publishedStories=" + publishedStories
				+ ", publishedSnippets=" + publishedSnippets + ", favoriteStories=" + favoriteStories + "]";
	}
	
}
