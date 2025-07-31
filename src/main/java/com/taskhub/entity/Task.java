package com.taskhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "completed",nullable = false)
    private boolean completed;

    @Column(name = "date_of_creation")
    private LocalDateTime creationDate;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "task_list_id" )
    private TaskList taskList;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Comment> comments;

}