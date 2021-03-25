package com.PatchworkNovels.entities;

public class Comment {
	
	// private fields
	
	private int commentId;
	private int commentAuthorId;
	private int storyId;
	private String commentText;
	private int commentRating;
	
	// constructors
	
	public Comment() { }
	
	public Comment(int commentAuthorId, int storyId, String commentText) {
		this.commentAuthorId = commentAuthorId;
		this.storyId = storyId;
		this.commentText = commentText;
		this.commentRating = 0;
	}
	
	// getters
	
	public int getCommentId() {
		return this.commentId;
	}
	
	public int getCommentAuthorId() {
		return this.commentAuthorId;
	}
	
	public int getStoryId() {
		return this.storyId;
	}
	
	public String getCommentText() {
		return this.commentText;
	}
	
	public int getCommentRating() {
		return this.commentRating;
	}
	
	// setters
	
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public void setAuthorCommentId(int commentAuthorId) {
		this.commentAuthorId = commentAuthorId;
	}
	
	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}
	
	public void setCommenText(String commentText) {
		this.commentText = commentText;
	}
	
	public void setCommentRating(int commentRating) {
		this.commentRating = commentRating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentAuthorId;
		result = prime * result + commentId;
		result = prime * result + commentRating;
		result = prime * result + ((commentText == null) ? 0 : commentText.hashCode());
		result = prime * result + storyId;
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
		Comment other = (Comment) obj;
		if (commentAuthorId != other.commentAuthorId)
			return false;
		if (commentId != other.commentId)
			return false;
		if (commentRating != other.commentRating)
			return false;
		if (commentText == null) {
			if (other.commentText != null)
				return false;
		} else if (!commentText.equals(other.commentText))
			return false;
		if (storyId != other.storyId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentAuthorId=" + commentAuthorId + ", storyId=" + storyId
				+ ", commentText=" + commentText + ", commentRating=" + commentRating + "]";
	}
	
}
