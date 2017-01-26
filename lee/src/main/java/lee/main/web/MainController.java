package lee.main.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lee.board.service.BoardService;
import lee.board.service.BoardVO;
import lee.main.service.MainService;
import lee.test.spring.bean.PrototypeBean;
import lee.test.spring.bean.RequestBean;
import lee.test.spring.bean.SessionBean;
import lee.test.spring.bean.SingletonBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

/** 메인 */
@Controller
public class MainController {
	
	@Resource(name = "mainService")
	private MainService mainService;
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	/*@Resource(name = "lifeBean")
	private LifeBean lifeBean;*/

	@Autowired
	private RequestBean requestBean01;
	
	@Autowired
	private RequestBean requestBean02;
	
	@Autowired
	private SessionBean sessionBean01;
	
	@Autowired
	private SessionBean sessionBean02;
	
	@Autowired
	private SingletonBean singletonBean01;
	
	@Autowired
	private SingletonBean singletonBean02;
	
	@Autowired
	private PrototypeBean prototypeBean01;
	
	@Autowired
	private PrototypeBean prototypeBean02;
	
	public void deleteFolder(String parentPath) {
	    File file = new File(parentPath);
	    String[] fnameList = file.list();
	    int fCnt = fnameList.length;
	    String childPath = "";
	    
	    for(int i = 0; i < fCnt; i++) {
	    	System.out.println(parentPath + "@@@@@@@@@" + fnameList[i]);
	      childPath = parentPath+"/"+fnameList[i];
	      File f = new File(childPath);
	      if( ! f.isDirectory()) {
	        f.delete();   //파일이면 바로 삭제
	      }
	      else {
	        deleteFolder(childPath);
	      }
	    }
	    
	    File f = new File(parentPath);
	    f.delete();   //폴더는 맨 나중에 삭제
	    
	  }
	
	@RequestMapping("/main/main.do")
	public String main(HttpServletRequest req, ModelMap modelMap, SessionStatus status) throws Exception {
		
		System.out.println(singletonBean01);
		System.out.println(singletonBean02);
		
		System.out.println(prototypeBean01);
		System.out.println(prototypeBean02);
		
		System.out.println(requestBean01);
		System.out.println(requestBean02);
		
		System.out.println(sessionBean01);
		System.out.println(sessionBean02);
		
		
		
		//네이버 로그인시 받는 url
		//http://1.245.161.247:5080/lee/main/main.do?code=bhVIbNIxJDgXMww1&state=gp9ge0ql40fa2ak1lncf50ghkq
		//state = gp9ge0ql40fa2ak1lncf50ghkq
		/*
		// 콜백 응답에서 state 파라미터의 값을 가져옴
		String state = request.queryParams(“state”);

		// 세션 또는 별도의 저장 공간에서 상태 토큰을 가져옴
		String storedState = request.session().attribute(“state”);

		if( !state.euals( storedState ) ) {
		    return RESPONSE_UNAUTHORIZED; //401 unauthorized
		} else {
		    Return RESPONSE_SUCCESS; //200 success
		}*/
		
		
		
		//deleteFolder("C:/Users/leejh/Desktop/ttt"); 파일삭제 테스트
		
		
		//ApplicationContext context = new ClassPathXmlApplicationContext("spring/beanTest-context.xml");
		//ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/config/spring/servlet-context.xml");
		
		/*BeanTest beanTest = (BeanTest)context.getBean("beanTest");
		BeanTest beanTest2 = (BeanTest)context.getBean("beanTest");
		BeanTest beanTest3 = (BeanTest)context.getBean("beanTest");
		
		System.out.println("############beanTest" + beanTest);
		System.out.println("############beanTest2" + beanTest2);
		System.out.println("############beanTest3" + beanTest3);*/
		
		//ApplicationContext context = new ClassPathXmlApplicationContext("spring/beanTest-context.xml");
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring/beanTest-context.xml");


		//LifeBean lifeBean = (LifeBean)context.getBean("lifeBean");
		//lifeBean.lifeMethod();
		
		//context.close();
		
		
		  
		//공지사항 목록
			ArrayList<BoardVO> list = new ArrayList<BoardVO>();
			ArrayList<BoardVO> mainNoticeList = new ArrayList<BoardVO>();
			
			list = (ArrayList<BoardVO>) mainService.mainNoticeList();
			
			Calendar cal = Calendar.getInstance();
			String today = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
			
			int popup_stt_dt;
			int popup_end_dt;
			int popup_today;

			for(int i=0; i< list.size(); i++){
				popup_stt_dt = Integer.parseInt(list.get(i).getPopup_stt_dt());
				popup_end_dt = Integer.parseInt(list.get(i).getPopup_end_dt());
				popup_today = Integer.parseInt(today);
				if(popup_stt_dt <= popup_today && popup_today <=popup_end_dt){
					mainNoticeList.add(list.get(i));
				}
			}
					
			modelMap.put("mainNoticeList",mainNoticeList);
			
			return "/main/main";
	}
				
	//공지사항 팝업창
	@RequestMapping("/comm/pop/mainNotice.do")
	public String mainNotice(HttpServletRequest req, ModelMap modelMap) throws Exception {
		String blt_rsrc_sno_st;
		
		blt_rsrc_sno_st = req.getParameter("blt_rsrc_sno");
		
		long blt_rsrc_sno = Integer.parseInt(blt_rsrc_sno_st);
		
		BoardVO boardvo = new BoardVO();
		
		boardvo = boardService.boardView(blt_rsrc_sno);
		
		modelMap.put("boardvo", boardvo);
		
	
		return "/comm/pop/mainNotice";
	}
	
	
	

}


