package com.example.tasklistcrudexample.controller;

import com.example.tasklistcrudexample.exception.TodoItemNotFoundException;
import com.example.tasklistcrudexample.model.TodoItem;
import com.example.tasklistcrudexample.responses.TodoItemPatchRequest;
import com.example.tasklistcrudexample.responses.TodoItemResponse;
import com.example.tasklistcrudexample.service.TodoItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TodoItemControllerTest {
    TodoItemService mockTodoItemService;
    TodoItemController todoItemController;

    @BeforeEach
    public void set(){
        mockTodoItemService = mock(TodoItemService.class);
        todoItemController = new TodoItemController(mockTodoItemService);
    }
    @Test
    public void getAll_TodoItems(){
        todoItemController.getAllTodoItems();
        verify(mockTodoItemService).getAllItems();
    }
    @Test
    public void getAll_ReturnValuesFromService(){
        when(mockTodoItemService.getAllItems()).thenReturn(null);
        assertEquals(null, todoItemController.getAllTodoItems());
    }
    @Test
    public void getItemById_shouldReturn404WhenRecordDoesNotExist(){
        Mockito.when(mockTodoItemService.getById(1L)).thenThrow(new TodoItemNotFoundException("Task 1 not found."));
        ResponseEntity<TodoItem> actual = todoItemController.getItemById(1L);
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }
    @Test
    public void createTodoItem(){
        TodoItem input = new TodoItem();
        todoItemController.createTodoItem(input);
        verify(mockTodoItemService).createTodoItem(input);
    }
    @Test
    public void putUpdateTodo_ShouldReturn404WhenRecordDoesNotExist() {
        TodoItem input = new TodoItem();
        Mockito.when(mockTodoItemService.replaceTodoItem(1L, input)).thenThrow(new TodoItemNotFoundException("Task 1 not found."));
        ResponseEntity<TodoItem> actual = todoItemController.overwriteRecord(1L, input);
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }
    @Test
    public void patchUpdateTodo_ShouldReturn404WhenRecordDoesNotExist() {
        TodoItemPatchRequest input = new TodoItemPatchRequest();
        Mockito.when(mockTodoItemService.updateTodoItem(1L, input)).thenThrow(new TodoItemNotFoundException("Task 1 not found."));
        ResponseEntity<TodoItem> actual = todoItemController.useNonNullFieldsToUpdateRecord(1L, input);
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }
    @Test
    public void deleteTodoItem_CallsService() {
        todoItemController.deleteTheItem(1L);
        verify(mockTodoItemService).deleteTodoItem(1L);
    }
    @Test
    public void delete_TaskCompletedMessage() {
        TodoItem input = new TodoItem();
        ResponseEntity<TodoItemResponse> actual = todoItemController.deleteTheItem(1L);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("Task 1 was deleted.", actual.getBody().message);
    }
}
