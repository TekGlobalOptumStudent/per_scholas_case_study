package com.PatchworkNovels.repo;


import com.PatchworkNovels.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
	
	public Story getByStoryId(int storyId);
	
}
