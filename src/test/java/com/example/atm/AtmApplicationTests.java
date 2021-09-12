package com.example.atm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AtmApplicationTests {

	@Autowired
	private BankController bankController;

	@Test
	void contextLoads() {
	}

	@Test
	void takesCorrectPIN() throws Exception {
		assertThat(bankController.withdrawFunds(123456789,1234,300)).contains("Incorrect PIN");
	}

}
