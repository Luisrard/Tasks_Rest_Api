package com.nextline.repositories;

import com.nextline.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TasksRepository extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task> {
    @Query("SELECT t FROM Task t WHERE t.idUser = ?1")
    List<Task> findAllByUser(Integer idUser);
}