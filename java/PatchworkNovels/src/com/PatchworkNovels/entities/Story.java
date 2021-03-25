package com.PatchworkNovels.entities;

import java.util.ArrayList;
import java.util.List;

public class Story {
	
	// private fields
	
	private int storyId;
	private int storyAuthorId;
	private List<Snippet> storyText;
	private List<Comment> storyComments;
	private int storyRating;
	
	// constructors
	
	public Story() { }
	
	public Story(int storyAuthorId) {
		this.storyAuthorId = storyAuthorId;
		storyText = new ArrayList<Snippet>();
		storyComments = new ArrayList<Comment>();
		this.storyRating = 0;
	}
	
	// getters
	
	public int getStoryId() {
		return this.storyId;
	}
	
	public int getStoryAuthorId() {
		return this.storyAuthorId;
	}
	
	public List<Snippet> getStoryText() {
		return this.storyText;
	}
	
	public List<Comment> getStoryComments() {
		return this.storyComments;
	}
	
	public int getStoryRating() {
		return this.storyRating;
	}
	
	// setters
	
	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}
	
	public void setStoryAuthorId(int storyAuthorId) {
		this.storyAuthorId = storyAuthorId;
	}
	
	public void setStoryText(List<Snippet> storyText) {
		this.storyText = storyText;
	}
	
	public void setStoryComments(List<Comment> storyComments) {
		this.storyComments = storyComments;
	}
	
	public void setStoryRating(int storyRating) {
		this.storyRating = storyRating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + storyAuthorId;
		result = prime * result + ((storyComments == null) ? 0 : storyComments.hashCode());
		result = prime * result + storyId;
		result = prime * result + storyRating;
		result = prime * result + ((storyText == null) ? 0 : storyText.hashCode());
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
		Story other = (Story) obj;
		if (storyAuthorId != other.storyAuthorId)
			return false;
		if (storyComments == null) {
			if (other.storyComments != null)
				return false;
		} else if (!storyComments.equals(other.storyComments))
			return false;
		if (storyId != other.storyId)
			return false;
		if (storyRating != other.storyRating)
			return false;
		if (storyText == null) {
			if (other.storyText != null)
				return false;
		} else if (!storyText.equals(other.storyText))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Story [storyId=" + storyId + ", authorId=" + storyAuthorId + ", storyText=" + storyText + ", storyComments="
				+ storyComments + ", storyRating=" + storyRating + "]";
	}
	
}
