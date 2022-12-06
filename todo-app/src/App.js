import React, { useState, useEffect } from 'react';
import './App.css';
import axios from "axios";
//Importing Components
import LoginButton from './components/LoginButton';
import LogoutButton from './components/LogoutButton';
import Form from './components/form';
import TodoList from './components/TodoList';
import { useAuth0 } from '@auth0/auth0-react'
import Profile from './components/profile';

function App() {
  const { isAuthenticated } = useAuth0();

  //States
  const [inputText, setInputText] = useState("");
  const [todos, setTodos] = useState([]);
  const [status, setStatus] = useState("all")
  const [filteredTodos, setFilteredTodos] = useState([])

  useEffect(() => {
    getList()
  }, [])

  useEffect(() => {
    filterHandler()
  }, [todos, status])

  const getList = () => {
    axios.get(`http://localhost:8080/tasks`)
    .then(({data}) => {
      setTodos(data)
    })
}
  //Functions
  const filterHandler = () => {
    switch (status) {
      case "completed":
      setFilteredTodos(todos.filter(todo => todo.completed === true))
      break;
      case "uncompleted":
        setFilteredTodos(todos.filter(todo => todo.completed === false))
        break;
      default:
        setFilteredTodos(todos)
        break;
    }
  }

  return (
    
    <div className="App">
      <header>
      <h1>Todo List </h1>
      </header>
      
      <>
      <LoginButton />
      <LogoutButton />
      <Profile />
      </>

      <Form 
        inputText={inputText}
        todos={todos} 
        setTodos={setTodos} 
        setInputText={setInputText}
        setStatus={setStatus}
      />

      <TodoList 
        setTodos={setTodos} 
        todos={todos}
        filteredTodos={filteredTodos}
      />
      
    </div>
  );
}

export default App;
