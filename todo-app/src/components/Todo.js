import React from "react";
import axios from "axios";
import { useAuth0 } from '@auth0/auth0-react'

const Todo = ({todo,  todos, setTodos}) => {
    const { isAuthenticated } = useAuth0();
    //Events
    const deleteHandler = (id) => {
        axios.delete(`http://localhost:8080/tasks/${id}`)
        .then(() => {
            const index = todos.findIndex ( (t) => t.id === id); 
            const updatedList = [ ...todos.splice (0, index), ...todos.splice (index + 1), ]; 
            setTodos (updatedList); 
        });
    };

    const completeHandler = (id, completed) => {

        let body = { completed };
        axios.patch(`http://localhost:8080/tasks/${id}`, body)
        .then(({data:updatedTask}) => {
                const index = todos.findIndex ( (t) => t.id === updatedTask.id); 
                const updatedList = [...todos]
                updatedList[index] = updatedTask
                setTodos(updatedList)
            });
    }

    return ( 
        isAuthenticated && (
        <div className="todo">
            <li className={`todo-item ${todo.completed ? "completed" : ""}`}>{todo.title}</li>
            <button onClick={() => completeHandler(todo.id, !todo.completed)} className="complete-btn">
                <i className="fas fa-check"></i>
            </button>
            <button onClick={() => deleteHandler(todo.id)} className="trash-btn">
                <i className="fas fa-trash"></i>
            </button>
        </div>
        )
    )
}

export default Todo