package com.nextline.models;

import lombok.Data;

import java.io.Serializable;

/**
 * To show the info of the users as the:</br>
 * {@link #id} the id.<br>
 * {@link #nickName} the nick name of the user.<br>
 * @author Luis
 */
@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nickName;
}