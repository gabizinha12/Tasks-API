package com.impacta.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impacta.tasks.model.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {

	

}
