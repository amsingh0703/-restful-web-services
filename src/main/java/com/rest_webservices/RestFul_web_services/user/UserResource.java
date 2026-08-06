package com.rest_webservices.RestFul_web_services.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	//Get users
	@GetMapping("/users")
	public List<user> retriveAllUsers(){
		return service.findAll();
	}
	
	//Get user
	@GetMapping("/users/{id}")
	public user retriveAllUsers(@PathVariable Integer id){
		user user=service.findOne(id);
		
		if(user == null) {
			throw new  UserNotFoundException("id:"+id);
		}
		
		return user;
	}
	
	//post user
	@PostMapping("/users")
	public ResponseEntity<user> createUser(@Valid @RequestBody user user) {
		user saveuser = service.save(user);

		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(saveuser.getId()).
				toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void Deleteuser(@PathVariable Integer id){
		service.DeleteById(id);
		
		
	}
	
}
