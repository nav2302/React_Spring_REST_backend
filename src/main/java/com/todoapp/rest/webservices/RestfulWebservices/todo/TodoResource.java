package com.todoapp.rest.webservices.RestfulWebservices.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TodoResource {
	
	@Autowired
	private TodoHardCodedService todoService;
	
	@GetMapping("/users/{userName}/todos")
	public List<Todo> getAllTodos(@PathVariable String userName){
		return todoService.findAll();
	}
	
	@DeleteMapping("/users/{userName}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String userName, @PathVariable long id){
		Todo deletedTodo = todoService.deleteById(id);
		if(deletedTodo != null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}