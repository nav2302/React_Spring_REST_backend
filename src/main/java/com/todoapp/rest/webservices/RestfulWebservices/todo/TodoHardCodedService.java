package com.todoapp.rest.webservices.RestfulWebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardCodedService {
	private static List<Todo> todos = new ArrayList<>();
	private static Long counter = 0l;
	
	static {
		todos.add(new Todo(++counter, "nav", "Learn to dance", new Date(), false));
		todos.add(new Todo(++counter, "nav", "Learn to Microservices ", new Date(), false));
		todos.add(new Todo(++counter, "nav", "Learn about Angular", new Date(), false));
	}
	
	public List<Todo> findAll(){
		return todos;
	}
	
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		if(todo == null) return null;
		if(todos.remove(todo))
			return todo;
		return null;
			
	}

	public Todo findById(long id) {
		for(Todo todo: todos) {
			if(todo.getId() == id)
				return todo;
		}
		return null;
	}
	
	public Todo save(Todo todo) {
		if (todo.getId() == -1) {
			todo.setId(++counter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
}
