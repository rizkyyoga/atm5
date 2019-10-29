package com.yoga.atm.app.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoga.atm.app.dao.TransactionRepository;
import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.model.Transaction;
import com.yoga.atm.app.response.DatatableResponse;

@Controller
@PropertySource("classpath:message.properties")
@RequestMapping("")
public class ViewTransactionController {

	@Autowired
	private Environment env;

	@Autowired
	private TransactionRepository transactionService;

	@RequestMapping(value = "/viewTransaction", method = RequestMethod.GET)
	public ModelAndView inputAccountNumber(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		ModelAndView view = new ModelAndView();
		try {
			view.setViewName("view/transaction");
		} catch (Exception e) {
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
			view = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("message", env.getProperty("app.unknown.error"));
		}
		return view;
	}

	@RequestMapping(value = "/getDataTransaction", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public DatatableResponse getDataCommunity(HttpServletRequest request,
			@RequestParam(value = "draw", required = true) long draw,
			@RequestParam(value = "length", required = true) int length,
			@RequestParam(value = "start", required = true) int start) {
		DatatableResponse response = new DatatableResponse();
		try {
			Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			response.setDraw(draw);
			response.setRecordsFiltered(transactionService.countByAccountAccountNumber(account.getAccountNumber()));
			response.setRecordsTotal(transactionService.countByAccountAccountNumber(account.getAccountNumber()));
			Pageable paging = PageRequest.of(start / length, length, Sort.by("date").descending());
//			List<Transaction> listTransaction = transactionService.findPagedTransaction(account.getAccountNumber(),
//					start, length);
			List<Transaction> listTransaction = transactionService
					.findAllByAccountAccountNumber(account.getAccountNumber(), paging);
			List<List<String>> rows = new ArrayList<List<String>>();
			List<String> row = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
			DecimalFormat formatter = new DecimalFormat("#,###.00");
			for (Transaction transaction : listTransaction) {
				row = new ArrayList<String>();
				row.add(String.valueOf(transaction.getId()));
				row.add(dateFormat.format(transaction.getDate()));
				row.add(transaction.getType().toString());
				row.add(formatter.format(transaction.getAmount()));
				if (transaction.getDestinationAccount() != null)
					row.add(transaction.getDestinationAccount().getAccountNumber());
				else
					row.add(null);
				row.add(transaction.getReference());
				rows.add(row);
			}
			response.setData(rows);
		} catch (Exception e) {
			e.printStackTrace();
			response.setDraw(draw);
			response.setRecordsFiltered(0L);
			response.setRecordsTotal(0L);
			response.setData(new ArrayList<List<String>>());
		}
		return response;
	}

}
