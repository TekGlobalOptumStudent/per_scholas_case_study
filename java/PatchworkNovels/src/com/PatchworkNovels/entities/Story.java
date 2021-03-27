package com.PatchworkNovels.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Story")
public class Story {
	
	// private fields
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "storyId", nullable = false, columnDefinition = "INT NOT NULL")
	private int storyId;
	
	@ManyToOne
	@JoinColumn(name = "storyAuthorId", referencedColumnName = "userId")
	private User storyAuthor;
	
	@Basic
	@Column(name = "storyTitle", nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
	private String storyTitle;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "storyTimePosted", nullable = false)
	private Date storyTimePosted;
	
	@OneToMany(targetEntity = Snippet.class, fetch = FetchType.EAGER)
	private List<Snippet> storyText;
	
	@OneToMany(targetEntity = Comment.class, fetch = FetchType.EAGER)
	private List<Comment> storyComments;
	
	@Basic
	@Column(name = "storyRating", nullable = false, columnDefinition = "INT NOT NULL")
	private int storyRating;
	
	// constructors
	
	public Story() { }
	
	public Story(User storyAuthor, String storyTitle) {
		this.storyAuthor = storyAuthor;
		this.storyTitle = storyTitle;
		this.storyTimePosted = new Date();
		this.storyText = new ArrayList<Snippet>();
		this.storyComments = new ArrayList<Comment>();
		this.storyRating = 0;
	}
	
	// getters
	
	public int getStoryId() {
		return this.storyId;
	}
	
	public User getStoryAuthor() {
		return this.storyAuthor;
	}
	
	public String getStoryTitle() {
		return this.storyTitle;
	}
	
	public Date getStoryTimePosted() {
		return this.storyTimePosted;
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
	
	public void setStoryAuthorId(User storyAuthor) {
		this.storyAuthor = storyAuthor;
	}
	
	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}
	
	public void setStoryTimePosted(Date storyTimePosted) {
		this.storyTimePosted = storyTimePosted;
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
	
	// standard methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((storyAuthor == null) ? 0 : storyAuthor.hashCode());
		result = prime * result + ((storyComments == null) ? 0 : storyComments.hashCode());
		result = prime * result + storyId;
		result = prime * result + storyRating;
		result = prime * result + ((storyText == null) ? 0 : storyText.hashCode());
		result = prime * result + ((storyTimePosted == null) ? 0 : storyTimePosted.hashCode());
		result = prime * result + ((storyTitle == null) ? 0 : storyTitle.hashCode());
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
		if (storyAuthor == null) {
			if (other.storyAuthor != null)
				return false;
		} else if (!storyAuthor.equals(other.storyAuthor))
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
		if (storyTimePosted == null) {
			if (other.storyTimePosted != null)
				return false;
		} else if (!storyTimePosted.equals(other.storyTimePosted))
			return false;
		if (storyTitle == null) {
			if (other.storyTitle != null)
				return false;
		} else if (!storyTitle.equals(other.storyTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Story [storyId=" + storyId + ", storyAuthor=" + storyAuthor + ", storyTitle=" + storyTitle
				+ ", storyTimePosted=" + storyTimePosted + ", storyText=" + storyText + ", storyComments="
				+ storyComments + ", storyRating=" + storyRating + "]";
	}

	// other methods
	
	public void setStoryTimePostedNow() {
		this.storyTimePosted = new Date();
	}

}
