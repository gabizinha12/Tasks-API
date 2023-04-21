package com.impacta.tasks.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.tasks.dto.TaskDTO;
import com.impacta.tasks.model.Task;
import com.impacta.tasks.repository.TaskRepository;

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
	Task task = obj.orElseThrow();
	return new TaskDTO(task);
	
}
	
@Transactional
	public TaskDTO edit(Long id, TaskDTO dto) {
		Task entity = taskRepository.getReferenceById(id);
		entity = taskRepository.save(entity);
		copyToDTO(dto, entity);
		return new TaskDTO(entity);		
	}
	
	
	public void delete(Long id) {
		taskRepository.deleteById(id);
		
	}
	
	public void copyToDTO(TaskDTO dto, Task task) {
		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		task.setDeadline(dto.getDeadline());
	}
	 
}
