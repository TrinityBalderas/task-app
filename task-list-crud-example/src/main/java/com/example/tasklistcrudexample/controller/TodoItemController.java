package com.example.tasklistcrudexample.controller;

import com.example.tasklistcrudexample.exception.TodoItemNotFoundException;
import com.example.tasklistcrudexample.model.TodoItem;
import com.example.tasklistcrudexample.responses.TodoItemPatchRequest;
import com.example.tasklistcrudexample.responses.TodoItemPatchResponse;
import com.example.tasklistcrudexample.responses.TodoItemResponse;
import com.example.tasklistcrudexample.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://domain2.com", maxAge = 3600)
@RestController
@RequestMapping("/tasks")
public class TodoItemController {
    private TodoItemService todoItemService;

    @Autowired // Constructor, setter, reflection
    public TodoItemController(TodoItemService todoItemService){
        this.todoItemService = todoItemService;
    }

    // GET tasks
    // GET tasks/{id}
    // POST tasks
    // PUT tasks/{id}
    // PATCH tasks/{id}
    // DELETE tasks/{id}

    @GetMapping()
    public List<TodoItem> getAllTodoItems() {
        return todoItemService.getAllItems();
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoItem> getItemById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(todoItemService.getById(id));
        }
        catch (TodoItemNotFoundException e){
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foo Not Found", e);
            return new ResponseEntity(new TodoItemResponse( null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public TodoItem createTodoItem(@RequestBody TodoItem body) {
        return todoItemService.createTodoItem(body);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoItem> overwriteRecord(@PathVariable Long id, @RequestBody TodoItem body) {
        try {
            return ResponseEntity.ok(todoItemService.replaceTodoItem(id, body));
        }
        catch (TodoItemNotFoundException e){
            return new ResponseEntity(new TodoItemResponse( body, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoItem> useNonNullFieldsToUpdateRecord(@PathVariable Long id, @RequestBody TodoItemPatchRequest body) {
        try {
            return ResponseEntity.ok(todoItemService.updateTodoItem(id, body));
        }
        catch (TodoItemNotFoundException e){
            return new ResponseEntity(new TodoItemPatchResponse( body, e.getMessage()), HttpStatus.NOT_FOUND);
            // return new ResponseEntity(body, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TodoItemResponse> deleteTheItem(@PathVariable Long id) {
        todoItemService.deleteTodoItem(id);
        return ResponseEntity.ok(new TodoItemResponse(null, "Task " + id + " was deleted."));
    }
}
