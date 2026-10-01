package com.rest_webservices.RestFul_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	// JPA/Hibernate > Database 
	// UserDaoService > Static List  
	
	private static List<User> users = new ArrayList<>();
	private static int userCount = 0;
	
	static {
		users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++userCount, "RAM", LocalDate.now().minusYears(20)));
		users.add(new User(++userCount, "AMAN", LocalDate.now().minusYears(10)));
	}
	
	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		Predicate<? super User> predicate = u -> u.getId().equals(id);

		return users.stream()
		            .filter(predicate)
		            .findFirst()
		            .orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = u -> u.getId().equals(id);
		users.removeIf(predicate);
	}

	public void DeleteById(int id) {
		deleteById(id);
	}
}
