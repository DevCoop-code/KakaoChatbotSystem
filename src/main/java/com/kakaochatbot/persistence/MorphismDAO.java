package com.kakaochatbot.persistence;

import java.util.List;

import com.kakaochatbot.domain.MorphismVO;

public interface MorphismDAO 
{
	public void create(MorphismVO vo) throws Exception;
	
	public MorphismVO check(MorphismVO vo) throws Exception;
	
	public void increase_frequent(MorphismVO vo) throws Exception;
	
	public List<MorphismVO> getWords(String target) throws Exception;
	
	public List<MorphismVO> listAll() throws Exception;
	
	public List<MorphismVO> listPage(int page) throws Exception;
	
	public int countPaging() throws Exception;
}
