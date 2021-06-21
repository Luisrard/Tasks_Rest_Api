package com.nextline.services;

import com.nextline.entities.Task;
import com.nextline.entities.User;
import com.nextline.mappers.TaskMapper;
import com.nextline.mappers.UserMapper;
import com.nextline.models.CommentDto;
import com.nextline.models.TaskBasicInfoDto;
import com.nextline.models.TaskDto;
import com.nextline.models.UserDto;
import com.nextline.repositories.CommentsRepository;
import com.nextline.repositories.TagsRepository;
import com.nextline.repositories.TasksRepository;
import com.nextline.repositories.UsersRepository;
import com.nextline.tools.ChangeableTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Service to manage the tasks
 */
@Transactional
@RequiredArgsConstructor
@Service
public class TaskService implements CrudService<TaskDto, Object>{
    private final TaskMapper taskMapper;
    private final TasksRepository tasksRepository;
    private final CommentsRepository commentRepository;
    private final TagsRepository tagsRepository;

    /**
     * To search a task with the id of the task
     * @param id the id.
     * @return the task in the case of exist or a null if has not been found.
     */
    public Task getTask(Integer id){
        if(id != null){
            return tasksRepository.findById(id).orElse(null);
        }
        return null;
    }

    /**
     * To find the all the tasks of the user provided
     * @param idUser the id of the user
     * @return the list in a basic form of the tasks
     */
    public List<TaskBasicInfoDto> getBasicTasks(Integer idUser){
        if(idUser != null){
            return taskMapper.taskListToTaskBasicInfoDtoList(tasksRepository.findAllByUser(idUser));
        }
        return null;
    }

    @Override
    public TaskDto find(Integer id) {
        return taskMapper.taskToTaskDto(getTask(id));
    }

    @Override
    public TaskDto save(TaskDto request) throws RuntimeException {
        if(request != null){
            request.setId(null);
            Task task = taskMapper.taskToTaskDto(request);
            Task response = tasksRepository.save(task);
            return taskMapper.taskToTaskDto(response);
        }
        return null;
    }

    @Override
    public TaskDto update(TaskDto request) throws RuntimeException {
        if(request != null){
            Task task = getTask(request.getId());
            if(task != null) {
                deleteItems(request);
                taskMapper.updateExistentTask(task, request);
                Task response = tasksRepository.save(task);
                return taskMapper.taskToTaskDto(response);
            }
        }
        return null;
    }

    /**
     * Function to delete the items that has to be deleted of the task.
     * @param request The task
     */
    private void deleteItems(TaskDto request) {
        //Comments
        List<Integer> commentsToDelete = ChangeableTools.getItemsToDelete(request.getComments());
        for (Integer commentId : commentsToDelete) {
            commentRepository.deleteById(commentId);
        }
        List<Integer> tagsToDelete = ChangeableTools.getItemsToDelete(request.getTags());
        for (Integer tagId : tagsToDelete) {
            tagsRepository.deleteById(tagId);
        }
    }

    @Override
    public boolean delete(Integer id) throws RuntimeException {
        if(id != null){
            tasksRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
