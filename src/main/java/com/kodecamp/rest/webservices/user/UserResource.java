package com.kodecamp.rest.webservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


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
	public User findUserById(@PathVariable int id) {
		User user = userDaoService.findById(id);
		if(user == null) {
			throw new UserNotFoundException("User Doesn't exist with this id  - "+id);
		}
		return user;
	}

	// created
	// details of hte user output - created & return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createdUser(@RequestBody User user) {
		
		User savedUser = userDaoService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}

}
