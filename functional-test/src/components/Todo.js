import React, { useState } from "react";

const Todo = () => {
  const [task, setTask] = useState("");
  const [tasks, setTasks] = useState([]);
  const handleInputChange = (event) => setTask(event.target.value);

  const handleFormSubmit = (event) => {
    event.preventDefault();
    if (task.trim()) {
      setTasks([...tasks, task]);
      setTask("");
    }
  };

  return (
    <>
      <form onSubmit={handleFormSubmit}>
        <input
          data-testid="form-field"
          onChange={handleInputChange}
          placeholder="Type some task"
          type="text"
          value={task}
        />
        <button data-testid="form-btn" type="submit">
          Add new Task
        </button>
      </form>
      <table>
        <thead>
          <tr>
            <th>Your Tasks</th>
          </tr>
        </thead>
        <tbody>
          {tasks.map((t, index) => (
            <tr key={index}>
              <td>{t}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

export default Todo;
