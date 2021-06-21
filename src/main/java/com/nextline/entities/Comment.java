package com.nextline.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * To persist the fields of the table <b>"comments"</b> as the:</br>
 * {@link #id} the id.<br>
 * {@link #comment} the comment.<br>
 * {@link #task} the task that this comment belongs.<br>
 * @author Luis
 */
@Data
@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTask", nullable = false)
    private Task task;
}
