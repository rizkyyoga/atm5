package com.yoga.atm.app.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.service.TransactionService;

@Controller
@PropertySource("classpath:message.properties")
@RequestMapping("")
public class WithdrawController {

	@Autowired
	private Environment env;

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public ModelAndView withdraw(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		ModelAndView view = new ModelAndView();
		try {
			Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			view.addObject("accountNumber", account.getAccountNumber());
			view.setViewName("withdraw/index");
		} catch (Exception e) {
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", env.getProperty("app.unknown.error"));
		}
		return view;
	}

	@RequestMapping(value = "/withdrawl", method = RequestMethod.GET)
	public ModelAndView withdrawl(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@RequestParam(value = "amount", required = true) String amount) {
		ModelAndView view = new ModelAndView();
		try {
			Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String message = "";
			boolean goToSummary = true;
			if (!amount.matches("[0-9]+")) {
				goToSummary = false;
				message += env.getProperty("app.amount.number");
			} else {
				if (Double.valueOf(amount) % 10 != 0) {
					goToSummary = false;
					message += env.getProperty("app.invalid.amount");
				}
				if (Double.valueOf(amount) > 1000) {
					goToSummary = false;
					message += env.getProperty("app.amount.maximum");
				}
				if (Double.valueOf(amount) > account.getBalance()) {
					goToSummary = false;
					message += env.getProperty("app.amount.insufficient") + account.getBalance() + "<br>";
				}
			}

			if (goToSummary) {
				account = transactionService.withdraw(account.getAccountNumber(), Double.valueOf(amount));
				if (account == null)
					throw new Exception();
				SecurityContextHolder.getContext()
						.setAuthentication(new UsernamePasswordAuthenticationToken(account, null, new ArrayList<>()));
				view = new ModelAndView("redirect:/withdrawSummary");
				DecimalFormat formatter = new DecimalFormat("#,###.00");
				redirectAttributes.addFlashAttribute("balance", formatter.format(account.getBalance()));
				redirectAttributes.addFlashAttribute("withdraw", formatter.format(Double.valueOf(amount)));
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
				redirectAttributes.addFlashAttribute("date", dateFormat.format(new Date()));
				redirectAttributes.addFlashAttribute("accountNumber", account.getAccountNumber());
			} else {
				view = new ModelAndView("redirect:/withdraw");
				redirectAttributes.addFlashAttribute("message", message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", env.getProperty("app.unknown.error"));
		}
		return view;
	}

}
