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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "User")
public class User {
	
	// private fields
	
	@Id
	@Column(name = "username", nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
	private String username;
	
	@Basic
	@Column(name = "password", nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
	private String password;
	
	@Lob
	@Column(name = "profileImage", nullable = true, columnDefinition = "BLOB")
	private String profileImage;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateJoined", nullable = false)
	private Date dateJoined;
	
	@OneToMany(targetEntity = Story.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "user_publishedStories")
	private List<Story> publishedStories;
	
	@OneToMany(targetEntity = Snippet.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "user_publishedSnippets")
	private List<Snippet> publishedSnippets;
	
	@OneToMany(targetEntity = Story.class, fetch = FetchType.EAGER)
	@JoinTable(name = "user_favoriteStories")
	private List<Story> favoriteStories;
	
	// constructors
	
	public User() {
		this.username = null;
		this.password = null;
		this.profileImage = null;
		this.dateJoined = new Date();
		this.publishedStories = new ArrayList<Story>();
		this.publishedSnippets = new ArrayList<Snippet>();
		this.favoriteStories = new ArrayList<Story>();
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.profileImage = null;
		this.dateJoined = new Date();
		this.publishedStories = new ArrayList<Story>();
		this.publishedSnippets = new ArrayList<Snippet>();
		this.favoriteStories = new ArrayList<Story>();
	}
	
	// getters
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getProfileImage() {
		return this.profileImage;
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
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
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
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		} else if (!dateJoined.toString().equals(other.dateJoined.toString()))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		return "User [username=" + username + ", password=" + password + ", dateJoined=" + dateJoined + "]";
	}

	
}
