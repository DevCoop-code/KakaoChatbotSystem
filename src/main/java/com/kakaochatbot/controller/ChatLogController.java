package com.kakaochatbot.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kakaochatbot.domain.PageMaker;
import com.kakaochatbot.service.ChatLogService;

@Controller
@RequestMapping("/chatlog/*")
public class ChatLogController 
{
	private static final Logger logger = LoggerFactory.getLogger(ChatLogController.class);
	
	@Inject
	private ChatLogService chat_service;
	
	@RequestMapping(value="/listAll", method = RequestMethod.GET)
	public void listAll(@RequestParam("page") int page, Model model) throws Exception
	{
		logger.info("ingredient info show all list..........");
		model.addAttribute("list", chat_service.listPage(page));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setTotalCount(chat_service.listCount(), page);
		
		model.addAttribute("pageMaker", pageMaker);
	}
}