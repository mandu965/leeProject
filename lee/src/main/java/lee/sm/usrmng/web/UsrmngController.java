package lee.sm.usrmng.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lee.comm.excel.PrintExcel;
import lee.library.RequestUtil;
import lee.login.service.LoginService;
import lee.sm.usrmng.service.UsrmngSearchVO;
import lee.sm.usrmng.service.UsrmngService;
import lee.sm.usrmng.service.UsrmngVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/*@Controller 이 어노테이션을 통해 컨트롤러로 정의한다.
@Autowired
property injection을 위한 어노테이션, 등록된 bean을 찾아서 맵핑해준다.
@Resource는 id, name으로 찾지 못하면 type으로 찾는다. (기본적으로 id로 DI한다)
@Autowired도 동일한 타입의 bean이 여러개일 경우 id, name을 이용하여 DI한다(기본적으로 Type으로 DI 한다.)
[동일한 타입이 있을 경우 @Qualifier를 사용해서 구분]

*
*/
@Controller
public class UsrmngController {

	@Resource(name = "usrmngService")
	private UsrmngService usrmngService;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name="printExcel")
	private PrintExcel printExcel;

	private static final Logger log = LoggerFactory.getLogger(UsrmngController.class);

	@RequestMapping(value = "/sm/usrmng/usrmngList.do")
	public String requestList(HttpServletRequest req, @ModelAttribute("usrmngSearchVO") UsrmngSearchVO usrmngSearchVO, ModelMap modelMap) throws Exception {
		log.info("............................");
		List usrmngList = usrmngService.usrmngList(usrmngSearchVO);

		///////paging : S//////////////////////////////
		String pageNum = null; 
		String listCount = null;
		pageNum = req.getParameter("pageNum");
		listCount = req.getParameter("listCount");
		
		if (pageNum == null) {
			pageNum = "1";
		}
		
		int pageSize = 10;
		
		if(listCount != null){
			pageSize = Integer.parseInt(listCount);
		}

		final int pageGroupSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize;
		int endRow = currentPage * pageSize;
		int count = 0;

		count = usrmngList.size();
		
		int pageGroupCount = count / (pageSize * pageGroupSize)
				+ (count % (pageSize * pageGroupSize) == 0 ? 0 : 1);

		int numPageGroup = (int) Math.ceil((double) currentPage / pageGroupSize);

		ArrayList articleList = new ArrayList();

		if (count > 0) {
			if (endRow > count)
				endRow = count;
			for (int i = startRow; i < endRow; i++) {
				articleList.add(usrmngList.get(i));
			}

		} else {
			articleList = null;
		}

		modelMap.put("listCount", listCount);
		modelMap.put("usrList", usrmngList);
		modelMap.put("currentPage", Integer.parseInt(pageNum));
		modelMap.put("pageSize", pageSize);
		modelMap.put("count", count);
		modelMap.put("startRow", startRow);
		modelMap.put("endRow", endRow);
		modelMap.put("pageGroupSize", pageGroupSize);
		modelMap.put("numPageGroup", numPageGroup);
		modelMap.put("pageGroupCount", pageGroupCount);
		modelMap.put("articleList", articleList);
		///////paging : E//////////////////////////////
		

		return "/sm/usrmng/usrmngList";
	}
	
	@RequestMapping(value = "/sm/usrmng/usrmngAdd.do")
	public String usrmngAdd(HttpServletRequest req) {

		
		return "/sm/usrmng/usrmngAdd";
	}
	
	@RequestMapping(value = "/sm/usrmng/usrmngAdd.do", method = RequestMethod.POST)
	public String usrAdd(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("usrmngVO") UsrmngVO usrmngVO) {
		//@ModelAttribute에서 속성으로 지정한 값(클래스)이 .jsp와 자유롭게 값(변수)을 주고받는다
		
		long a=0;
		
		a = usrmngService.usrmngAdd(usrmngVO);
		
		if(a>0) System.out.println("성공");
		
		return "/sm/usrmng/usrmngList";
	}
	
	
	
	/*@RequestMapping(value="/ajax/usr/usrMod.do",method=RequestMethod.GET) //dialog
	public String usrmngModShowForm(HttpServletRequest req,@RequestParam("usr_no")long usr_no,ModelMap modelMap)throws Exception{

		System.out.println("###");
		return "usr/usrMod";
	}*/
	
	
	
	@RequestMapping(value="/ajax/sm/usrmng/usrmngMod.do",method=RequestMethod.GET)
	public String usrmngModShowForm(HttpServletRequest req,@RequestParam("usr_no")long usr_no,ModelMap modelMap)throws Exception{
		UsrmngVO usrmngVO = usrmngService.usrmngView(usr_no);
		
		modelMap.put("usrmngVO", usrmngVO);
		
		return "/sm/usrmng/usrmngMod";
	}
	
	@RequestMapping(value = "/ajax/sm/usrmng/usrmngMod.do", method = RequestMethod.POST)
	public String usrmngModPro(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("usrmngVO") UsrmngVO usrmngVO) {
		
		//int result
		usrmngService.usrmngMod(usrmngVO);
		
		return "redirect:/sm/usrmng/usrmngList.do";
	}
	
	@RequestMapping(value = "/sm/usrmng/usrmngDel.do")
	public String usrDel(HttpServletRequest req) {
		
		return "/sm/usrmng/usrmngDel";
	}
	@RequestMapping(value = "/sm/usrmng/usrmngDelPro.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String usrDelPro(HttpServletRequest req, ModelMap modelMap) {

		String delID = "";
		String delPWD = "";
		String webView="";
		
		Map params  = new HashMap();
		
		delID = req.getParameter("delID");
		delPWD = req.getParameter("delPWD");
		
		ArrayList idList = new ArrayList();
		ArrayList pwdList = new ArrayList();
			
		
        idList = (ArrayList) loginService.idCheck(delID);
		
		
		if(idList.size()!=0){
			System.out.println("id 체크 성공 ");
			pwdList = (ArrayList) loginService.pwdCheck(delPWD);
			if(pwdList.size()!=0){
				System.out.println("pwd 성공");
				//삭제 로직
				try {
					params.put("usr_id", delID);
					params.put("usr_pw", delPWD);
					usrmngService.usrmngDel(params);
					System.out.println("삭제로직 통과!!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				webView = "main/main";
			}else{
				System.out.println("pwd 실패");
				webView = "/sm/usrmng/usrDel";
			}
		}
		else{
			System.out.println("id 실패");
			webView = "/sm/usrmng/usrDel";
		}
		 
		return webView;
	}
	
	/** 사용자 엑셀*/
	@RequestMapping("/sm/usrmng/userXlsDwon.do")
	public ModelAndView mngXlsDown(HttpServletRequest req, ModelMap modelMap) throws Exception {
		
		Map params = new HashMap();
		
		List userExcelList =  usrmngService.userExcelList(params);
		params.put("userExcelList", userExcelList);
		
		return new ModelAndView(printExcel, params);
	}

}
