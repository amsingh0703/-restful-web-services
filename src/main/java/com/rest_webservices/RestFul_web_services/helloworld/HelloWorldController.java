package com.rest_webservices.RestFul_web_services.helloworld;

import java.util.Locale;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Rest API
@RestController
public class HelloWorldController {
	@Autowired
	private MessageSource messagesource;
	public HelloWorldController(MessageSource messagesource) {
		this.messagesource = messagesource;
	}
	
	
//	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path ="/hello-world")
	public String helloWorld() {
		return "Hello World!!";
	}
	
	@GetMapping(path ="/hello-world-bean")
	public HelloWorldBean helloWorldbean() {
		return new HelloWorldBean("Hello BSDK");
	}
	
	@GetMapping(path ="/hello-world/PathVariable/{name}")
	public HelloWorldBean helloWorlPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World , %s", name));
	}
	
	@GetMapping(path ="/hello-world-Internationalized")
	public String helloWorldInternationalized() {
		
		@Nullable
		Locale Locale=LocaleContextHolder.getLocale();
		return messagesource.getMessage("good.morning.message", null,"Default Message", Locale);
		
		//return "Hello World V2"; 
		
		//1:
		//2:
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)
	}	
}
