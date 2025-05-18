package com.jamersc.springboot.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void itShouldAddTwoNumbers() {

		Calculator underTest = new Calculator();
		int firstInteger = 30;
		int secondInteger = 20;
		int result = underTest.add(firstInteger, secondInteger);
		int expected = 50;

		assertThat(result).isEqualTo(expected); // passed

	}

	static class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}

}
