package com.nextline.models;

import lombok.Data;

import java.time.LocalDate;

/**
 * To show the basic info of the tasks as the:</br>
 * {@link #id} the id.<br>
 * {@link #title} the title.<br>
 * {@link #description} the description of the task.<br>
 * {@link #title} the title.<br>
 * {@link #status} to indicate the status of the task either {@code true} to indicates that has been finished
 * or {@code false} to indicates that has not been finished.<br>
 * @author Luis
 */
@Data
public class TaskBasicInfoDto {
    protected Integer id;
    protected String title;
    protected String description;
    protected boolean status;
    protected LocalDate deathLine;

    @Override
    public String toString() {
        return "TaskBasicInfoDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", deathLine=" + deathLine +
                '}';
    }
}
