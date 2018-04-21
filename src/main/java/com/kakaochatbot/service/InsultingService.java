package com.kakaochatbot.service;

import java.util.List;

import com.kakaochatbot.domain.InsultWordVO;

public interface InsultingService 
{
	public void regist(String word) throws Exception;
	
	public void remove(Integer bno) throws Exception;
	
	public List<InsultWordVO> listAll() throws Exception;
}
