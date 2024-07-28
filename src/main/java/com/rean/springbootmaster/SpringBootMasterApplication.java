package com.rean.springbootmaster;

import com.rean.springbootmaster.user.model.Role;
import com.rean.springbootmaster.user.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootMasterApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMasterApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
		return args -> {
			Role role =new Role();
			role.setId(0L);
			role.setName("ADMIN");
			roleRepository.save(role);
		};
	}
}
