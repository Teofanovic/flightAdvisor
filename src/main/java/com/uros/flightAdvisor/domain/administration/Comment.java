package com.uros.flightAdvisor.domain.administration;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

/**
 * Comment entity.
 * 
 * @author Uros
 *
 */
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String content;

	private LocalDateTime creationTime;

	private LocalDateTime modifiedTime;

	@ManyToOne(fetch = FetchType.LAZY)
	private City city;

	@PrePersist
	protected void onCreate() {
		this.creationTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.modifiedTime = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", creationTime=" + creationTime + ", modifiedTime="
				+ modifiedTime + ", city=" + city + "]";
	}
}
