package com.impacta.tasks.resources;

import java.awt.print.Book;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.impacta.tasks.dto.TaskDTO;
import com.impacta.tasks.model.Task;
import com.impacta.tasks.services.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin("origins = *")
@RequestMapping(value ="/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	
	@Operation(summary = "Get a task by id")
	@GetMapping("/task/{id}")
	@Parameter(description = "id of task to be searched")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Found the task", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Book.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Task not found", 
			    content = @Content) })
	public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
		TaskDTO task = taskService.findById(id);
		return ResponseEntity.ok().body(task);
	}

	@Operation(summary = "Get all tasks")
	@GetMapping()
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Found the tasks", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Book.class)) }), 
			  @ApiResponse(responseCode = "404", description = "Tasks not found", 
			    content = @Content) })
	public ResponseEntity<List<TaskDTO>> findAll() {
		List<TaskDTO> tasks = taskService.findAllTasks();
		return ResponseEntity.ok().body(tasks);
	}
	
	@Operation(summary = "Create task")
	@PostMapping("/task/create")
	@Parameter(description = "Request body of the TaskDTO")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Created task successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Task.class)) }),
			  @ApiResponse(responseCode = "500", description = "Invalid fields for task description or title", 
			    content = @Content) })
	public ResponseEntity<TaskDTO> insertTask(@RequestBody TaskDTO dto) {
		dto = taskService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);

	}
	@Operation(summary = "Update a task")
	@PutMapping("/update/{id}")
	@Parameter(description = "id of task to be updated")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Found the task", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Task.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Task not found", 
			    content = @Content) })
	public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO dto) {
		dto = taskService.edit(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	@Operation(summary = "Delete a task")
	@DeleteMapping("/delete/{id}")
	@Parameter(description = "id of task to be deleted")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "201", description = "Deleted successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Task.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Task not found", 
			    content = @Content) })
	public ResponseEntity<TaskDTO> delete(@PathVariable Long id) {
		taskService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
