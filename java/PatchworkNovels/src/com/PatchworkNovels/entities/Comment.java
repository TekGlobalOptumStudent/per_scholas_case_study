package com.PatchworkNovels.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Comment")
public class Comment {
	
	// private fields
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "commentId", nullable = false, columnDefinition = "INT NOT NULL")
	private int commentId;
	
	@ManyToOne
	@JoinColumn(name = "commentAuthorId", referencedColumnName = "userId")
	private User commentAuthor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "commentTimePosted", nullable = false)
	private Date commentTimePosted;
	
	@Basic
	@Column(name = "commentText", nullable = false, columnDefinition = "TEXT NOT NULL")
	private String commentText;
	
	@Basic
	@Column(name = "commentRating", nullable = false, columnDefinition = "INT NOT NULL")
	private int commentRating;
	
	// constructors
	
	public Comment() { }
	
	public Comment(User commentAuthor, String commentText) {
		this.commentAuthor = commentAuthor;
		this.commentTimePosted = new Date();
		this.commentText = commentText;
		this.commentRating = 0;
	}
	
	// getters
	
	public int getCommentId() {
		return this.commentId;
	}
	
	public User getCommentAuthor() {
		return this.commentAuthor;
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
	
	public void setCommentAuthor(User commentAuthor) {
		this.commentAuthor = commentAuthor;
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
		result = prime * result + ((commentAuthor == null) ? 0 : commentAuthor.hashCode());
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
		if (commentAuthor == null) {
			if (other.commentAuthor != null)
				return false;
		} else if (!commentAuthor.equals(other.commentAuthor))
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
		return "Comment [commentId=" + commentId + ", commentAuthor=" + commentAuthor + ", commentTimePosted="
				+ commentTimePosted + ", commentText=" + commentText + ", commentRating=" + commentRating + "]";
	}
	
	// other methods
	
	public void setCommentTimePostedNow() {
		this.commentTimePosted = new Date();
	}

}
