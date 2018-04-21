package com.kakaochatbot.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController 
{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//@Inject
	//private RecipeProcessService recipeprocessservice;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) 
	{
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/*
	@RequestMapping(value = "/recipeprocess", method = RequestMethod.GET)
	public String recipeprocess(@RequestParam("code") int code, Model model) throws Exception
	{
		logger.info("/recipeprocess");
		logger.info("code = " + code);
		
		model.addAttribute("list", recipeprocessservice.getRecipeProcess(code));
		
		return "recipeprocess";
	}
	*/
}