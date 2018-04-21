package com.kakaochatbot.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kakaochatbot.domain.MorphismVO;

@Repository
public class MorphismDAOImpl implements MorphismDAO
{

	@Inject
	private SqlSession session;
	
	private static String namespace = "org.zerock.mapper.BotSentenceMapper";
	
	@Override
	public void create(MorphismVO vo) throws Exception 
	{
		session.insert(namespace + ".create", vo);
	}

	@Override
	public MorphismVO check(MorphismVO vo) throws Exception 
	{
		return session.selectOne(namespace + ".check", vo);
	}

	@Override
	public void increase_frequent(MorphismVO vo) throws Exception 
	{
		session.update(namespace + ".freqincrease", vo);
	}

	@Override
	public List<MorphismVO> getWords(String target) throws Exception 
	{
		return session.selectList(namespace + ".getwords", target);
	}

	@Override
	public List<MorphismVO> listAll() throws Exception 
	{
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public List<MorphismVO> listPage(int page) throws Exception 
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
}
