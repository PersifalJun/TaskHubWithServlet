package com.taskhub.utils;

import jakarta.servlet.http.HttpServletRequest;

import static java.util.Objects.isNull;

public class Util {

    public static Long extractUserId(HttpServletRequest request) {
        String path = request.getPathInfo(); // например: /5/projects/7/delete
        if (isNull(path)) {
            throw new IllegalArgumentException("Path is null");
        }
        String[] parts = path.split("/");
        for (String part : parts) {
            if (part.matches("\\d+")) { // первое число — userId
                return Long.parseLong(part);
            }
        }
        throw new IllegalArgumentException("User ID not found in path: " + path);
    }

    public static Long extractProjectId(HttpServletRequest request) {
        String path = request.getPathInfo(); // например: /5/projects/7/delete
        if (isNull(path)) {
            throw new IllegalArgumentException("Path is null");
        }
        String[] parts = path.split("/");
        for (int i = 0; i < parts.length - 1; i++) {
            if ("projects".equals(parts[i]) && parts[i + 1].matches("\\d+")) {
                return Long.parseLong(parts[i + 1]);
            }
        }
        throw new IllegalArgumentException("Project ID not found in path: " + path);
    }

    public static Long extractTaskListId(HttpServletRequest request) {
        String path = request.getPathInfo();
        if (isNull(path)) {
            throw new IllegalArgumentException("Path is null");
        }
        String[] parts = path.split("/");
        for (int i = 0; i < parts.length - 1; i++) {
            if ("tasklist".equals(parts[i]) && parts[i + 1].matches("\\d+")) {
                return Long.parseLong(parts[i + 1]);
            }
        }
        throw new IllegalArgumentException("TaskList ID not found in path: " + path);
    }

}
