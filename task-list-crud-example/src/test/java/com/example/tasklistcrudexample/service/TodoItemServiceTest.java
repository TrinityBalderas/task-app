package com.example.tasklistcrudexample.service;

import com.example.tasklistcrudexample.exception.TodoItemNotFoundException;
import com.example.tasklistcrudexample.model.TodoItem;
import com.example.tasklistcrudexample.repository.TodoItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TodoItemServiceTest {
    private TodoItemRepository mockTodoItemRepository;
    private TodoItem insideDatabase;
    private TodoItemService todoItemService;

    @BeforeEach
    public void setup() {
        insideDatabase = new TodoItem();
        insideDatabase.setId(1L);
        mockTodoItemRepository = mock(TodoItemRepository.class);
        todoItemService = new TodoItemService(mockTodoItemRepository);
        when(mockTodoItemRepository.findById(1L)).thenReturn(Optional.of(insideDatabase));
        when(mockTodoItemRepository.save(any())).thenAnswer(method -> method.getArgument(0));
    }
    @Test
    public void getAll_CallsRepo() {
        todoItemService.getAllItems();
        verify(mockTodoItemRepository).findAll();
    }
    @Test
    public void getById_IfSearchResultIsEmptyThenReturn404() {
        TodoItemNotFoundException exception = assertThrows(TodoItemNotFoundException.class, () -> {
           todoItemService.getById(2L);
        });
        assertEquals("Task 2 not found.", exception.getMessage());
    }
    @Test
    public void getById_CallRepo() {
        Mockito.when(mockTodoItemRepository.findById(1L)).thenReturn(Optional.of(insideDatabase));
        TodoItem actual = todoItemService.getById(1L);
        assertEquals(1L, actual.getId());
    }
}
