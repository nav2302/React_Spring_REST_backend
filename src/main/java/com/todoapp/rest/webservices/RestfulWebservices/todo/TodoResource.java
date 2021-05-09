package com.todoapp.rest.webservices.RestfulWebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping("/users/{userName}/todos/{id}")
	public Todo getTodo(@PathVariable String userName, @PathVariable long id){
		return todoService.findById(id);
	}
	
	@PutMapping("/users/{userName}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String userName, @PathVariable long id, @RequestBody Todo todo){
		Todo updatedTodo = todoService.save(todo);
		
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/users/{userName}/todos")
	public ResponseEntity<Void> newTodo(@PathVariable String userName, @RequestBody Todo todo){
		Todo createdTodo = todoService.save(todo);
		
		//Always return Location or url of the created resource for post mapping
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		System.out.println(uri);
		
		return ResponseEntity.created(uri).build();
	}
}
