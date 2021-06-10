package com.kodecamp.rest.webservices.user;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

//	Get /user
//	fetchAllUsers
	@GetMapping("/users")
	public List<User> getAllUser() {
		return userDaoService.findAll();
	}

//	fetchUserById
//	Get /users/{id}
	@GetMapping("/users/{id}")
	public EntityModel<User> findUserById(@PathVariable int id) {
		User user = userDaoService.findById(id);
		if (user == null) {
			throw new UserNotFoundException("User Doesn't exist with this id  - " + id);
		}
//		"all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUser());

		resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return resource;
	}

//	delete user by id
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		User user = userDaoService.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("User Doesn't exist with this id  - " + id);

		}

	}

	// created
	// details of hte user output - created & return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createdUser(@Valid @RequestBody User user) {

		User savedUser = userDaoService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

}
