package com.example.mockitoUserCrud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MockitoUserCrudApplicationTests {
//
//
//	@Autowired
//	private DefaultUserService userService;
//	@MockBean
//	private UserRepository userRepository;
//
//	@Test
//	public void getUsersTest() {
//		when(userRepository.findAll()).thenReturn(
//				Stream.of(
//						new Users(2, "ghjk", "adfgj"),
//						new Users(3, "jis","ipoi")
//				).collect(Collectors.toList())
//		);
//		assertEquals(2, userService.getAllUsers().size());
//	}
//
//	@Test
//	public void getUserByIdTest() {
//		when(userRepository.findById(1L)).thenReturn(Optional.of(new Users(1, "dsd", "dsds")));
//
//		Optional<Long> userIdOptional = userService.getUsersById(1L).map(Users::getId);
//		long actualUserId = userIdOptional.orElse(0L); // Default value in case Optional is empty
//
//		assertEquals(1, actualUserId);
//	}
//
//	@Test
//	public void saveUserTest() {
//		Users users = new Users(3, "ds", "asd");
//		when(userRepository.save(users)).thenReturn(users);
//		assertEquals(users, userService.saveUser(users));
//	}
//
//	@Test
//	public void deleteUser() {
//		Users users = new Users(3, "ds", "asd");
//		userService.deleteUserById(3L);
//		verify(userRepository, times(1)).deleteById(3L);
//	}


	@Test
	void contextLoads() {
	}

}
