package com.example.blog;

import com.example.blog.AUTH.AppRole;
import com.example.blog.AUTH.AppUser;
import com.example.blog.AUTH.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run (UserService userService){
		// 1-Create role
		// 2-Create User
		// 3-Assign User To Role
		return args -> {
			userService.saveRole(new AppRole(1L,"ADMIN"));
			userService.saveUser(new AppUser(1L,"966560073923","1234","admin",new ArrayList<>()));
			userService.addRoleToUser(2L, 1L);
		};
	}

}
