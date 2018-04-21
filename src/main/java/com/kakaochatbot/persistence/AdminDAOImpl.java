package com.kakaochatbot.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakaochatbot.domain.AdminVO;

@Repository
public class AdminDAOImpl implements AdminDAO
{
	@Inject
	private SqlSession session;
	
	private static String namespace = "org.zerock.mapper.AdminMapper";

	@Override
	public AdminVO login(AdminVO vo) throws Exception 
	{
		return session.selectOne(namespace + ".login", vo);
	}
}
