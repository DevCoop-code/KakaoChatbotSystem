package com.kakaochatbot.persistence;

import com.kakaochatbot.domain.AdminVO;

public interface AdminDAO 
{
	public AdminVO login(AdminVO vo) throws Exception;
}
