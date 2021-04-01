package com.PatchworkNovels.repo;


import com.PatchworkNovels.entities.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, Integer> {
	
	public Snippet getBySnippetId(int snippetId);
	
}
