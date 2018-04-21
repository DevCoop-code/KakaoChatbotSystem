package com.kakaochatbot.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kakaochatbot.service.InsultingService;

@Controller
@RequestMapping("/insult/*")
public class InsultingWordController 
{
	private static final Logger logger = LoggerFactory.getLogger(InsultingWordController.class);
	
	@Inject
	private InsultingService service;
	
	@RequestMapping(value="/InsultingWordsManagement", method = RequestMethod.GET)
	public void showPage(Model model) throws Exception
	{
		logger.info("Insulting Page Show..........");
		
		model.addAttribute("list", service.listAll());
	}
	
	@RequestMapping(value="/InsultingWordsManagement", method = RequestMethod.POST)
	public String regist(String registeredWord) throws Exception
	{
		logger.info(registeredWord);
		
		service.regist(registeredWord);
		
		return "/insult/InsultingWordsPost";
	}
}
