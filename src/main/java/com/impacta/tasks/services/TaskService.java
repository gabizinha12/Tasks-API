package com.impacta.tasks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.tasks.dto.TaskDTO;
import com.impacta.tasks.model.Task;
import com.impacta.tasks.repository.TaskRepository;
import com.impacta.tasks.services.exceptions.DatabaseException;
import com.impacta.tasks.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Transactional
	public TaskDTO insert(TaskDTO task) {
		Task objectTask = new Task();
		objectTask = taskRepository.save(objectTask);
		copyToDTO(task, objectTask);
		return new TaskDTO(objectTask);

	}

	@Transactional(readOnly = true)
	public TaskDTO findById(Long id) {
		Optional<Task> obj = taskRepository.findById(id);
		Task task = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new TaskDTO(task);

	}
	
	@Transactional(readOnly = true)
	public List<TaskDTO> findAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		return tasks.stream().map(t -> new TaskDTO(t)).toList();

	}

@Transactional
	public TaskDTO edit(Long id, TaskDTO dto) {
	try {
		Task entity = taskRepository.getReferenceById(id);
		entity = taskRepository.save(entity);
		copyToDTO(dto, entity);
		return new TaskDTO(entity);		
	} catch (EntityNotFoundException e) {
		throw new ResourceNotFoundException("The id doesnt exist " + id);
	}
	}

	public void delete(Long id) {
		try {
		taskRepository.deleteById(id);
		} catch(EmptyResultDataAccessException | DataIntegrityViolationException e) {
			throw new DatabaseException("An database error has occurred");
		}
		
	}

	public void copyToDTO(TaskDTO dto, Task task) {
		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		task.setDeadline(dto.getDeadline());
	}

}
