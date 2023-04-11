package com.impacta.tasks.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.impacta.tasks.dto.TaskDTO;
import com.impacta.tasks.services.TaskService;

@RestController
@RequestMapping(value ="/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks/{id}")
	public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
		TaskDTO task = taskService.findById(id);
		return ResponseEntity.ok().body(task);
	}

	@PostMapping("/task/create")
	public ResponseEntity<TaskDTO> insertTask(@RequestBody TaskDTO dto) {
		dto = taskService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO dto) {
		dto = taskService.edit(id, dto);
		return ResponseEntity.ok().body(dto);
	}

}
