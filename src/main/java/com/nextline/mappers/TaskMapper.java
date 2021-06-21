package com.nextline.mappers;


import com.nextline.entities.Comment;
import com.nextline.entities.Tag;
import com.nextline.entities.Task;
import com.nextline.entities.User;
import com.nextline.models.CommentDto;
import com.nextline.models.TagDto;
import com.nextline.models.TaskBasicInfoDto;
import com.nextline.models.TaskDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TaskMapper {
    TaskDto taskToTaskDto(Task task);

    @Named("TaskBasicInfoDto")
    TaskBasicInfoDto taskToTaskBasicInfoDto(Task task);

    @IterableMapping(qualifiedByName = "TaskBasicInfoDto")
    List<TaskBasicInfoDto> taskListToTaskBasicInfoDtoList(List<Task> tasks);

    Task taskToTaskDto(TaskDto taskDto);

    /**
     * Function to update a existence entity of {@link Task}.<br>
     * @param task the task to update.
     * @param taskDto the task dto to fetch the task.
     */
    default void  updateExistentTask(Task task, TaskDto taskDto) {
        if ( taskDto == null ) {
            return;
        }
        if ( taskDto.getIdUser() != null ) {
            task.setIdUser(taskDto.getId());
        }
        if ( taskDto.getTitle() != null ) {
            task.setTitle( taskDto.getTitle() );
        }
        if ( taskDto.getDescription() != null ) {
            task.setDescription( taskDto.getDescription() );
        }
        task.setStatus( taskDto.isStatus() );
        if ( taskDto.getDeathLine() != null ) {
            task.setDeathLine( taskDto.getDeathLine() );
        }
        List<Tag> list = tagDtoListToTagList( taskDto.getTags() );
        if ( list != null ) {
            task.setTags( list );
        }
        List<Comment> list1 = commentDtoListToCommentList( taskDto.getComments() );
        if ( list1 != null ) {
            task.setComments( list1 );
        }

        updateTask( task );
    }

    /**
     * To set the link of the internal list of tha task
     * @param task the task to link.
     */
    @AfterMapping
    default void updateTask(@MappingTarget Task task)
    {
        if(task != null)
        {
            if(task.getTags() != null)
                for(Tag tag : task.getTags()){
                    tag.setTask(task);
                }
            if(task.getComments() != null)
                for (Comment comment : task.getComments()){
                    comment.setTask(task);
                }
        }
    }

    @Mapping(target = "task", ignore = true)
    Tag tagDtoToTag(TagDto tagDto);

    TagDto tagToTagDto(Tag tag);

    CommentDto commentToCommentDto(Comment comment);

    @Mapping(target = "task", ignore = true)
    Comment commentDtoToComment(CommentDto commentDto);

    List<Tag> tagDtoListToTagList(List<TagDto> tagDtoList);
    List<Comment> commentDtoListToCommentList(List<CommentDto> list);
}
