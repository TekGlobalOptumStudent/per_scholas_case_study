package com.PatchworkNovels.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Snippet")
public class Snippet {
	
	// private fields
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "snippetId", nullable = false, columnDefinition = "INT NOT NULL")
	private int snippetId;
	
	@ManyToOne
	@JoinColumn(name = "snippetAuthorId", referencedColumnName = "userId")
	private User snippetAuthor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "snippetTimePosted", nullable = false)
	private Date snippetTimePosted;
	
	@Basic
	@Column(name = "snippetText", nullable = false, columnDefinition = "TEXT NOT NULL")
	private String snippetText;
	
	@ManyToMany(targetEntity = Story.class, fetch = FetchType.EAGER)
	@JoinTable(name = "snippet_snippetStories")
	private List<Story> snippetStories;
	
	@OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "snippet_snippetComments")
	private List<Comment> snippetComments;
	
	// constructors
	
	public Snippet() { }
	
	public Snippet(User snippetAuthor, String snippetText) {
		this.snippetAuthor = snippetAuthor;
		this.snippetText = snippetText;
		this.snippetTimePosted = new Date();
		this.snippetStories = new ArrayList<Story>();
		this.snippetComments = new ArrayList<Comment>();
	}
	
	// getters
	
	public int getSnippetId() {
		return this.snippetId;
	}
	
	public User getSnippetAuthor() {
		return this.snippetAuthor;
	}
	
	public Date getSnippetTimePosted() {
		return this.snippetTimePosted;
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
	
	public void setSnippetAuthor(User snippetAuthor) {
		this.snippetAuthor = snippetAuthor;
	}
	
	public void setSnippetTimePosted(Date snippetTimePosted) {
		this.snippetTimePosted = snippetTimePosted;
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
	
	// standard methods

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((snippetAuthor == null) ? 0 : snippetAuthor.hashCode());
		result = prime * result + ((snippetComments == null) ? 0 : snippetComments.hashCode());
		result = prime * result + snippetId;
		result = prime * result + ((snippetStories == null) ? 0 : snippetStories.hashCode());
		result = prime * result + ((snippetText == null) ? 0 : snippetText.hashCode());
		result = prime * result + ((snippetTimePosted == null) ? 0 : snippetTimePosted.hashCode());
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
		if (snippetAuthor == null) {
			if (other.snippetAuthor != null)
				return false;
		} else if (!snippetAuthor.equals(other.snippetAuthor))
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
		if (snippetTimePosted == null) {
			if (other.snippetTimePosted != null)
				return false;
		} else if (!snippetTimePosted.equals(other.snippetTimePosted))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Snippet [snippetId=" + snippetId + ", snippetAuthor=" + snippetAuthor + ", snippetTimePosted=" + snippetTimePosted
				+ ", snippetText=" + snippetText + ", snippetStories=" + snippetStories + ", snippetComments="
				+ snippetComments + "]";
	}
	
	// other methods
	
	public void setSnippetTimePostedNow() {
		this.snippetTimePosted = new Date();
	}

}
