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
import com.kakaochatbot.service.ChatbotSentenceService;

@Controller
@RequestMapping("/corpus/*")
public class CorpusController 
{
	private static final Logger logger = LoggerFactory.getLogger(CorpusController.class);
	
	@Inject
	private ChatbotSentenceService service;
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(@RequestParam("page") int page, Model model) throws Exception
	{
		model.addAttribute("list", service.listPage(page));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setTotalCount(service.listCount(), page);
		
		model.addAttribute("pageMaker", pageMaker);
	}
}
