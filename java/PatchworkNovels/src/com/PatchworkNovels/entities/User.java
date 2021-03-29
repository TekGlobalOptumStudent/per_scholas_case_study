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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId", nullable = false, columnDefinition = "INT NOT NULL")
	private int userId;
	
	@Basic
	@Column(name = "username", nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
	private String username;
	
	@Basic
	@Column(name = "password", nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateJoined", nullable = false)
	private Date dateJoined;
	
	@OneToMany(targetEntity = Story.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_publishedStories")
	private List<Story> publishedStories;
	
	@OneToMany(targetEntity = Snippet.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_publishedSnippets")
	private List<Snippet> publishedSnippets;
	
	@OneToMany(targetEntity = Story.class, fetch = FetchType.EAGER)
	@JoinTable(name = "user_favoriteStories")
	private List<Story> favoriteStories;
	
	// constructors
	
	public User() {
		this.username = null;
		this.password = null;
		this.dateJoined = new Date();
		this.publishedStories = new ArrayList<Story>();
		this.publishedSnippets = new ArrayList<Snippet>();
		this.favoriteStories = new ArrayList<Story>();
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.dateJoined = new Date();
		this.publishedStories = new ArrayList<Story>();
		this.publishedSnippets = new ArrayList<Snippet>();
		this.favoriteStories = new ArrayList<Story>();
	}
	
	// getters
	
	public int getUserId() {
		return this.userId;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
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
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((publishedSnippets == null) ? 0 : publishedSnippets.hashCode());
		result = prime * result + ((publishedStories == null) ? 0 : publishedStories.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", dateJoined="
				+ dateJoined + "]";
	}

}
