package com.yoga.atm.app.controller;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoga.atm.app.dao.AccountRepository;
import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.service.TransactionService;

@Controller
@PropertySource("classpath:message.properties")
@RequestMapping("")
public class TransferController {

	@Autowired
	private Environment env;

	@Autowired
	private AccountRepository accountService;

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/transferDestination", method = RequestMethod.GET)
	public ModelAndView transferDestination(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		ModelAndView view = new ModelAndView();
		try {
			view.setViewName("transfer/destination");
		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", env.getProperty("app.unknown.error"));
		}
		return view;
	}

	@RequestMapping(value = "/transferAmount", method = RequestMethod.GET)
	public ModelAndView transferAmount(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@RequestParam(value = "destination", required = true) String destination) {
		ModelAndView view = new ModelAndView();
		try {
			String message = "";
			boolean stoper = false;
			if (!destination.matches("[0-9]+")) {
				message += env.getProperty("app.invalid.account");
				stoper = true;
			}

			List<Account> listAccount = accountService.findByAccountNumber(destination);
			if (listAccount.size() <= 0) {
				message += env.getProperty("app.invalid.account");
				stoper = true;
			}

			if (stoper) {
				view = new ModelAndView("redirect:/transferDestination");
				redirectAttributes.addFlashAttribute("message", message);
			} else {
				view.addObject("destination", destination);
				view.setViewName("transfer/amount");
			}
		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", env.getProperty("app.unknown.error"));
		}
		return view;
	}

	@RequestMapping(value = "/transferConfirm", method = RequestMethod.GET)
	public ModelAndView transferConfirm(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@RequestParam(value = "destination", required = true) String destination,
			@RequestParam(value = "amount", required = true) String amount) {
		ModelAndView view = new ModelAndView();
		try {
			Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String message = "";
			boolean stoper = false;
			if (!amount.matches("[0-9]+")) {
				message += env.getProperty("app.amount.number");
				stoper = true;
			} else {
				if (Long.valueOf(amount) < 1) {
					message += env.getProperty("app.amount.mintransfer");
					stoper = true;
				}
				if (Long.valueOf(amount) > 1000) {
					message += env.getProperty("app.amount.maxtransfer");
					stoper = true;
				}
				if (Long.valueOf(amount) > account.getBalance()) {
					message += env.getProperty("app.amount.insufficient") + account.getBalance() + "<br>";
					stoper = true;
				}
			}

			if (stoper) {
				view = new ModelAndView("redirect:/transferAmount");
				redirectAttributes.addFlashAttribute("message", message);
			} else {
				view.addObject("accountNumber", account.getAccountNumber());
				view.addObject("destination", destination);
				DecimalFormat formatter = new DecimalFormat("#,###.00");
				view.addObject("amount", formatter.format(Double.valueOf(amount)));
				view.addObject("reference", generateTransferId());
				view.setViewName("transfer/confirm");
			}
		} catch (Exception e) {
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", env.getProperty("app.unknown.error"));
		}
		return view;
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@RequestParam(value = "destination", required = true) String destination,
			@RequestParam(value = "reference", required = true) String reference,
			@RequestParam(value = "amount", required = true) String amount) {
		ModelAndView view = new ModelAndView();
		try {
			Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String message = "";
			boolean stoper = false;
			if (!destination.matches("[0-9]+")) {
				message += env.getProperty("app.invalid.account");
				stoper = true;
			}

			List<Account> listAccount = accountService.findByAccountNumber(destination);
			if (listAccount.size() <= 0) {
				message += env.getProperty("app.invalid.account");
				stoper = true;
			}
			amount = amount.replace(".00", "");
			if (!amount.matches("[0-9]+")) {
				message += env.getProperty("app.amount.number");
				stoper = true;
			} else {
				if (Long.valueOf(amount) < 1) {
					message += env.getProperty("app.amount.mintransfer");
					stoper = true;
				}
				if (Long.valueOf(amount) > 1000) {
					message += env.getProperty("app.amount.maxtransfer");
					stoper = true;
				}
				if (Long.valueOf(amount) > account.getBalance()) {
					message += env.getProperty("app.amount.insufficient") + account.getBalance() + "<br>";
					stoper = true;
				}
			}

			if (stoper) {
				view = new ModelAndView("redirect:/transaction");
				redirectAttributes.addFlashAttribute("message", message);
				System.out.println(message);
			} else {
				account = transactionService.transfer(account.getAccountNumber(), Double.valueOf(amount), destination,
						reference);
				if (account == null)
					throw new Exception();
				SecurityContextHolder.getContext()
						.setAuthentication(new UsernamePasswordAuthenticationToken(account, null, new ArrayList<>()));
				redirectAttributes.addFlashAttribute("destination", destination);
				redirectAttributes.addFlashAttribute("amount", amount);
				redirectAttributes.addFlashAttribute("reference", reference);
				redirectAttributes.addFlashAttribute("balance", account.getBalance());
				view = new ModelAndView("redirect:/transferSummary");
			}
		} catch (Exception e) {
			e.printStackTrace();
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", env.getProperty("app.unknown.error"));
		}
		return view;
	}

	private String generateTransferId() {
		String NUMBER = "0123456789";
		String DATA_FOR_RANDOM_STRING = NUMBER;
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(6);
		for (int i = 0; i < 6; i++) {
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
			sb.append(rndChar);
		}
		return sb.toString();
	}
}
