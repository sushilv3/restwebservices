package com.kodecamp.rest.webservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

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
	
	@RequestMapping(method = RequestMethod.GET,path="/hello-world-internationalized")
	public String helloWorld(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message",null, locale);
	}
}
