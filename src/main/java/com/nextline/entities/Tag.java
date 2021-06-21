package com.nextline.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * To persist the fields of the table <b>"tags"</b> as the:</br>
 * {@link #id} the id.<br>
 * {@link #tag} the tag.<br>
 * {@link #task} the task that this task belongs.<br>
 * @author Luis
 */
@Data
@Entity
@Table(name = "tags")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tag", nullable = false)
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTask", nullable = false)
    private Task task;
}
