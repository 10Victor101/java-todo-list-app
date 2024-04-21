package com.example.todolist.exceptions.groupExceptions;

public class GrouperNotFoundException extends RuntimeException {
    public GrouperNotFoundException(String message) {
        super(message);
    }
}