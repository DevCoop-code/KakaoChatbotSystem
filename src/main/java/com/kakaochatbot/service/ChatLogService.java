package com.kakaochatbot.service;

import java.util.List;

import com.kakaochatbot.domain.ChatLogVO;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;

import scala.collection.Seq;

public interface ChatLogService 
{
	public void regist(ChatLogVO vo) throws Exception;
	
	public List<ChatLogVO> listAll() throws Exception;
	
	public List<ChatLogVO> listPage(int page) throws Exception;
	
	public int listCount() throws Exception;
	
	public String chatResponses(String request) throws Exception;
}
