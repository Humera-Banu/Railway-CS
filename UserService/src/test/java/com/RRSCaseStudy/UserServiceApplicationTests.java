package com.RRSCaseStudy;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.RRSCaseStudy.Model.User;
import com.RRSCaseStudy.Repository.UserRepository;
import com.RRSCaseStudy.Service.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceApplicationTests {
	@Autowired
	UserService userService;
	@MockBean
	UserRepository userRepository;
	@Test
	public void getUser()
	{
		when(userRepository.findAll()).thenReturn(Stream
			.of(new User(12,"Tapan","humera",91092846464l), new User(67,"dasd","humera",910928564l)).collect(Collectors.toList()));
	assertEquals(2,userService.getUsers().size());
	}
	@Test
	public void addUserTest()
	{
		User user = new User(133,"Test Name","Test@com",8907654231l);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user,userService.AddUser(user));
	}
	@Test
	public void updateUserTest()
	{
		User user = new User(133,"UpdateName","Update@Com",8907654231l);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user,userService.UpdateUser(user));
		
	}
	@Test
	public void deleteUserTest()
	{
		User user = new User(133,"UpdateName","Update@Com",8907654231l);
		userService.deleteUser(user);
		verify(userRepository,times(1)).delete(user);
		
	}
}
