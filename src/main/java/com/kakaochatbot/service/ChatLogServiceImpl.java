package com.kakaochatbot.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kakaochatbot.domain.ChatLogVO;
import com.kakaochatbot.persistence.ChatLogDAO;

@Service
public class ChatLogServiceImpl implements ChatLogService
{
	@Inject
	private ChatLogDAO chatdao;
	
	@Override
	public void regist(ChatLogVO vo) throws Exception 
	{
		chatdao.create(vo);
	}

	@Override
	public List<ChatLogVO> listAll() throws Exception 
	{
		return chatdao.listAll();
	}

	@Override
	public List<ChatLogVO> listPage(int page) throws Exception 
	{
		return chatdao.listPage(page);
	}

	@Override
	public int listCount() throws Exception 
	{
		return chatdao.countPaging();
	}

	@Override
	public String chatResponses(String request) throws Exception 
	{
		List<String> response_list = chatdao.reply(request);
		if(response_list.isEmpty())
		{
			return "nonereply";
		}
		else if(response_list.get(0).equals(request))
		{
			return "nonereply";
		}
		return response_list.get(0);
	}
}
