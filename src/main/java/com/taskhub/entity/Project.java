package com.taskhub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne(mappedBy = "project",cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, orphanRemoval = true)
    private TaskList taskList;





}