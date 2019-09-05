package com.uros.flightAdvisor.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uros.flightAdvisor.domain.administration.Comment;
import com.uros.flightAdvisor.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CityService cityService;

	public Comment findOne(Long id) {
		return commentRepository.findById(id).orElse(null);
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	public List<Comment> findByCityLimitNewest(Long cityId, Integer limit) {
		if (limit != null && limit != 0) {
			return commentRepository.findByCityIdOrderByCreationTimeDesc(cityId, PageRequest.of(0, limit));
		}
		return commentRepository.findByCityIdOrderByCreationTimeDesc(cityId);
	}

	public Comment save(Comment comment, Long cityId) {
		comment.setCity(cityService.findOne(cityId));
		return commentRepository.save(comment);
	}

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	public Comment update(Long id, Comment newComment) throws EntityNotFoundException {
		Comment comment = findOne(id);
		if (comment == null) {
			throw new EntityNotFoundException("Comment with id " + id + " not found");
		}
		if (newComment.getContent() != null)
			comment.setContent(newComment.getContent());
		if (newComment.getCreationTime() != null)
			comment.setCreationTime(newComment.getCreationTime());
		return save(comment);
	}

	public void delete(Comment comment) {
		comment.setDeleted(true);
		commentRepository.save(comment);
	}

	public void delete(Long id) {
		Comment comment = findOne(id);
		if (comment == null) {
			throw new EntityNotFoundException("Comment with id " + id + " not found");
		}
		delete(comment);
	}
}
