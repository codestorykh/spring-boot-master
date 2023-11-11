package com.rean.springbootmaster;

import com.rean.springbootmaster.repository.TodoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootMasterApplication {

//
//	@Autowired
//	private TodoRepository todoRepository;
//
	private final TodoRepository todoRepository;

	private final JdbcTemplate jdbcTemplate;
	private final RestTemplate restTemplate;

	public static void main(String[] args) {

//		SpringApplication.run(SpringBootMasterApplication.class, args);
		var context = SpringApplication.run(SpringBootMasterApplication.class, args);

//		System.out.println("Count Beans" + context.getBeanDefinitionCount());
//
//		String[] allBeanNames = context.getBeanDefinitionNames();
//		for(String beanName : allBeanNames) {
//			System.out.println(beanName);
//		}
	}

	void newInstance() {
		RestTemplate rest = new RestTemplate();
	}

	// @PostConstruct will start after project is run
	/*@PostConstruct
	void test() {
		System.out.println("Hello World");
		System.out.println(todoRepository.findAll());
	}*/


	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			System.out.println("Hello World");
			System.out.println(todoRepository.findAll());
		};
	}
}
