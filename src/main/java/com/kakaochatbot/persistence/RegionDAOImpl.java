package com.kakaochatbot.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class RegionDAOImpl implements RegionDAO
{

	@Inject
	private SqlSession session;
	
	private static String namespace = "org.zerock.mapper.RegionDBMapper";
	
	@Override
	public List<String> read_large() throws Exception 
	{
		return session.selectList(namespace + ".read_large");
	}
}
