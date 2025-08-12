package com.taskhub.service;

import com.taskhub.dao.DAO;
import com.taskhub.dao.ProjectDAO;

import com.taskhub.dto.ProjectInfo;
import com.taskhub.entity.Project;
import com.taskhub.entity.User;

import java.util.List;


public class ProjectService extends AbstractService<Project> {


    public ProjectService(DAO<Project> projectDAO) {
        super(projectDAO);
    }


    @Override
    public List<Project> getByUserId(Long userId, DAO<Project> projectDAO) {

        if (!(projectDAO instanceof ProjectDAO)) {
            throw new IllegalArgumentException("Invalid DAO provided");
        }
        return ((ProjectDAO)projectDAO).getProjectByUserId(userId);

    }



    public Project create(ProjectInfo projectInfo, User owner) {
        Project project = Project.builder()
                .projectName(projectInfo.getProjectName())
                .owner(owner)
                .build();
        return dao.save(project);
    }


    public Project edit(Long projectId, ProjectInfo info) {
        Project project = dao.getById(projectId).orElseThrow(()->new RuntimeException("Not found"));
        project.setProjectName(info.getProjectName());
        return dao.update(project);


    }



}
