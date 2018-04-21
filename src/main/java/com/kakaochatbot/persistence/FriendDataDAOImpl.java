package com.kakaochatbot.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class FriendDataDAOImpl implements FriendDataDAO
{
	@Inject
	private SqlSession session;
	
	private static String namespace = "org.zerock.mapper.FriendDataMapper";

	@Override
	public void create(String kakaoid) throws Exception 
	{
		session.insert(namespace + ".create", kakaoid);
	}

	@Override
	public String read(String kakaoid) throws Exception 
	{
		return session.selectOne(namespace + ".read", kakaoid);
	}

	@Override
	public void delete(Integer bno) throws Exception 
	{
		session.delete(namespace + ".delete", bno);
	}
}
