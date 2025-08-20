package com.taskhub.service;


import com.taskhub.dao.DAO;


import com.taskhub.dao.TaskListDAO;
import com.taskhub.dto.ProjectInfo;
import com.taskhub.dto.TaskListInfo;
import com.taskhub.entity.Project;
import com.taskhub.entity.TaskList;

import java.util.List;


public class TaskListService extends AbstractService<TaskList> {

    public TaskListService(DAO<TaskList> taskListDAO) {
        super(taskListDAO);
    }

    @Override
    public List<TaskList> getByUserId(Long userId, DAO<TaskList> dao) {
        return List.of();
    }

    public TaskList getByProjectId(Long projectId, DAO<TaskList> taskListDAO) {
        if (!(taskListDAO instanceof TaskListDAO)) {
            throw new IllegalArgumentException("Invalid DAO provided");
        }
        return ((TaskListDAO) taskListDAO).getTaskListByProjectId(projectId);
    }

    public TaskList create(TaskListInfo taskListInfo, Project projectWithoutTaskList) {
        TaskList taskList = TaskList.builder()
                .title(taskListInfo.getTitle())
                .project(projectWithoutTaskList)
                .build();
        return dao.save(taskList);
    }

    public TaskList edit(Long taskListId, TaskListInfo taskListInfo) {
        TaskList taskList = dao.getById(taskListId).orElseThrow(()->new RuntimeException("Not found"));
        taskList.setTitle(taskListInfo.getTitle());
        return dao.update(taskList);


    }




}
