package com.taskhub.service;

import com.taskhub.dao.DAO;
import com.taskhub.dao.ProjectDAO;

import com.taskhub.entity.Project;

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


}
