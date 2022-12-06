package com.example.tasklistcrudexample.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

public class TodoItemModelTest {
    @Test
    public void id_GetterAndSetter(){
        TodoItem input = new TodoItem();
        input.setId(1L);
        assertEquals(1L, input.getId());
    }
    @Test
    public void title_GetterAndSetter(){
        TodoItem input = new TodoItem();
        input.setTitle("Cali");
        assertEquals("Cali", input.getTitle());
    }
    @Test
    public void completed_GetterAndSetter(){
        TodoItem input = new TodoItem();
        input.setCompleted(true);
        assertEquals(true, input.isCompleted());
    }
    @Test
    public void toString_GetterAndSetter(){
        TodoItem input = new TodoItem();
        String expected = "TodoItem{id=null, title='null', completed=false}";
        assertEquals(expected, input.toString());
    }
}
