package com.example.tasklistcrudexample.repository;

import com.example.tasklistcrudexample.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

// no bean annotation
//INSERT INTO TODO_ITEM (ID, COMPLETED, TITLE) VALUES (1, false, 'bob')
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}
