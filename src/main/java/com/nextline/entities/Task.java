package com.nextline.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * To persist the fields of the table <b>"tasks"</b> as the:</br>
 * {@link #id} the id.<br>
 * {@link #title} the title.<br>
 * {@link #description} the description of the task.<br>
 * {@link #title} the title.<br>
 * {@link #status} to indicate the status of the task either {@code true} to indicates that has been finished
 * or {@code false} to indicates that has not been finished.<br>
 * @author Luis
 */
@Data
@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "deathLine", nullable = false)
    private LocalDate deathLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", nullable = false)
    private User idUser;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "task")
    private List<Tag> tags;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "task")
    private List<Comment> comments;
}
