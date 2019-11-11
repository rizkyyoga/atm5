package com.yoga.atm.app;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;

import com.yoga.atm.app.enumerable.TransactionType;
import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.model.Transaction;
import com.yoga.atm.app.service.AccountService;
import com.yoga.atm.app.service.TransactionService;

@SpringBootTest
@AutoConfigureMockMvc
class AppApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

	private class MockSecurityContext implements SecurityContext {

		private static final long serialVersionUID = -1386535243513362694L;

		private Authentication authentication;

		public MockSecurityContext(Authentication authentication) {
			this.authentication = authentication;
		}

		@Override
		public Authentication getAuthentication() {
			return this.authentication;
		}

		@Override
		public void setAuthentication(Authentication authentication) {
			this.authentication = authentication;
		}
	}

	private MockHttpSession mockLogIn() {
		Account account = accountService.findByAccountNumber("100000");
		Authentication auth = new UsernamePasswordAuthenticationToken(account, null, new ArrayList<>());
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				new MockSecurityContext(auth));
		return session;
	}

	@BeforeEach
	public void setup() {
		transactionService.deleteAll();
		Account acc = new Account();
		acc.setAccountNumber("100000");
		acc.setPin("100000");
		acc.setName("java");
		acc.setBalance(200.0);
		accountService.save(acc);
		acc = new Account();
		acc.setAccountNumber("200000");
		acc.setPin("200000");
		acc.setName("java2");
		acc.setBalance(300.0);
		accountService.save(acc);
	}

	// TESTCASE #1
	@Test
	void expectedAccountToBeThere() throws Exception {
		Account proofAccount = accountService.findByAccountNumber("100000");
		assertEquals(proofAccount.getAccountNumber(), "100000");
		assertEquals(proofAccount.getPin(), "100000");
		assertEquals(proofAccount.getName(), "java");
		assertEquals(proofAccount.getBalance(), 200.0);
	}

	// TESTCASE #2
	@Test
	void expectedAccountToBeThereInList() throws Exception {
		List<Account> proofAccount = (List<Account>) accountService.findAll();
		boolean isInList = false;
		for (Account a : proofAccount) {
			if (a.getAccountNumber().equals("200000")) {
				isInList = true;
				break;
			}
		}
		assertTrue(isInList);
	}

	// TESTCASE #3
	@Test
	void reducedBalanceWhenWithdraw() throws Exception {
		Account account = accountService.findByAccountNumber("100000");
		double balance = account.getBalance();
		this.mockMvc.perform(post("/withdrawl").param("amount", "10").session(mockLogIn()))
				.andExpect(redirectedUrl("/withdrawSummary"));
		assertEquals(balance - 10, accountService.findByAccountNumber("100000").getBalance());
	}

	// TESTCASE #4
	@Test
	void showingErrorWhenDoesntEnoughFundWithdraw() throws Exception {
		this.mockMvc.perform(post("/withdrawl").param("amount", "210").session(mockLogIn()))
				.andExpect(redirectedUrl("/withdraw"))
				.andExpect(flash().attribute("message", containsString("Insufficient balance $")));
	}

	// TESTCASE #5
	@Test
	void transferSuccess() throws Exception {
		Account account = accountService.findByAccountNumber("100000");
		double balance = account.getBalance();
		double balanceDestination = accountService.findByAccountNumber("200000").getBalance();
		this.mockMvc.perform(post("/transfer").param("destination", "200000").param("reference", "123456")
				.param("amount", "20").session(mockLogIn())).andExpect(redirectedUrl("/transferSummary"));
		assertEquals(balance - 20, accountService.findByAccountNumber("100000").getBalance());
		assertEquals(balanceDestination + 20, accountService.findByAccountNumber("200000").getBalance());
	}

	// TESTCASE #6
	@Test
	void transferFailedDoesntEnoughFund() throws Exception {
		this.mockMvc
				.perform(post("/transfer").session(mockLogIn()).param("destination", "200000")
						.param("reference", "123456").param("amount", "210"))
				.andExpect(redirectedUrl("/transaction"))
				.andExpect(flash().attribute("message", containsString("Insufficient balance $")));
	}

	// TESTCASE #7
	@Test
	void transferFailedAccount2DoesntExist() throws Exception {
		this.mockMvc
				.perform(post("/transfer").session(mockLogIn()).param("destination", "247819")
						.param("reference", "123456").param("amount", "10"))
				.andExpect(redirectedUrl("/transaction"))
				.andExpect(flash().attribute("message", "Invalid account<br>"));
	}

	// TESTCASE #8
	@Test
	void succesfullTransactionShowed() throws Exception {
		Map<String, Object> model = this.mockMvc.perform(post("/withdrawl").param("amount", "10").session(mockLogIn()))
				.andExpect(redirectedUrl("/withdrawSummary")).andReturn().getFlashMap();
		String transactionDate = (String) model.get("date");
		String content = this.mockMvc.perform(get("/getDataTransaction?draw=0&length=5&start=0").session(mockLogIn()))
				.andReturn().getResponse().getContentAsString();
		boolean stat = false;
		if (content.contains(transactionDate))
			stat = true;
		assertTrue(stat);
	}

	// TESTCASE #9
	@Test
	void succesfullBunchTransactionShowed() throws Exception {
		Account from = accountService.findByAccountNumber("100000");
		Account to = accountService.findByAccountNumber("200000");
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		for (int i = 0; i < 5; i++) {
			Transaction t = new Transaction(TransactionType.TRANSFER, from, (double) (i + 1) * 10, new Date(), to,
					"00000" + i);
			listTransaction.add(transactionService.save(t));
		}

		String content = this.mockMvc.perform(get("/getDataTransaction?draw=0&length=5&start=0").session(mockLogIn()))
				.andReturn().getResponse().getContentAsString();
		boolean stat = true;
		for (Transaction t : listTransaction) {
			if (!content.contains(t.getReference()))
				stat = false;
		}
		assertTrue(stat);
	}

	// TESTCASE #10
	@Test
	void moreTransactionDoesntShowed() throws Exception {
		Account from = accountService.findByAccountNumber("100000");
		Account to = accountService.findByAccountNumber("200000");
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		Transaction exclude = new Transaction();
		for (int i = 0; i < 6; i++) {
			Transaction t = new Transaction(TransactionType.TRANSFER, from, (double) (i + 1) * 10, new Date(), to,
					"00000" + (i + 1));
			t = transactionService.save(t);
			listTransaction.add(t);
			if (i == 0)
				exclude = t;
			TimeUnit.SECONDS.sleep(1);
		}
		String content = this.mockMvc.perform(get("/getDataTransaction?draw=0&length=5&start=0").session(mockLogIn()))
				.andReturn().getResponse().getContentAsString();
		boolean stat = true;
		boolean statExclude = true;
		listTransaction.remove(0); // removing item not in the page
		for (Transaction temp : listTransaction) {
			if (!content.contains(temp.getReference()))
				stat = false;
		}
		if (!content.contains(exclude.getReference())) {
			statExclude = false;
		}
		assertTrue(stat && !statExclude);
	}
}
