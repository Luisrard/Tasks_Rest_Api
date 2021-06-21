package com.nextline.controllers;

import com.nextline.models.TaskBasicInfoDto;
import com.nextline.models.TaskDto;
import com.nextline.services.CrudService;
import com.nextline.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller to map the http request of the task interface
 * @author Luis
 */
@RestController
@RequestMapping("/tasks")
public class TaskController extends CrudController<TaskDto, Object, TaskService>{
    private final TaskService taskService;
    public TaskController(CrudService<TaskDto, Object> service) {
        super(service, TaskController.class);
        taskService = (TaskService) service;
    }

    @GetMapping
    public ResponseEntity<List<TaskBasicInfoDto>> getListTaskByUser(Integer idUser){
        logger.info("Obtaining basic task list of user "+ idUser + "...");
        ResponseEntity<List<TaskBasicInfoDto>> response = ResponseEntity.ok(taskService.getBasicTasks(idUser));
        logger.info("Basic task list obtained...");
        return response;
    }

    @GetMapping("/find")
    @Override
    public ResponseEntity<TaskDto> findObject(int id) {
        return super.findObject(id);
    }

    @PostMapping
    @Override
    public ResponseEntity<TaskDto> saveObject(@RequestBody TaskDto requestObject) {
        return super.saveObject(requestObject);
    }

    @PutMapping
    @Override
    public ResponseEntity<TaskDto> updateObject(@RequestBody TaskDto requestObject) {
        return super.updateObject(requestObject);
    }

    @DeleteMapping
    @Override
    public ResponseEntity<?> deleteObject(int id) {
        return super.deleteObject(id);
    }

    @Override
    protected String getEntityName() {
        return "Task";
    }

    @Override
    protected String getEntityPluralName() {
        return "Tasks";
    }
}
