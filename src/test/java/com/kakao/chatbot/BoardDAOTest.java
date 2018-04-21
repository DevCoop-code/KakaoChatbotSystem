package com.kakao.chatbot;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest 
{
	/*
	@Inject
	private IngredientDAO ingre_dao;
	@Inject
	private RecipeBasicDAO recipebasic_dao;
	@Inject
	private RecipeProcessDAO recipeprocess_dao;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	*/
	/*
	 * 레시피+재료정보 테이블에 데이터 삽입
	@Test
	public void testCreate() throws Exception
	{
		InputStreamReader inputstream = new InputStreamReader(new FileInputStream(new File("/Users/hangyojeong/Desktop/레시피/레시피+재료정보_20180216.csv")), "EUC-KR");
		BufferedReader br = new BufferedReader(inputstream);
		String csv_line = null;
		//CSV 파일의 첫번째 줄을 버리기 위함
		csv_line = br.readLine();
		System.out.println("레시피+재료정보 = "+csv_line);
		
		while((csv_line = br.readLine()) != null)
		{
			//System.out.println(csv_line);
			StringTokenizer csv_token = new StringTokenizer(csv_line,",");
			IngredientVO vo = new IngredientVO();
			for(int i=1; csv_token.hasMoreElements(); i++)
			{
				String data = csv_token.nextToken();
				data = data.trim();
				byte[] euckrStrbyte = data.getBytes("utf-8");
				String utf8data = new String(euckrStrbyte, "utf-8");
				//레시피 코드
				if(i == 1)
				{
					utf8data = utf8data.replaceAll("\"", "");
					//System.out.println(Integer.parseInt(utf8data));
					vo.setRecipecode(Integer.parseInt(utf8data));
				}
				//재료명
				else if(i == 3)
				{
					utf8data = utf8data.replaceAll("\"", "");
					//System.out.println(utf8data);
					vo.setIngredientname(utf8data);
				}
				//재료타입코드
				else if(i == 5)
				{
					utf8data = utf8data.replaceAll("\"", "");
					//System.out.println(Integer.parseInt(utf8data));
					vo.setIngredienttypecode(Integer.parseInt(utf8data));
				}
				//System.out.print("문자:"+i+"="+csv_token.nextToken());
			}
			if(vo != null)
				ingre_dao.create(vo);
		}
	}
	*/
	/*
	@Test
	public void testCreate() throws Exception
	{
		JSONParser parser = new JSONParser();
		
		try
		{	
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("/Users/hangyojeong/Desktop/레시피/레시피+과정정보_20180216230446.json"));;
			
			JSONArray data_array = (JSONArray)jsonObject.get("data");
			
			RecipeProcessVO vo = null;
			for(int i=0; i<data_array.size(); i++)
			{
				vo = new RecipeProcessVO();
				JSONObject recipedata = (JSONObject)data_array.get(i);
				//System.out.println(recipedata.toString());
				
				//레시피 코드
				Object recipe_id = recipedata.get("RECIPE_ID");
				int recipecode = Integer.parseInt(recipe_id.toString());
				//System.out.println(recipecode);
				vo.setRecipecode(recipecode);
				
				//요리설명 순서
				int cooking_no = Integer.parseInt(((Object)recipedata.get("COOKING_NO")).toString());
				//System.out.println(cooking_no);
				vo.setRecipeorder(cooking_no);;
				
				//유형설명
				String cooking_dc = (String)recipedata.get("COOKING_DC");
				//System.out.println(cooking_dc);
				vo.setRecipedes(cooking_dc);;
				
				//과정 이미지
				String stre_step_image_url = (String)recipedata.get("STRE_STEP_IMAGE_URL");
				//System.out.println(stre_step_image_url);
				vo.setRecipeimageurl(stre_step_image_url);
				
				//팁
				String step_tip = (String)recipedata.get("STEP_TIP");
				//System.out.println(step_tip);
				vo.setTip(step_tip);
				
				recipeprocess_dao.create(vo);
			}
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	*/
	/*
	@Test
	public void testRead() throws Exception
	{
		logger.info(dao.read(1).toString());
	}
	*/
	
	/*
	@Test
	public void testUpdate() throws Exception
	{
		ComicBoardVO vo = new ComicBoardVO();
		vo.setBno(1);
		vo.setTitle("오버로드 2기");
		vo.setProduction("매드하우스");
		vo.setGrade("19세 이상");
		vo.setSeason("2018년 1분기");
		vo.setDescription("나자릭 밖으로 나가보니 본적도 없는 이세계가 펼쳐져 있었다. 모몬가는 예전 동료들을 찾기 위해 길드의 이름이었던 아인즈 울 고운이라는 이름을 사용하고, 이세계에 그 이름을 널리 퍼뜨리기로 결심한다. 그리고 절대적인 충성을 맹세한 부하들과 함께 새로운 땅을 향해 진격한다!");
		vo.setWriter("cooddy");
		
		dao.update(vo);
	}
	*/
	
	/*
	@Test
	public void testDelete() throws Exception
	{
		dao.delete(1);
	}
	*/
}
