package com.example.tasklistcrudexample.service;

import com.example.tasklistcrudexample.exception.TodoItemNotFoundException;
import com.example.tasklistcrudexample.model.TodoItem;
import com.example.tasklistcrudexample.repository.TodoItemRepository;
import com.example.tasklistcrudexample.responses.TodoItemPatchRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
@Service
public class TodoItemService {
    private final TodoItemRepository todoItemRepository;

    public TodoItemService(TodoItemRepository todoItemRepository){
        this.todoItemRepository = todoItemRepository;
    }
    public List<TodoItem> getAllItems(){
        return todoItemRepository.findAll();
    }
    public TodoItem getById(Long id) {
        Optional<TodoItem> searchResult = todoItemRepository.findById(id);
        if (searchResult.isEmpty()){
            throw new TodoItemNotFoundException("Task " + id + " not found.");
        }
        return searchResult.get();
    }
    public TodoItem createTodoItem(TodoItem body) {
        body.setId(null);
        TodoItem saved = todoItemRepository.save(body);
        return saved;
    }
    public TodoItem replaceTodoItem(Long id, TodoItem body) {
        if (todoItemRepository.existsById(id)){
            body.setId(id);
            return todoItemRepository.save(body);
        }
        throw new TodoItemNotFoundException("Task " + id  + " not found.");
    }
    public TodoItem updateTodoItem(Long id, TodoItemPatchRequest body) {
        if (todoItemRepository.existsById(id)){
            body.setId(id);
            TodoItem original = todoItemRepository.findById(id).get();
            if (body.getTitle() != null){
                original.setTitle(body.getTitle());
            }
            if (body.getCompleted() != null) {
                original.setCompleted(body.getCompleted());
            }
            return todoItemRepository.save(original);
        }
        throw new TodoItemNotFoundException("Task " + id  + " not found.");
    }

    public void deleteTodoItem(Long id) {
        if (todoItemRepository.existsById(id)){
            todoItemRepository.deleteById(id);
        }
    }
}
