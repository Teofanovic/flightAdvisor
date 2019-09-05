package com.uros.flightAdvisor.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uros.flightAdvisor.domain.administration.Comment;
import com.uros.flightAdvisor.service.CommentService;

@RestController
@RequestMapping("/cities")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping(value = "/{cityId}/comments/{id}", produces = "application/json")
	public ResponseEntity<Comment> getComment(@PathVariable Long id) {
		return ResponseEntity.ok(commentService.findOne(id));
	}

	@GetMapping(value = "/{cityId}/comments", produces = "application/json")
	public ResponseEntity<List<Comment>> getComments(@PathVariable("cityId") Long cityId,
			@RequestParam(name = "limit", required = false) Integer limit) {
		return ResponseEntity.ok(commentService.findByCityLimitNewest(cityId, limit));
	}

	@PostMapping(value = "/{cityId}/comments", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Comment> postComment(@PathVariable("cityId") Long cityId, @RequestBody Comment comment) {
		comment = commentService.save(comment, cityId);
		try {
			return ResponseEntity.created(new URI(String.format("/cities/%s/comments/%s", cityId, comment.getId())))
					.body(comment);
		} catch (URISyntaxException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping(value = "/{cityId}/comments/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Comment> editComment(@PathVariable("cityId") Long cityId, @PathVariable Long id,
			@RequestBody Comment comment) {
		try {
			return ResponseEntity.ok(commentService.update(id, comment));
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/{cityId}/comments/{id}")
	public ResponseEntity<Comment> deleteComment(@PathVariable("cityId") Long cityId, @PathVariable Long id) {
		try {
			commentService.delete(id);
			return ResponseEntity.ok().build();
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
}
