package com.rest_webservices.RestFul_web_services.user;

import java.net.URI;
import java.util.List;
import static org. springframework. hateoas.server.mvc.WebMvcLinkBuilder .*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
	public EntityModel<user> retriveAllUsers(@PathVariable Integer id){
		user user=service.findOne(id);
		
		if(user == null) {
			throw new  UserNotFoundException("id:"+id);
		}

		EntityModel<user> entitymodel = EntityModel.of(user);
		
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retriveAllUsers());
		entitymodel.add(link.withRel("all-users"));
		return entitymodel;
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
