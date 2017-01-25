package lee.login.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lee.LeeConstants;
import lee.library.SessionUtil;
import lee.login.service.LoginService;
import lee.login.service.LoginSession;
import lee.sm.usrmng.service.UsrmngVO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "loginService")
	private LoginService loginService;

	@RequestMapping(value = "/login/login.do")
	// req 굳이 필요한가??modelMap으로 주고받는데!!! req지우고 테스트 해보자
	public String login() throws Exception{
		
		System.out.println("###########################################로그인화면 이동");
	
		return "/login/login";
	}
	
	@RequestMapping(value = "/login/loginPro.do", method = RequestMethod.POST)
	public String loginPro(HttpServletRequest req, ModelMap modelMap, HttpSession session) throws Exception {
		String id="";
		String pw="";
		String webView = "";
		long usr_no;

		UsrmngVO login_info = new UsrmngVO();
		
		id = req.getParameter("loginID");
		pw = req.getParameter("loginPWD");
		
		//usr_no= Integer.toString((int)login_info.get(0).getUsr_no());
		//logger.info(instan);
		
		
		login_info = (UsrmngVO)loginService.loginProc(id);//사용자 정보 얻어오기 
		
		if(id.equals(login_info.getUsr_id())){
			logger.info("id 성공");
			if(pw.equals(login_info.getUsr_pw())){
				logger.info("pw 성공");
				
				//세션생성 
				LoginSession loginSession = login(req, login_info,  session);

				webView = "redirect:/main/main.do";
				
			}else{
				logger.info("pw 실패");
				webView = "login/login";
			}
			
		}else{
			logger.info("id 실패");
			webView = "login/login";
		}
		 
		return webView;
	}
	
	
	/** 세션생성 */
	public LoginSession login(HttpServletRequest req, UsrmngVO login_info, HttpSession session) throws Exception {
		
		// 세션 발행
	    LoginSession ls = new LoginSession();
	   
	    if (login_info == null) {
			return null;
		}
	    
	    // 이전 세션 삭제
	 	//session.invalidate(); 세션이 있지 않은데 삭제하므로 에러 유발
	 	
		//세션생성		
	    ls.setUsr_auth_cd(login_info.getUsr_auth_cd());
	    ls.setUsr_hp(login_info.getUsr_hp());
	    ls.setReg_date(login_info.getReg_date());
	    ls.setUsr_addr(login_info.getUsr_addr());
	    ls.setUsr_birth(login_info.getUsr_birth());
	    ls.setUsr_id(login_info.getUsr_id());
	    ls.setUsr_nm(login_info.getUsr_nm());
	    ls.setUsr_no(login_info.getUsr_no());
	    ls.setUsr_point(login_info.getUsr_point());
	    ls.setUsr_pw(login_info.getUsr_pw());
	    ls.setUsr_sex(login_info.getUsr_sex());
	    
	    
	  /*  session.setAttribute("loginSession_admin_yn", login_info.getAdmin_yn());
	    session.setAttribute("loginSession_reg_date", login_info.getReg_date());
	    session.setAttribute("loginSession_addr", login_info.getUsr_addr());
	    session.setAttribute("loginSession_birth", login_info.getUsr_birth());
	    session.setAttribute("loginSession_id", login_info.getUsr_id());
	    session.setAttribute("loginSession_nm", login_info.getUsr_nm());
	    session.setAttribute("loginSession_no", login_info.getUsr_no());
	    session.setAttribute("loginSession_point", login_info.getUsr_point());
	    session.setAttribute("loginSession_pw", login_info.getUsr_pw());
	    session.setAttribute("loginSession_sex", login_info.getUsr_sex());
	    session.setAttribute("loginSession_phoneNumber", login_info.getUsr_hp());*/
	    
	    // 세션 저장
	    SessionUtil.setAttribute(req, LeeConstants.SESSION_KEY_LOGIN, ls);
	    
		return ls;
		
	}
	
	@RequestMapping(value = "/login/logout.do")
	public String logOut(HttpServletRequest req, ModelMap modelMap, HttpSession session) {
		
		session.invalidate();
	
		return "redirect:/main/main.do";
	}	
	
	@RequestMapping(value = "/login/usrAdd.do")
	// req 굳이 필요한가??modelMap으로 주고받는데!!! req지우고 테스트 해보자
	public String usrAdd() throws Exception{
		
		System.out.println("###########################################회원가입화면 이동");
	
		return "/login/usrAdd";
	}
	
}
