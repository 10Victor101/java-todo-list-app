package com.example.todolist.exceptions.groupExceptions;

public class GrouperAlreadyExistsException extends RuntimeException {
    public GrouperAlreadyExistsException(String message) {
        super(message);
    }
}