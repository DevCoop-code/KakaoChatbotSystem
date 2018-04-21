package com.kakaochatbot.service;

public interface FriendDataService 
{
	public void regist(String kakaoid) throws Exception;
	
	public String read(String kakaoid) throws Exception;
	
	public void remove(Integer bno) throws Exception;
}
