package com.example.atm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AtmApplicationTests {

	@Autowired
	private BankController bankController;
	private AtmRepository atmRepository;
	private AccountRepository accountRepository;
	@Autowired
	private BankService bankService;

	@Test
	void contextLoads() {
	}

	@Test
	void startsWithCorrectAmountOfNotes()  {
		bankService.initializeDatabases();
		assertThat(bankService.getAtms().get(0).getFifties()).isEqualTo(10);
		assertThat(bankService.getAtms().get(0).getTwenties()).isEqualTo(30);
		assertThat(bankService.getAtms().get(0).getTens()).isEqualTo(30);
		assertThat(bankService.getAtms().get(0).getFives()).isEqualTo(20);
	}

	@Test
	void withdrawFundsRejectsIncorrectPIN()  {
		bankService.initializeDatabases();
		assertThat(bankController.withdrawFunds(123456789,42,300)).contains("Incorrect PIN");
	}

	@Test
	void withdrawFundsRejectsAmountGreaterThanAmountInAtm() {
		bankService.initializeDatabases();
		assertThat(bankController.withdrawFunds(123456789,1234,9999)).contains("Request is greater than maximum amount available.");
	}


	@Test
	void getBalanceRejectsIncorrectPIN() {
		bankService.initializeDatabases();
		assertThat(bankController.getBalance(123456789,42)).contains("Incorrect PIN");
	}

	@Test
	void withdrawFundsGivesCorrectAmount() {
		bankService.initializeDatabases();
		assertThat(bankController.withdrawFunds(123456789,1234,495)).contains("Dispensed: 495");
	}

	@Test
	void withdrawFundsGivesCorrectNumberOfNotes() {
		bankService.initializeDatabases();
		assertThat(bankController.withdrawFunds(123456789,1234,205)).contains("Total notes dispensed: 5");
	}

	@Test
	void accountBalanceRemainsTheSameAfterWithdrawFundsReceivesBadRequest() {
		bankService.initializeDatabases();
		int initialBalance = bankService.getAccounts().get(0).getOpeningBalance();
		bankController.withdrawFunds(123456789,1234,1005);
		assertThat(bankService.getAccounts().get(0).getOpeningBalance()).isEqualTo(initialBalance);
	}

}
