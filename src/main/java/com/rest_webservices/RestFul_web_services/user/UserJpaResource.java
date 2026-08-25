package com.rest_webservices.RestFul_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.rest_webservices.RestFul_web_services.jpa.PostRepository;
import com.rest_webservices.RestFul_web_services.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	@Autowired
	private UserDaoService service;
	
	private UserRepository repository;
	
	private PostRepository postrepository;
	
	public UserJpaResource(UserDaoService service,UserRepository repository,PostRepository postrepository) {
		this.service = service;
		this.repository=repository;
		this.postrepository=postrepository;
	}
	//Get users
	@GetMapping("/jpa/users")
	public List<user> retriveAllUsers(){
		return repository.findAll();
	}
	
	//Get user
	@GetMapping("/jpa/users/{id}")
	public EntityModel<user> retriveAllUsers(@PathVariable Integer id){
		Optional<user> user=repository.findById(id);
		
		if(user.isEmpty()) {
			throw new  UserNotFoundException("id:"+id);
		}

		EntityModel<user> entitymodel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retriveAllUsers());
		entitymodel.add(link.withRel("all-users"));
		return entitymodel;
	}
	
	//post user
	@PostMapping("/jpa/users")
	public ResponseEntity<user> createUser(@Valid @RequestBody user user) {
		user saveuser = repository.save(user);

		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(saveuser.getId()).
				toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void Deleteuser(@PathVariable Integer id){
		repository.deleteById(id);
		
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievepostsForuser(@PathVariable Integer id){
		Optional<user> user=repository.findById(id);
		
		if(user.isEmpty()) {
			throw new  UserNotFoundException("id:"+id);
		}
		
		return user.get().getPost();
	}
	
	@GetMapping("/jpa/users/{id}/posts/{postId}")
	public Post retrievePostByPostId(
	        @PathVariable Integer id,
	        @PathVariable Integer postId) {

	    user user = repository.findById(id).get();

	    Post post = postrepository.findById(postId).get();

	    return post;
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createpostsForuser(@PathVariable Integer id,@Valid @RequestBody Post post){
		Optional<user> user=repository.findById(id);
		
		if(user.isEmpty()) {
			throw new  UserNotFoundException("id:"+id);
		}
		
		post.setUser(user.get());
		Post savedpost=postrepository.save(post);
		
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(savedpost.getId()).
				toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	
}
