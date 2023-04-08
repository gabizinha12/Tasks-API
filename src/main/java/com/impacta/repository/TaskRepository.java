package com.impacta.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.impacta.model.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {

	

}
