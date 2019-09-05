package com.uros.flightAdvisor.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uros.flightAdvisor.domain.administration.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByCityIdOrderByCreationTimeDesc(Long cityId, Pageable pageable);
	
	List<Comment> findByCityIdOrderByCreationTimeDesc(Long cityId);
}
