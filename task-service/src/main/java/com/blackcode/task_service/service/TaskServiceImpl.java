package com.blackcode.task_service.service;

import com.blackcode.task_service.dto.TaskReq;
import com.blackcode.task_service.dto.TaskRes;
import com.blackcode.task_service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    private static final String USER_API_PATH = "/api/user/getUserById/";

    private final TaskRepository taskRepository;

    private final WebClient userClient;

    public TaskServiceImpl(TaskRepository taskRepository,
                           @Qualifier("userClient") WebClient userClient) {
        this.taskRepository = taskRepository;
        this.userClient = userClient;
    }

    @Override
    public List<TaskRes> getAllTask() {
        return List.of();
    }

    @Override
    public TaskRes getTaskById(Long taskId) {
        return null;
    }

    @Override
    public TaskRes addTask(TaskReq taskReq) {
        return null;
    }

    @Override
    public TaskRes updateTask(Long taskId, TaskReq taskReq) {
        return null;
    }

    @Override
    public Map<String, Object> deleteTask(Long taskId) {
        return Map.of();
    }
}
