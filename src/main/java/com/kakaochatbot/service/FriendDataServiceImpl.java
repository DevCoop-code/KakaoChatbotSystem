package com.kakaochatbot.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kakaochatbot.persistence.FriendDataDAO;

@Service
public class FriendDataServiceImpl implements FriendDataService
{
	@Inject
	private FriendDataDAO dao;

	@Override
	public void regist(String kakaoid) throws Exception 
	{
		dao.create(kakaoid);
	}

	@Override
	public String read(String kakaoid) throws Exception 
	{
		return dao.read(kakaoid);
	}

	@Override
	public void remove(Integer bno) throws Exception 
	{
		dao.delete(bno);
	}
}
