package com.impacta.tasks.dto;

import com.impacta.tasks.model.Task;

public class TaskDTO {

	private Long id;
	private String title;
	private String description;

	public TaskDTO(Task entity) {
		super();
		this.id = entity.getId();
		this.description = entity.getDescription();
		this.title = entity.getTitle();

	}

	public TaskDTO() {
	}
	
	public TaskDTO(Long id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
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



}
