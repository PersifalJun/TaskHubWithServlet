package com.taskhub.service;


import com.taskhub.dao.TaskListDAO;
import com.taskhub.entity.TaskList;

import java.util.List;

public class TaskListService {

    private final TaskListDAO taskListDAO;

    public TaskListService(TaskListDAO taskListDAO) {
        this.taskListDAO = taskListDAO;
    }

    public List<TaskList> getFixedCountTaskLists(int offset, int limit) {
        return taskListDAO.getItems(offset,limit);
    }

    public TaskList getTaskListById(Long id) {

        return taskListDAO.getById(id).orElseThrow();

    }
    public List<TaskList> getAllTaskLists() {
        return taskListDAO.findAll();
    }

    public TaskList saveTaskList(TaskList taskList) {
        return taskListDAO.save(taskList);
    }

    public void deleteTaskListById(Long id) {
        taskListDAO.deleteById(id);
    }

    public TaskList updateTaskList(TaskList task) {
        return taskListDAO.update(task);
    }

    public int getAllTaskListsCount() {
        return taskListDAO.getAllCount();
    }
}
