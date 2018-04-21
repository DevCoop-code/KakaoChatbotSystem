package com.kakaochatbot.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakaochatbot.domain.InsultWordVO;

@Repository
public class InsultingDAOImpl implements InsultingDAO
{

	@Inject
	private SqlSession session;
	
	private static String namespace = "org.zerock.mapper.InsultMapper";
	
	@Override
	public void create(String words) throws Exception 
	{
		session.insert(namespace + ".create", words);
	}

	@Override
	public void delete(Integer bno) throws Exception 
	{
		session.delete(namespace + ".delete", bno);	
	}

	@Override
	public List<InsultWordVO> listAll() throws Exception 
	{
		return session.selectList(namespace + ".listAll");
	}
}
