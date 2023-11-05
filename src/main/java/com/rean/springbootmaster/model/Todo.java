package com.rean.springbootmaster.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "todos", schema = "public")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String descEn;
}
