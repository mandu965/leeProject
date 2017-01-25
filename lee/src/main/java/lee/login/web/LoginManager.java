package lee.login.web;

import javax.servlet.http.HttpServletRequest;

import lee.LeeConstants;
import lee.library.SessionUtil;
import lee.login.service.LoginSession;

public class LoginManager {
	
	/** loginSession */
	public static LoginSession loginSession(HttpServletRequest req) {
		Object obj = SessionUtil.getAttribute(req, LeeConstants.SESSION_KEY_LOGIN);
		return obj != null ? (LoginSession) obj : null;
	}
	
	/** 세션 사용자번호 */
	public static long userNo(HttpServletRequest req) {
		Object obj = SessionUtil.getAttribute(req, LeeConstants.SESSION_KEY_LOGIN);
		return obj == null ? 0 : ((LoginSession) obj).getUsr_no();
	}
	
	/** 세션 사용자명*/
	public static String userNm(HttpServletRequest req) {
		Object obj = SessionUtil.getAttribute(req, LeeConstants.SESSION_KEY_LOGIN);
		return obj == null ? "" : ((LoginSession) obj).getUsr_nm();
	}
	
	/** 세션 사용자 주소*/
	public static String userAddr(HttpServletRequest req) {
		Object obj = SessionUtil.getAttribute(req, LeeConstants.SESSION_KEY_LOGIN);
		return obj == null ? "" : ((LoginSession) obj).getUsr_addr();
	}
	
	/** 세션 사용자 연락처*/
	public static String userHp(HttpServletRequest req) {
		Object obj = SessionUtil.getAttribute(req, LeeConstants.SESSION_KEY_LOGIN);
		return obj == null ? "" : ((LoginSession) obj).getUsr_hp();
	}

}
