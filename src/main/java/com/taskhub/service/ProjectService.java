package com.taskhub.service;

import com.taskhub.dao.DAO;
import com.taskhub.dao.ProjectDAO;

import com.taskhub.entity.Project;



public class ProjectService extends AbstractService<Project> {

    public ProjectService(DAO<Project> projectDAO) {
        super(projectDAO);
    }


}
