package com.taskhub.entity;

import jakarta.persistence.*;
import lombok.*;



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

    @OneToOne(mappedBy = "project",orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "task_list_id",unique = true)
    private TaskList taskList;

    @Override
    public String toString() {
        return "Project id: " + this.id;
    }

}