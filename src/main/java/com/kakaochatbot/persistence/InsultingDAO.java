package com.kakaochatbot.persistence;

import java.util.List;

import com.kakaochatbot.domain.InsultWordVO;

public interface InsultingDAO 
{
	public void create(String words) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<InsultWordVO> listAll() throws Exception;
}