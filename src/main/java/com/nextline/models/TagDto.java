package com.nextline.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * To show the info of the tags as the:</br>
 * {@link #id} the id.<br>
 * {@link #tag} the tag.<br>
 * @author Luis
 */
@Data
public class TagDto implements Serializable, IChangeable<Integer> {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Integer id;
    private String tag;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean toDelete = false;

}

