package com.PatchworkNovels.entities;

import java.util.Date;

public class Comment {
	
	// private fields
	
	private int commentId;
	private int commentAuthorId;
	private Date commentTimePosted;
	private String commentText;
	private int commentRating;
	
	// constructors
	
	public Comment() { }
	
	public Comment(int commentAuthorId, String commentText) {
		this.commentAuthorId = commentAuthorId;
		this.commentTimePosted = new Date();
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
	
	public Date getCommentTimePosted() {
		return this.commentTimePosted;
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
	
	public void setCommentTimePosted(Date commentTimePosted) {
		this.commentTimePosted = commentTimePosted;
	}
	
	public void setCommenText(String commentText) {
		this.commentText = commentText;
	}
	
	public void setCommentRating(int commentRating) {
		this.commentRating = commentRating;
	}
	
	// standard methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentAuthorId;
		result = prime * result + commentId;
		result = prime * result + commentRating;
		result = prime * result + ((commentText == null) ? 0 : commentText.hashCode());
		result = prime * result + ((commentTimePosted == null) ? 0 : commentTimePosted.hashCode());
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
		if (commentTimePosted == null) {
			if (other.commentTimePosted != null)
				return false;
		} else if (!commentTimePosted.equals(other.commentTimePosted))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentAuthorId=" + commentAuthorId + ", commentTimePosted="
				+ commentTimePosted + ", commentText=" + commentText + ", commentRating=" + commentRating + "]";
	}
	
	// other methods
	
	public void setCommentTimePostedNow() {
		this.commentTimePosted = new Date();
	}
}
