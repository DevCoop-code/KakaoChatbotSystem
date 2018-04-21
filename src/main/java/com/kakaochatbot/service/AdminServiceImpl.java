package com.kakaochatbot.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kakaochatbot.domain.AdminVO;
import com.kakaochatbot.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService
{
	@Inject
	private AdminDAO dao;

	@Override
	public AdminVO login(AdminVO vo) throws Exception 
	{
		return dao.login(vo);
	}
}
