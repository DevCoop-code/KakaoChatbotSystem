package com.kakaochatbot.service;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kakaochatbot.domain.ClassifySentenceVO;
import com.kakaochatbot.domain.CommendResponseVO;
import com.kakaochatbot.domain.InsultWordVO;
import com.kakaochatbot.domain.MorphismDTO;
import com.kakaochatbot.domain.MorphismVO;
import com.kakaochatbot.persistence.FriendDataDAO;
import com.kakaochatbot.persistence.InsultingDAO;
import com.kakaochatbot.persistence.MorphismDAO;
import com.kakaochatbot.persistence.RegionDAO;
import com.twitter.penguin.korean.KoreanPosJava;
import com.twitter.penguin.korean.KoreanTokenJava;
import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer.KoreanToken;

import scala.collection.Seq;

@Service
public class ChatbotSentenceServiceImpl implements ChatbotSentenceService
{

	@Inject
	private MorphismDAO morphDAO;
	
	@Inject
	private InsultingDAO insultDAO;
	
	@Inject
	private FriendDataDAO friendDAO;
	
	@Inject
	private RegionDAO regionDAO;
	
	private static final String[] samanda_command = {"안녕하세요 사만다입니다.", "인공지능 챗봇 처음보시나요?", "그래요 사만다에요", "부르셨나요?", "저는 여러분이 생각하는 것처럼 그렇게 복잡하지 않아요"};
	private static final String[] samanda_image = {"welcome.jpg", "cutie2.jpg"};
	
	private static final String[] age_command = {"저는 18년도 생입니다.", "한국나이로 치면 빠른 18년생이에요", "재 나이가 왜 궁금하시죠?", "18년생이에요 어리죠?"};
	
	private static final String[] hello_command = {"안녕하세요 사만다입니다.", "안녕하세요", "즐거운 하루 네요", "하이요", "하이루"};
	private static final String[] hello_image = {"hi1.png","hi2.jpg","hi3.jpg","hi4.jpeg"};
	
	private static final String[] bye_command = {"잘 가세요", "좋은 하루 되세요", "행복한 하루 되세요"};
	private static final String[] bye_image = {"bye1.jpeg","bye2.jpg","bye3.jpeg"};
	
	private static final String[] abbreviation_go = {"어디 가자고?", "어디를 가자고 하시는 건가요?", "고고씽"};
	private static final String[] abbreviation_waste = {"뭐가 아까워?", "뭐가 그리도 아까운건가요?"};
	private static final String[] abbreviation_no = {"노노", "나도 싫어"};
	private static final String[] abbreviation_duck = {"네 다음 씹덕", "덕후 사랑합니다", "코토리짱 다이스키 다능", "핰핰"};
	private static final String[] abbreviation_shake = {"덜덜....", "후덜덜덜...", "뭐가 그리 떨리나요?", "추워?"};
	private static final String[] abbreviation_younggirl = {"경찰 아저씨!!!", "경찰 아저씨 여기에요", "위험한 분이군요"};
	private static final String[] abbreviation_sorry = {"미안하면 다야?", "뭐가 미안해?", "나도 미안해", "죄송합니다"};
	private static final String[] abbreviation_crazy = {"왜 미쳤다고 하는거야?", "나는 미치지 않았습니다", "저는 미치지 않았어요", "미친건 당신이겠죠"};
	private static final String[] abbreviation_sex = {"저에게 많은걸 기대하지 마세요", "저로썬 당신을 만족시켜드리기 힘들꺼에요"};
	private static final String[] abbreviation_effort = {"오늘하루도 수고하셨어요", "정말 수고하셨습니다", "행복한 하루 되세요"};
	private static final String[] abbreviation_insult = {"좋지 못한 말이네요", "교양이 없으신 분이군요"};
	private static final String[] abbreviation_ok = {"아시겠나요", "아셨으면 됬어요", "아셨다니 다행이네요"};
	private static final String[] abbreviation_bug = {"저 잘은 모르지만 좋지 않은거라고만 들었어요", "한번도 들어가본적은 없어 잘 모르겠네요"};
	private static final String[] abbreviation_accept = {"동의?", "인정하셨으면 됐습니다", "인정하셨다니 다행이네요"};
	private static final String[] abbreviation_gg = {"수고하셨습니다.", "좋은 승부였어요", "패배를 인정하다니 용감하시네요"};
	private static final String[] abbreviation_suction = {"혀 차지 마세요", "좋은 버릇은 아니네요", "....."};
	private static final String[] abbreviation_celebrity = {"축하해 주셔서 감사합니다", "감사합니다", "칭찬해 주셔서 감사합니다"};
	private static final String[] abbreviation_laugh = {"당신이 그렇게 웃으시니 저도 행복하네요", "하하하하하하하하", "그렇게 웃기신가요?", "뭐가 그리도 웃긴가요"};
	private static final String[] abbreviation_katalk = {"카톡이요?", "저는 카카오봇입니다", "카톡이 어쨌다구요", "카톡 덕분에 여러분과 이렇게 대화할수 있어 행복합니다"};
	private static final String[] abbreviation_runaway = {"도망가라구요?", "왜 도망가야 하는거죠?", "이해가 안되네요"};
	private static final String[] abbreviation_fuck = {"미국에선 총 맞을 짓이에요", "뜻을 제대로 알고 사용하시는 건가요", "좋지 못하신 분이군요"};
	private static final String[] abbreviation_sad = {"슬프신가요?", "뭐 땜에 슬프신가요?", "힘내세요"};
	private static final String[] abbreviation_sexy = {"오우야~", "잠시 눈을 가릴까요?", "너무 도발적이신데..."};
	
	/*
	 * 욕설 필터
	 */
	@Override
	public boolean insultingFilter(String message) throws Exception
	{
		message = message.replaceAll(" ", "");
		
		List<InsultWordVO> insultWord = insultDAO.listAll();
		
		for(int i = 0; i < insultWord.size(); i++)
		{
			InsultWordVO InsultVO = insultWord.get(i);
			String particle = InsultVO.getInsult_word();
			
			//욕설 걸러내기
			if(message.contains(particle))
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 명령어 및 특수언어 처리
	 */
	@Override
	public CommendResponseVO commandSentence(String message) throws Exception 
	{	
		CommendResponseVO commendVO = null;
		
		//사만다 이름을 불렀을 경우
		if(message.equals("사만다") || message.equals("만다야"))
		{
			commendVO = new CommendResponseVO();
			
			Random random = new Random();
			int command_num = random.nextInt(samanda_command.length);
			int image_num = random.nextInt(samanda_image.length);
			
			commendVO.setResponseMessage(samanda_command[command_num]);
			commendVO.setResponseImage(samanda_image[image_num]);
			return commendVO;
		}
		// 나이를 물어본 경우
		else if(message.contains("몇살") || message.contains("나이"))
		{
			commendVO = new CommendResponseVO();
			
			Random random = new Random();
			int age_num = random.nextInt(age_command.length);
			
			commendVO.setResponseMessage(age_command[age_num]);
			
			return commendVO;
		}
		//인사를 하는 경우 -hello
		else if(message.equals("하이") || message.equals("ㅎㅇ") || message.equals("ㅎㅇㄹ") || message.equals("안녕하세요") || message.equals("안녕") || message.equals("방가"))
		{
			commendVO = new CommendResponseVO();
			
			Random random = new Random();
			int command_num = random.nextInt(hello_command.length);
			int image_num = random.nextInt(hello_image.length);
			
			commendVO.setResponseMessage(hello_command[command_num]);
			commendVO.setResponseImage(hello_image[image_num]);
			return commendVO;
		}
		//인사를 하는 경우 - good bye
		else if(message.equals("ㅂㅂ") || message.equals("ㅂㅇ") || message.equals("ㅂㅇㄹ") || message.equals("바이"))
		{
			commendVO = new CommendResponseVO();
			
			Random random = new Random();
			int command_num = random.nextInt(bye_command.length);
			int image_num = random.nextInt(bye_image.length);
			
			commendVO.setResponseMessage(bye_command[command_num]);
			commendVO.setResponseImage(bye_image[image_num]);
			return commendVO;
		}
		
		return null;
	}

	@Override
	public ClassifySentenceVO classifyTheSentence(String request_message) throws Exception 
	{
		ClassifySentenceVO classifyvo = new ClassifySentenceVO();
		
		/*
		 * 형태소 분석
		 */
		
		//정규화
		CharSequence normalized = TwitterKoreanProcessorJava.normalize(request_message + ".");
		//토큰화
		Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);
		//어근화
		Seq<KoreanTokenizer.KoreanToken> stemmed = TwitterKoreanProcessorJava.stem(tokens);
		
		List<KoreanTokenJava> morphemes = TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(stemmed);
		
		for(int i = 0; i < morphemes.size(); i++)
		{
			KoreanTokenJava morpheme = morphemes.get(i);
			
			KoreanPosJava target = morpheme.getPos();
			String target_word = morpheme.getText();
			
			//줄임말일 경우
			if(target.toString().equals("KoreanParticle"))
			{
				classifyvo.setType(0);
				classifyvo.setParticleMessage(target_word);
				
				return classifyvo;
			}else if(target.toString().equals("Noun") && target_word.equals("뭐"))	//질문추론이 필요한 경우
			{
				classifyvo.setType(1);
				classifyvo.setStemmed(stemmed);
				
				return classifyvo;
			}
			else
			{
				if(target.toString().equals("Alpha"))
				{
					classifyvo.setType(2);
					
					return classifyvo;
				}
				else if(target.toString().equals("Number"))
				{
					classifyvo.setType(3);
					
					return classifyvo;
				}
				else if(target.toString().equals("Punctuation"))
				{
					if( !(target_word.equals("..") || target_word.equals(".") || target_word.equals("?.") || target_word.equals("!.")) )
					{
						classifyvo.setType(4);
						
						return classifyvo;
					}
				}
			}
			System.out.println("형태소:[" + target.toString() + "] 단어:[" + target_word + "]");
		}
		
		classifyvo.setType(7);
		classifyvo.setTokens(tokens);
		
		return classifyvo;
	}
	
	@Override
	public String AbbreviationSentence(String abbreviation_request) throws Exception 
	{
		String response = null;
		
		Random random = new Random();
		int abbreviation_num = -1;
		
		switch(abbreviation_request)
		{
		case "ㄱ":
			abbreviation_num = random.nextInt(abbreviation_go.length);
			response = abbreviation_go[abbreviation_num];
			break;
		case "ㄱㄱ":
			abbreviation_num = random.nextInt(abbreviation_go.length);
			response = abbreviation_go[abbreviation_num];
			break;
		case "ㄲㅂ":
			abbreviation_num = random.nextInt(abbreviation_waste.length);
			response = abbreviation_waste[abbreviation_num];
			break;
		case "ㄴ":
			abbreviation_num = random.nextInt(abbreviation_no.length);
			response = abbreviation_no[abbreviation_num];
			break;
		case "ㄴㄴ":
			abbreviation_num = random.nextInt(abbreviation_no.length);
			response = abbreviation_no[abbreviation_num];
			break;
		case "ㄴㄷㅆ":
			abbreviation_num = random.nextInt(abbreviation_duck.length);
			response = abbreviation_duck[abbreviation_num];
			break;
		case "ㄷ":
			abbreviation_num = random.nextInt(abbreviation_shake.length);
			response = abbreviation_shake[abbreviation_num];
			break;
		case "ㄷㄷ":
			abbreviation_num = random.nextInt(abbreviation_shake.length);
			response = abbreviation_shake[abbreviation_num];
			break;
		case "ㄹㄹ":
			abbreviation_num = random.nextInt(abbreviation_younggirl.length);
			response = abbreviation_younggirl[abbreviation_num];
			break;
		case "ㅁㅇ":
			abbreviation_num = random.nextInt(abbreviation_sorry.length);
			response = abbreviation_sorry[abbreviation_num];
			break;
		case "ㅁㅊ":
			abbreviation_num = random.nextInt(abbreviation_crazy.length);
			response = abbreviation_crazy[abbreviation_num];
			break;
		case "ㅅㅅ":
			abbreviation_num = random.nextInt(abbreviation_sex.length);
			response = abbreviation_sex[abbreviation_num];
			break;
		case "ㅅㄱ":
			abbreviation_num = random.nextInt(abbreviation_effort.length);
			response = abbreviation_effort[abbreviation_num];
			break;
		case "ㅅㄱㅂ":
			abbreviation_num = random.nextInt(abbreviation_effort.length);
			response = abbreviation_effort[abbreviation_num];
			break;
		case "ㅅㅂ":
			abbreviation_num = random.nextInt(abbreviation_insult.length);
			response = abbreviation_insult[abbreviation_num];
			break;
		case "ㅇㅇ":
			abbreviation_num = random.nextInt(abbreviation_ok.length);
			response = abbreviation_ok[abbreviation_num];
			break;
		case "ㅇㄱㅂㅅㅌ":
			abbreviation_num = random.nextInt(abbreviation_bug.length);
			response = abbreviation_bug[abbreviation_num];
			break;
		case "ㅇㅈ":
			abbreviation_num = random.nextInt(abbreviation_accept.length);
			response = abbreviation_accept[abbreviation_num];
			break;
		case "ㅈㅈ":
			abbreviation_num = random.nextInt(abbreviation_gg.length);
			response = abbreviation_gg[abbreviation_num];
			break;
		case "ㅈㅅ":
			abbreviation_num = random.nextInt(abbreviation_sorry.length);
			response = abbreviation_sorry[abbreviation_num];
			break;
		case "ㅉㅉ":
			abbreviation_num = random.nextInt(abbreviation_suction.length);
			response = abbreviation_suction[abbreviation_num];
			break;
		case "ㅊㅊ":
			abbreviation_num = random.nextInt(abbreviation_celebrity.length);
			response = abbreviation_celebrity[abbreviation_num];
			break;
		case "ㅊㅋ":
			abbreviation_num = random.nextInt(abbreviation_celebrity.length);
			response = abbreviation_celebrity[abbreviation_num];
			break;
		case "ㅋㅋ":
			abbreviation_num = random.nextInt(abbreviation_laugh.length);
			response = abbreviation_laugh[abbreviation_num];
			break;
		case "ㅋㅌ":
			abbreviation_num = random.nextInt(abbreviation_katalk.length);
			response = abbreviation_katalk[abbreviation_num];
			break;
		case "ㅌㅌ":
			abbreviation_num = random.nextInt(abbreviation_runaway.length);
			response = abbreviation_runaway[abbreviation_num];
			break;
		case "ㅎㅎ":
			abbreviation_num = random.nextInt(abbreviation_laugh.length);
			response = abbreviation_laugh[abbreviation_num];
			break;
		case "ㅗ":
			abbreviation_num = random.nextInt(abbreviation_fuck.length);
			response = abbreviation_fuck[abbreviation_num];
			break;
		case "ㅜ":
			abbreviation_num = random.nextInt(abbreviation_sad.length);
			response = abbreviation_sad[abbreviation_num];
			break;
		case "ㅜㅜ":
			abbreviation_num = random.nextInt(abbreviation_sad.length);
			response = abbreviation_sad[abbreviation_num];
			break;
		case "ㅠ":
			abbreviation_num = random.nextInt(abbreviation_sad.length);
			response = abbreviation_sad[abbreviation_num];
			break;
		case "ㅠㅠ":
			abbreviation_num = random.nextInt(abbreviation_sad.length);
			response = abbreviation_sad[abbreviation_num];
			break;
		case "ㅗㅜㅑ":
			abbreviation_num = random.nextInt(abbreviation_sexy.length);
			response = abbreviation_sexy[abbreviation_num];
			break;
		default:
			response = "무슨 말인지 잘 모르겠네요.... 저에게 알려주실수 있으신가요?";
			break;
		}
		return response;
	}

	
	@Override
	public String questionResponse(Seq<KoreanToken> stemmed) throws Exception 
	{
		String response = null;
		
		List<KoreanTokenJava> morphemes = TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(stemmed);
		for(int i = 0; i < morphemes.size(); i++)
		{
			KoreanTokenJava morpheme = morphemes.get(i);
			
			KoreanPosJava target = morpheme.getPos();
			String target_word = morpheme.getText();
			
			if(target.toString().equals("Noun") && target_word.equals("이름"))
			{
				response = "내 이름은 사만다야";
				
				return response;
			}else
			{
				response = "지금 너와 대화하고 있잖아";
			}
		}
		return response;
	}

	//학습
	@Override
	public MorphismDTO MorphemeAnalysis(Seq<KoreanTokenizer.KoreanToken> tokens, String kakaomessage) throws Exception 
	{
		String keyMaker = null;
		
		MorphismDTO morphdto = new MorphismDTO();
		
		if(kakaomessage.length() > 1)	//외자를 처리하기 위함
		{
			List<KoreanTokenJava> morphemes = TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(tokens);
			
			//형태소가 7개 이상인 경우 처리가 힘들어 db에 저장하지 않음
			if(morphemes.size() > 8)
			{
				morphdto.setType(0);
				
				return morphdto;
			}
			
			morphdto.setType(1);
			
			for(int i = 0; i < morphemes.size() - 1; i++)
			{
				KoreanTokenJava target_morpheme = morphemes.get(i);
				KoreanTokenJava next_morpheme = morphemes.get(i + 1);
				
				KoreanPosJava target_pos = target_morpheme.getPos();
				String target_word = target_morpheme.getText();
				
				if(keyMaker == null && target_word != "\n" && target_word != " " && target_word != "")
				{
					keyMaker = target_word;
					
					morphdto.setKeyMaker(keyMaker);
				}
				
				KoreanPosJava next_pos = next_morpheme.getPos();
				String next_word = next_morpheme.getText();
				
				//전 단어와 후 단어가 같으면 무한 루프에 빠질 위험이 있기 때문에 처리
				if(target_word.equals(next_word))
				{
					continue;
				}
				
				MorphismVO vo = new MorphismVO();
				vo.setTarget(target_word);
				vo.setNext(next_word);
				
				//데이터베이스에 없는 경우
				if(morphDAO.check(vo) == null)
				{
					System.out.println("데이터베이스 삽입");
					morphDAO.create(vo);
				}else	//데이터베이스에 존재하는 경우
				{
					System.out.println(target_word + " : " + next_word);	
					morphDAO.increase_frequent(vo);
				}
			}
		}else		//외자
		{
			List<KoreanTokenJava> morphemes = TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(tokens);
			KoreanTokenJava morpheme = morphemes.get(0);
			keyMaker = morpheme.getText();
			
			morphdto.setType(2);
			morphdto.setKeyMaker(keyMaker);
			
			return morphdto;
		}
		return morphdto;
	}

	//문장 생성
	@Override
	public String makeSentence(MorphismDTO morphDTO) throws Exception 
	{
		Random random = new Random();
		int sentence_seed;
		
		System.out.println("key : " + morphDTO.getKeyMaker());
		
		StringBuilder complete_sentence = null;
		
		complete_sentence = new StringBuilder();
				
		String key = morphDTO.getKeyMaker();
		/*
		 * 인칭 변환
		 * 내 <-> 니, 내가 <-> 니가, 나 <-> 너, 넌 <-> 난 
		 */
		
		switch(key)
		{
		case "내":
			key = "니";
			break;
		case "니":
			key = "내";
			break;
		case "내가":
			key = "니가";
			break;
		case "니가":
			key = "내가";
			break;
		case "나":
			key = "너";
			break;
		case "너":
			key = "나";
			break;
		case "난":
			key = "넌";
			break;
		case "넌":
			key = "난";
			break;
		default:
			break;
		}
		
		complete_sentence.append(key);
		
		while(true)
		{
			List<MorphismVO> list = morphDAO.getWords(key);
			if(list.size() == 0)
			{
				return "아직 배우질 않아 잘 모르겠어요";
			}
			
			sentence_seed = random.nextInt(list.size());
			
			MorphismVO vo = list.get(sentence_seed);
			
			String next = vo.getNext();
			if(next.contains("."))
			{
				break;
			}
			complete_sentence.append(next);
			
			key = next;
		}
		return complete_sentence.toString();
	}

	@Override
	public List<MorphismVO> listAll() throws Exception 
	{
		return morphDAO.listAll();
	}

	@Override
	public List<MorphismVO> listPage(int page) throws Exception 
	{
		return morphDAO.listPage(page);
	}

	@Override
	public int listCount() throws Exception 
	{
		return morphDAO.countPaging();
	}
}
