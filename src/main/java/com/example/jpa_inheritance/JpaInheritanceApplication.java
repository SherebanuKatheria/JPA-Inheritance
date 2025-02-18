package com.example.jpa_inheritance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class JpaInheritanceApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	@Autowired
	public JpaInheritanceApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaInheritanceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		test();
		test2();
	}

	@Transactional
	private void test() {
		User user = User.builder()
				.userId("id1")
				.name("name")
				.build();

		UserAuth userAuth = UserAuth.builder()
				.user(user)
				.authType(AuthType.LOCAL)
				.build();

		LocalAuthDetails emailAuthData = LocalAuthDetails.builder()
				.passwordHash("hashedPassword")
				.build();

		// Set userAuth for LocalAuthDetails before persisting
		emailAuthData.setUserAuth(userAuth);
		userAuth.setAuthDetails(emailAuthData);

		user.setUserAuth(userAuth);

		userRepository.save(user);

		System.out.println("Saved!!!");
	}

	@Transactional
	private void test2() {
		User user = User.builder()
				.userId("id2")
				.name("Google User")
				.build();

		UserAuth userAuth = UserAuth.builder()
				.user(user)
				.authType(AuthType.GOOGLE)
				.build();

		GoogleAuthDetails googleAuthDetails = new GoogleAuthDetails();
		googleAuthDetails.setUserAuth(userAuth); // Must set this because of @MapsId
		userAuth.setAuthDetails(googleAuthDetails);

		user.setUserAuth(userAuth);

		userRepository.save(user);

		System.out.println("Saved Google User!!!");
	}
}
