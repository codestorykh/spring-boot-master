package com.rean.springbootmaster.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Immutable;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Immutable
@Entity
@Table(name = "todos")
public class Todo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    private String status;

}
