package com.impacta.tasks.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.impacta.tasks.model.Task;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class TaskDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate deadline;

	public TaskDTO(Task entity) {
		super();
		this.id = entity.getId();
		this.description = entity.getDescription();
		this.title = entity.getTitle();
		this.deadline = entity.getDeadline();

	}

	public TaskDTO() {
	}

	public TaskDTO(Long id, String title, String description, LocalDate deadline) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.deadline = deadline;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

}
