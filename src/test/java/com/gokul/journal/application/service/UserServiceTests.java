package com.gokul.journal.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gokul.journal.application.entity.User;
import com.gokul.journal.application.repos.UserRepository;

@SpringBootTest
public class UserServiceTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testAdd() {
		Assertions.assertEquals(4, 2+2);
	}

	@Test
	public void testFindByUserName() {
		User user = userRepository.findByUserName("Scaler");
		assertTrue(!user.getJournalEntries().isEmpty());
	}

	@ParameterizedTest
	@CsvSource({
		"4, 4, 8",
		"21, 10, 31",
		"13, 3, 16"
	})
	public void testAdditionWithMultipleInputs(int a, int b, int expected) {
		assertEquals(expected, a+b);
	}

	@ParameterizedTest
	@CsvSource({
		"Gokul",
		"Journal",
		"Scaler"
	})

	public void testFindByUserName(String name) {
		assertNotNull(userRepository.findByUserName(name), "failed for: " + name);
	}

}
