package com.nextline.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * To show the full info of the tasks as the:</br>
 * {@link #id} the id.<br>
 * {@link #title} the title.<br>
 * {@link #description} the description of the task.<br>
 * {@link #title} the title.<br>
 * {@link #status} to indicate the status of the task either {@code true} to indicates that has been finished
 * or {@code false} to indicates that has not been finished.<br>
 * {@link #tags} the tags of the task.<br>
 * {@link #comments} the comments of the task.<br>
 * @author Luis
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskDto extends TaskBasicInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idUser;
    private String mandated;
    private List<TagDto> tags;
    private List<CommentDto> comments;
}

