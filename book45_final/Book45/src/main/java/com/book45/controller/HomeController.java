package com.book45.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book45.domain.Criteria;
import com.book45.domain.PageDTO;
import com.book45.service.AlbumService;
import com.book45.service.BookService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AlbumService albumService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "main";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void main(Criteria cri, Model model) {
		int total = bookService.getTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri,total));
		
		int albumTotal = albumService.getTotal(cri);
		model.addAttribute("albumPageMaker", new PageDTO(cri, albumTotal));
	}
	
}
