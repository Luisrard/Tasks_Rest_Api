package com.nextline.repositories;

import com.nextline.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TasksRepository extends JpaRepository<Task, String>, JpaSpecificationExecutor<Task> {

}