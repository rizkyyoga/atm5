package com.yoga.atm.app.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoga.atm.app.Exception.WrongInputException;
import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.service.AccountService;

@Controller
@PropertySource("classpath:message.properties")
@RequestMapping("")
public class WelcomeController {

	@Autowired
	private Environment env;

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView inputAccountNumber(HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttributes) {
		ModelAndView view = new ModelAndView();
		try {
			if (!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal()))
				return new ModelAndView("redirect:/transaction");
			if (request.getSession().getAttribute("message") != null) {
				view.addObject("message", session.getAttribute("message"));
				session.removeAttribute("message");
			}
			view.setViewName("welcome/inputAccountNumber");
		} catch (Exception e) {
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", env.getProperty("app.unknown.error"));
		}
		return view;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@RequestParam("file") MultipartFile file) {
		ModelAndView view = new ModelAndView();
		List<Account> inputList = new ArrayList<Account>();
		try {
			InputStream inputFS = file.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
			inputList = br.lines().skip(1).map(mapToItem).parallel().collect(Collectors.toList());
			inputList = inputList.stream().filter(distinctByKey(Account::getAccountNumber))
					.collect(Collectors.toList());
			inputList.parallelStream().forEach(account -> {
				accountService.save(account);
			});
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("notif", env.getProperty("app.upload.success"));
		} catch (Exception e) {
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", env.getProperty("app.unknown.error"));
		}
		return view;
	}

	@RequestMapping(value = "/pin", method = RequestMethod.GET)
	public ModelAndView inputPin(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@RequestParam(value = "an", required = true) String accountNumber) {
		ModelAndView view = new ModelAndView();
		try {
			if (!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal()))
				return new ModelAndView("redirect:/transaction");
			String message = "";
			if (accountNumber.length() != 6) {
				message += env.getProperty("app.accountnumber.size");
				throw new WrongInputException(message);
			}
			if (!accountNumber.matches("[0-9]+")) {
				message += env.getProperty("app.accountnumber.number");
				throw new WrongInputException(message);
			}
			view.addObject("accountNumber", accountNumber);
			view.setViewName("welcome/inputPin");
		} catch (WrongInputException e) {
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		} catch (Exception e) {
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", env.getProperty("app.unknown.error"));
		}
		return view;
	}

	private Function<String, Account> mapToItem = (line) -> {
		String[] p = line.split(";");
		Account item = new Account();
		item.setName(p[0]);
		item.setPin(p[1]);
		item.setBalance(Double.valueOf(p[2]));
		item.setAccountNumber(p[3]);
		return item;
	};

	private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}
}
