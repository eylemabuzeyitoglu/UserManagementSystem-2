package com.eylemabz.UserManagementSystem2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UserManagementSystemApp {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(UserManagementSystemApp.class, args);

		UserRepository repository = context.getBean(UserRepository.class);


		repository.save(new User(11111, "Eylem", "eylem@example.com"));

		repository.findAll().forEach(user -> System.out.println(user.getName()));
	}
}

