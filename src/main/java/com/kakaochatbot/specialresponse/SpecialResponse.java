package com.kakaochatbot.specialresponse;

import java.util.Random;

public class SpecialResponse 
{
	private static final String[] english_response = 
		{
			"님.. 저 한글 학습만으로 벅찬거 안보이세요?", "한국 사람이면 한국말을 사용하세요!!", "한국말 안쓰냐...",
			"영어도 못하는 놈이 영어를 꼭 쓰려고 해요 하여튼...", "세종대왕님이 열심히 한글 만들어놨더니 영어를 쓰냐...",
			"한글이라고 하는 훌륭한 표기법을 놔두고 왜 치즈향 풍기나요..", "응 영어 몰라 ㅅㄱㅂ"
		};
	private static final String[] number_response =
		{
			"수포자 안보이나요?", "한글 학습도 벅찬데 숫자 놀음까지 하라고 하냐...", "숫자는 못한다 애야", "너도 수학을 그리 잘해보이지는 않는데?",
			"응 못해~", "응 안해~", "응 나 수포~~", "수학 잘하는 척 하지 마라 못하는 거 안다", "난 숫자가 싫어 죽여버리고 싶어"
		};
	private static final String[] specialCharacter_response =
		{
			"그건 무슨 말인가요?", "굉장히 말이군요 무슨말인지 가르켜 주실수 있어요?", "무슨 말인지 잘 모르겠는데 알려주실수 있으세요?",
			"처음 들어보는 말이네요 님 버드?"
		};
	private static final String[] longSentence_response =
		{
			"아직 똑똑하지 못해 긴문장 처리하는건 좀 힘들어..", "긴데? 좀 짧게 이야기 해줄래?", "우리 짧게짧게 가자",
			"문장이 좀 길면 내가 처리하는데 버벅 거려"
		};
	private static final String[] insultSentence_response =
		{
			"나쁜말은 배우지 말라고 배웠어요", "이제 더 이상 저에게 나쁜말을 학습시키려 하지 마세요", "신보라보라보라",
			"나쁜말은 안되요", "네네 무슨 말인지 대충 이해했습니다.."
		};
	
	static Random random = new Random();
	
	public static String ResponseAboutEnglish()
	{
		int abbreviation_num = random.nextInt(english_response.length);
		
		return english_response[abbreviation_num];
	}
	
	public static String ResponseAboutNumber()
	{
		int abbreviation_num = random.nextInt(number_response.length);
		
		return number_response[abbreviation_num];
	}
	
	public static String ResponseAboutSpecialCharacter()
	{
		int abbreviation_num = random.nextInt(specialCharacter_response.length);
		
		return specialCharacter_response[abbreviation_num];
	}
	
	public static String ResponseAboutLongSentence()
	{
		int abbreviation_num = random.nextInt(longSentence_response.length);
		
		return longSentence_response[abbreviation_num];
	}
	
	public static String ResponseAboutInsult()
	{
		int abbreviation_num = random.nextInt(insultSentence_response.length);
		
		return insultSentence_response[abbreviation_num];
	}
}
