package com.rest_webservices.RestFul_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

	private final UserRepository repository;
	private final PostRepository postRepository;

	public UserJpaResource(UserRepository repository, PostRepository postRepository) {
		this.repository = repository;
		this.postRepository = postRepository;
	}

	// GET all users from database
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return repository.findAll();
	}

	// GET single user from database
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUserById(@PathVariable Integer id) {
		Optional<User> user = repository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with id: " + id);
		}

		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}

	// POST new user
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	// DELETE user
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		if (!repository.existsById(id)) {
			throw new UserNotFoundException("User not found with id: " + id);
		}
		repository.deleteById(id);
	}

	// GET posts for a user
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable Integer id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

		return user.getPosts();
	}

	// GET specific post for a user
	@GetMapping("/jpa/users/{id}/posts/{postId}")
	public Post retrievePostByPostId(@PathVariable Integer id, @PathVariable Integer postId) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

		if (post.getUser() == null || !post.getUser().getId().equals(user.getId())) {
			throw new PostNotFoundException("Post id " + postId + " does not belong to user id " + id);
		}

		return post;
	}

	// POST create post for a user
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

		post.setUser(user);
		Post savedPost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
