package lee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lee.login.service.LoginSession;
import lee.login.web.LoginManager;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;





public class DefaultInterceptor extends HandlerInterceptorAdapter {
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		System.out.println("DefaultInterceptor####################################preHandle");
		LoginSession loginSession = LoginManager.loginSession(req);
		String loginUrl = req.getContextPath()+"/login/login.do";
		String reqURI = req.getRequestURI();
		loginUrl = loginUrl.substring(0, loginUrl.lastIndexOf("/")+1);
		reqURI = reqURI.substring(0, reqURI.lastIndexOf("/")+1);
		
		if(loginSession == null){
			//System.out.println("#############loginUrl = " + loginUrl);
			res.sendRedirect(loginUrl+"login.do");
		}
		return true;
	}
	
	
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("DefaultInterceptor####################################postHandle");
	}

}

