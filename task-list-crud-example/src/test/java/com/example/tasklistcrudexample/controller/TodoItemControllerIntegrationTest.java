package com.example.tasklistcrudexample.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoItemControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodoItemController mockTodoItemController;

    @Test
    public void getTask_ReturnTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tasks"))
                .andReturn()
                .getResponse();
        verify(mockTodoItemController).getAllTodoItems();
    }
}
