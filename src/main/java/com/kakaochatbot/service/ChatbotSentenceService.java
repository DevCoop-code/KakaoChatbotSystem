package com.kakaochatbot.service;

import java.util.List;

import com.kakaochatbot.domain.ClassifySentenceVO;
import com.kakaochatbot.domain.CommendResponseVO;
import com.kakaochatbot.domain.MorphismDTO;
import com.kakaochatbot.domain.MorphismVO;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;

import scala.collection.Seq;

public interface ChatbotSentenceService 
{
	public CommendResponseVO commandSentence(String message) throws Exception;
	
	public MorphismDTO MorphemeAnalysis(Seq<KoreanTokenizer.KoreanToken> tokens, String kakaomessage) throws Exception;
	
	public String makeSentence(MorphismDTO morphDTO) throws Exception;
	
	public List<MorphismVO> listAll() throws Exception;
	
	public List<MorphismVO> listPage(int page) throws Exception;
	
	public int listCount() throws Exception;
	
	public ClassifySentenceVO classifyTheSentence(String request_message) throws Exception;
	
	public String AbbreviationSentence(String request) throws Exception;
	
	public String questionResponse(Seq<KoreanTokenizer.KoreanToken> stemmed) throws Exception;
	
	public boolean insultingFilter(String message) throws Exception;
}
