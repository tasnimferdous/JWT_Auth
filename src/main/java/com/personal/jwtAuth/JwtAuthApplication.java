package com.personal.jwtAuth;

import com.personal.jwtAuth.entity.MyRole;
import com.personal.jwtAuth.entity.User;
import com.personal.jwtAuth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class JwtAuthApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User admin = userRepository.findByRole(MyRole.ADMIN);
		if(admin == null){
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setRole(MyRole.ADMIN);
			user.setFirstName("The");
			user.setLastName("Admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
