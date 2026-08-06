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
	
	private static List<user> users = new ArrayList<>();
	private static int usercount=0;
	
	static {
		users.add(new user(++usercount,"Adam",LocalDate.now().minusYears(30)));
		users.add(new user(++usercount,"RAM",LocalDate.now().minusYears(20)));
		users.add(new user(++usercount,"AMAN",LocalDate.now().minusYears(10)));
	}
	
	public List<user> findAll(){
		return users;
	}
	public user save(user user) {
		user.setId(++usercount);
		users.add(user);
		return user;
	}
	
	public user findOne(int id) {
//				for (user user : users) {
//				    if (user.getId().equals(id)) {
//				        return user;
//				    }
//				}
//
//				throw new RuntimeException("User not found");
//			}
		Predicate<? super user> predicate = user -> user.getId().equals(id);

		return users.stream()
		            .filter(predicate)
		            .findFirst()
		            .orElse(null)	;
	}
	
	public void DeleteById(int id) {
		Predicate<? super user> predicate = user -> user.getId().equals(id);
		
		users.removeIf(predicate);
	}
}
