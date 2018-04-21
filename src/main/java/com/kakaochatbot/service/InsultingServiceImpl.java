package com.kakaochatbot.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kakaochatbot.domain.InsultWordVO;
import com.kakaochatbot.persistence.InsultingDAO;

@Service
public class InsultingServiceImpl implements InsultingService
{
	@Inject
	private InsultingDAO dao;

	@Override
	public void regist(String word) throws Exception 
	{
		dao.create(word);
	}

	@Override
	public void remove(Integer bno) throws Exception 
	{
		dao.delete(bno);
	}

	@Override
	public List<InsultWordVO> listAll() throws Exception 
	{
		return dao.listAll();
	}	
}
