package com.kakaochatbot.persistence;

import java.util.List;

import com.kakaochatbot.domain.ChatLogVO;

public interface ChatLogDAO 
{
	public void create(ChatLogVO vo) throws Exception;
	
	public List<ChatLogVO> listAll() throws Exception;
	
	public List<ChatLogVO> listPage(int page) throws Exception;
	
	public int countPaging() throws Exception;
	
	public List<String> reply(String request) throws Exception;
}
