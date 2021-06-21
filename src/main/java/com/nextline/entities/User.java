package com.nextline.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * To persist the fields of the table <b>"tasks"</b> as the:</br>
 * {@link #id} the id.<br>
 * {@link #nickName} the title.<br>
 * @author Luis
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nickName", nullable = false)
    private String nickName;



    public User(Integer id) {
        this.id = id;
    }
}
