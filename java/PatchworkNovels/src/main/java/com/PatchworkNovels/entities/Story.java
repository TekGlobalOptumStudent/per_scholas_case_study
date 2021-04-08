package com.PatchworkNovels.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@Column(name = "storyTitle", nullable = false, columnDefinition = "VARCHAR(50) NOT NULL")
	private String storyTitle;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "storyAuthor", referencedColumnName = "username")
	private User storyAuthor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "storyTimePosted", nullable = false)
	private Date storyTimePosted;
	
	@ManyToMany(targetEntity = Snippet.class, fetch = FetchType.EAGER)
	@JoinTable(name = "story_storyText")
	private List<Snippet> storyText;
	
	@OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "story_storyComments")
	private List<Comment> storyComments;
	
	@Basic
	@Column(name = "storyRating", nullable = false, columnDefinition = "INT NOT NULL")
	private int storyRating;
	
	// constructors
	
	public Story() { }
	
	public Story(String storyTitle, User storyAuthor, List<Snippet> storyText) {
		this.storyTitle = storyTitle;
		this.storyAuthor = storyAuthor;
		this.storyTimePosted = new Date();
		this.storyText = storyText;
		this.storyComments = new ArrayList<Comment>();
		this.storyRating = 0;
	}
	
	// getters
	
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
	
	public void setStoryAuthor(User storyAuthor) {
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

	// mutator methods
	
	public void setStoryTimePostedNow() {
		this.storyTimePosted = new Date();
	}


	// standard methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((storyAuthor == null) ? 0 : storyAuthor.hashCode());
		result = prime * result + storyRating;
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
		if (storyTimePosted == null) {
			if (other.storyTimePosted != null)
				return false;
		} else if (!storyTimePosted.toString().equals(other.storyTimePosted.toString()))
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
		return "Story [storyTitle=" + storyTitle + ", storyAuthor=" + storyAuthor + ", storyTimePosted="
				+ storyTimePosted + ", storyRating=" + storyRating + "]";
	}
	
}
