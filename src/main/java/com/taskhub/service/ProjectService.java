package com.taskhub.service;

import com.taskhub.dao.ProjectDAO;

import com.taskhub.entity.Project;


import java.util.List;

public class ProjectService {
    private final ProjectDAO projectDAO;

    public ProjectService(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public List<Project> getFixedCountProjects(int offset, int limit) {
        return projectDAO.getItems(offset,limit);
    }

    public Project getProjectById(Long id) {
        return projectDAO.getById(id).orElseThrow();

    }
    public List<Project> getAllProjects() {
        return projectDAO.findAll();
    }

    public Project saveProject(Project project) {
        return projectDAO.save(project);
    }

    public void deleteProjectById(Long id) {
        projectDAO.deleteById(id);
    }

    public Project updateProject(Project project) {
        return projectDAO.update(project);
    }

    public int getAllProjectsCount() {
        return projectDAO.getAllCount();
    }

}
