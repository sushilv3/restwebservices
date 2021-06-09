package com.kodecamp.rest.webservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

//	@GetMapping("/welcome")
	@RequestMapping(method = RequestMethod.GET,path="/welcome")
	public String greeting() {
		return "welcome";
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello world bean");
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("hello world bean, %s",name));
	}
}
