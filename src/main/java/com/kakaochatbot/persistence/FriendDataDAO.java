package com.kakaochatbot.persistence;

public interface FriendDataDAO 
{
	public void create(String kakaoid) throws Exception;
	
	public String read(String kakaoid) throws Exception;
	
	public void delete(Integer bno) throws Exception;
}
