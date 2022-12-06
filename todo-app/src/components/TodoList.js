import React from "react";
import { useAuth0 } from '@auth0/auth0-react'
//Import Components
import Todo from './Todo';

const TodoList = ({todos, setTodos, filteredTodos }) => {
    const { isAuthenticated } = useAuth0();

    return(
        isAuthenticated && (
        <div className="todo-container">
            <ul className="todo-list">
                {filteredTodos.map(todo => (
                    <Todo 
                        setTodos={setTodos} 
                        todos={todos} 
                        key={todo.id}
                        todo={todo} 
                    />
                ))}
            </ul>
        </div>
        )
    )
}

export default TodoList;