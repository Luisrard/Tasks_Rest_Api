package com.nextline.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * To show the info of the comments as the:</br>
 * {@link #id} the id.<br>
 * {@link #comment} the comment.<br>
 * @author Luis
 */
@Data
public class CommentDto implements Serializable, IChangeable<Integer> {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Integer id;
    private String comment;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean toDelete = false;
}

