package com.uros.flightAdvisor.domain.administration;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Comment entity.
 * 
 * @author Uros
 *
 */
@Entity
@Where(clause = "deleted = 'false'")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String content;

	private LocalDateTime creationTime;

	private LocalDateTime modifiedTime;

	@JsonIgnore
	@ManyToOne
	private City city;

	@NotNull
	private boolean deleted = false;

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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", creationTime=" + creationTime + ", modifiedTime="
				+ modifiedTime + ", city=" + city + ", deleted=" + deleted + "]";
	}
}
