import axios from "axios";
import React from "react";
import { useAuth0 } from '@auth0/auth0-react'

const Form = ({inputText, setInputText, todos, setTodos, setStatus}) => {
    const { isAuthenticated } = useAuth0();

    const inputTextHandler = (e) => {
        setInputText(e.target.value)
    }
    
    const submitTodoHandler = (e) => {
        e.preventDefault();
        createTask(inputText)

    }
    const createTask = (text) => {
        let body = {
            title: text,
            completed: false
        }
        axios.post(`http://localhost:8080/tasks`, body)
        .then(( {data:savedTaskWithId} ) => {
            console.log(JSON.stringify(savedTaskWithId));
            setTodos([
                ...todos,
                savedTaskWithId
            ])
        })
    }
    const statusHandler = (e) => {
        setStatus(e.target.value)
    }

    return (
        isAuthenticated && (
        <form>
            <input value={inputText} onChange={inputTextHandler} type="text" className="todo-input" />
            <button onClick={submitTodoHandler} className="todo-button" type="submit">
                <i className="fas fa-plus-square"></i>
            </button>
            <div className="select">
                <select onChange={statusHandler} name="todos" className="filter-todo">
                <option value="all">All</option>
                <option value="completed">Completed</option>
                <option value="uncompleted">Uncompleted</option>
                </select>
            </div>
        </form>
        )
    )
}

export default Form;