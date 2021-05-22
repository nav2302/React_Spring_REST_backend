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
public class TodoJpaResource {
	
	@Autowired
	private TodoHardCodedService todoService;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	@GetMapping("/jpa/users/{userName}/todos")
	public List<Todo> getAllTodos(@PathVariable String userName){
		return todoJpaRepository.findByUserName(userName);
	}
	
	@DeleteMapping("/jpa/users/{userName}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String userName, @PathVariable long id){
		todoJpaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/jpa/users/{userName}/todos/{id}")
	public Todo getTodo(@PathVariable String userName, @PathVariable long id){
		return todoJpaRepository.findById(id).get();
	}
	
	@PutMapping("/jpa/users/{userName}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String userName, @PathVariable long id, @RequestBody Todo todo){
		todo.setUserName(userName);
		Todo updatedTodo = todoJpaRepository.save(todo);
		
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users/{userName}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String userName, @RequestBody Todo todo){
		todo.setUserName(userName);
		Todo createdTodo = todoJpaRepository.save(todo);
		
		//Always return Location or url of the created resource for post mapping
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		System.out.println(uri);
		
		return ResponseEntity.created(uri).build();
	}
}
