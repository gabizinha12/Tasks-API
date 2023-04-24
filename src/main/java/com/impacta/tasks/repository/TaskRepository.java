package com.impacta.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impacta.tasks.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
