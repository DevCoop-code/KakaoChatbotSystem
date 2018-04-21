package com.kakaochatbot.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kakaochatbot.domain.AdminVO;
import com.kakaochatbot.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController 
{
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	private AdminService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("vo") AdminVO vo)
	{
		
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(AdminVO vo, HttpSession session, Model model) throws Exception
	{
		AdminVO adminvo = service.login(vo);
		
		if(adminvo == null)
		{
			return;
		}
		
		model.addAttribute("adminVO", vo);
	}
}
