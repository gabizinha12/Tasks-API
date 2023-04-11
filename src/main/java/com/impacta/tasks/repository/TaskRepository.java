package com.impacta.tasks.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.impacta.tasks.model.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {

	

}
