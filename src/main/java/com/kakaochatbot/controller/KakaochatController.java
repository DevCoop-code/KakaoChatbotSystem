package com.kakaochatbot.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kakaochatbot.domain.ChatLogVO;
import com.kakaochatbot.domain.ChatbotKeyboardVO;
import com.kakaochatbot.domain.ChatbotRequestVO;
import com.kakaochatbot.domain.ChatbotResponseVO;
import com.kakaochatbot.domain.ClassifySentenceVO;
import com.kakaochatbot.domain.CommendResponseVO;
import com.kakaochatbot.domain.MorphismDTO;
import com.kakaochatbot.response.MessageResponse;
import com.kakaochatbot.response.PhotoResponse;
import com.kakaochatbot.service.ChatLogService;
import com.kakaochatbot.service.ChatbotSentenceService;
import com.kakaochatbot.specialresponse.SpecialResponse;

@RestController
public class KakaochatController 
{
	private static final Logger logger = LoggerFactory.getLogger(KakaochatController.class);
	
	@Inject
	private ChatbotSentenceService sentence_service;
	@Inject
	private ChatLogService chat_service;
	
	//이용자가 최초로 채팅방에 들어올때 기본으로 키보드 영역에 표시될 자동응답 명령을 호출
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET)
	public ChatbotKeyboardVO keyboard()
	{
		logger.info("/keyboard");
		ChatbotKeyboardVO vo = new ChatbotKeyboardVO();
		vo.setType("text");
		
		return vo;
	}
	
	//사용자가 선택한 명령어를 서버로 전달해주는 API
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ChatbotResponseVO message(@RequestBody ChatbotRequestVO vo) throws Exception
	{
		logger.info("/message");

		String kakaomessage, kakaouser, response = "error";
		
		kakaomessage = vo.getContent();
		kakaouser = vo.getUser_key();
		//사용자의 입력 텍스트
		logger.info(kakaomessage);
		logger.info(kakaouser);
		
		ChatbotResponseVO response_vo = new ChatbotResponseVO();
		MessageResponse message = new MessageResponse();
		PhotoResponse photo = null;
		
		// 응답이 이미 존재하는 경우
		//String cache_response = chat_service.chatResponses(kakaomessage);
		
		/*
		 * 응답 보내기
		 */
		//욕설 필터 true=욕설이 포함되어있음 / false = 욕설이 포함되어 있지 않음
		boolean insultlean = sentence_service.insultingFilter(kakaomessage);
		
		//request에 욕설이 포함되어 있는 경우
		if(insultlean)
		{
			response = SpecialResponse.ResponseAboutInsult();
		}else //request에 욕설이 포함되어 있지 않은 경우
		{
			//인사말 처리 => 하이, ㅎㅇ, ㅎㅇㄹ, 안녕하세요, 안녕, 방가, 방가안녕 / ㅂㅇ, ㅂㅇㄹ, 바이, 잘가, 
			//1. 명령어 처리 ex]@날씨
			CommendResponseVO commendVO = sentence_service.commandSentence(kakaomessage);
			
			//2. 형태소 분석 - 특수 응답
			if(commendVO == null)
			{
				//형태소 분석
				ClassifySentenceVO classifyvo = sentence_service.classifyTheSentence(kakaomessage);
				
				//줄임말 처리
				if(classifyvo.getType() == 0)
				{
					//의미 없는 자음, 모음을 걸러주는 역할도 존재
					response = sentence_service.AbbreviationSentence(classifyvo.getParticleMessage());
				}
				//특수질문 처리
				else if(classifyvo.getType() == 1)
				{
					response = sentence_service.questionResponse(classifyvo.getStemmed());
				}
				//Filter - 의미 없는 말을 걸러냄 - 영어
				else if(classifyvo.getType() == 2)
				{
					response = SpecialResponse.ResponseAboutEnglish();
				}
				//Filter - 의미 없는 말을 걸러냄 - 숫자
				else if(classifyvo.getType() == 3)
				{
					response = SpecialResponse.ResponseAboutNumber();
				}
				//Filter - 의미 없는 말을 걸러냄 - 특수문자
				else if(classifyvo.getType() == 4)
				{
					response = SpecialResponse.ResponseAboutSpecialCharacter();
				}
				//문장 학습 && 학습한 데이터로 문장 생성
				else
				{
					/*
					 * 문장 학습
					 */
					MorphismDTO morphDTO = sentence_service.MorphemeAnalysis(classifyvo.getTokens(), kakaomessage);
					/*
					 * 문장 생성
					 */
					if(morphDTO.getType() == 0)
					{
						response = SpecialResponse.ResponseAboutLongSentence();
					}else
					{
						response = sentence_service.makeSentence(morphDTO);
					}
				}
			}else
			{
				response = commendVO.getResponseMessage();
				
				photo = new PhotoResponse();
				photo.setUrl("http://124.51.164.17:8080/image/displayFile?fileName=" + commendVO.getResponseImage());
				logger.info("http://124.51.164.17:8080/image/displayFile?fileName=" + commendVO.getResponseImage());
				photo.setHeight(50);
				photo.setWidth(50);
			}	
		}
		
		message.setText(response);
		if(photo != null)
		{
			message.setPhoto(photo);
		}
		response_vo.setMessage(message);
		
		//LOG DB에 적재
		ChatLogVO chatlogvo = new ChatLogVO();
		chatlogvo.setRequest(kakaomessage);
		chatlogvo.setResponse(response);
		chatlogvo.setUser(kakaouser);
		chat_service.regist(chatlogvo);
	
		return response_vo;
	}
}
