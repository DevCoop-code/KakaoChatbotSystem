package com.kakaochatbot.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakaochatbot.domain.ChatLogVO;

@Repository
public class ChatLogDAOImpl implements ChatLogDAO
{

	@Inject
	private SqlSession session;
	
	private static String namespace = "org.zerock.mapper.ChatLogMapper";
	
	@Override
	public void create(ChatLogVO vo) throws Exception 
	{
		session.insert(namespace + ".create", vo);
	}

	@Override
	public List<ChatLogVO> listAll() throws Exception 
	{
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public List<ChatLogVO> listPage(int page) throws Exception 
	{
		if(page <= 0)
		{
			page = 1;
		}
		page = (page - 1) * 10;
		
		return session.selectList(namespace + ".listPage", page);
	}

	@Override
	public int countPaging() throws Exception 
	{
		return session.selectOne(namespace + ".countPaging");
	}

	@Override
	public List<String> reply(String request) throws Exception 
	{
		return session.selectList(namespace + ".responsechat", request);
	}
}
