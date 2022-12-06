package com.example.tasklistcrudexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TodoItemNotFoundException extends RuntimeException{

        public TodoItemNotFoundException(String message){
            super(message);
        }
}
