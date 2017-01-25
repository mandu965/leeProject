package lee.intro.web;

import javax.servlet.http.HttpServletRequest;

import lee.test.spring.bean.PrototypeBean;
import lee.test.spring.bean.RequestBean;
import lee.test.spring.bean.SessionBean;
import lee.test.spring.bean.SingletonBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IntroController {
	
	/*@Autowired
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
	private PrototypeBean prototypeBean02;*/

	@RequestMapping(value = "/intro/aM01.do")
	public String aM01(HttpServletRequest req, ModelMap modelMap) {
		/*System.out.println(singletonBean01);
		System.out.println(singletonBean02);
		
		System.out.println(prototypeBean01);
		System.out.println(prototypeBean02);
		
		System.out.println(requestBean01);
		System.out.println(requestBean02);
		
		System.out.println(sessionBean01);
		System.out.println(sessionBean02);*/
		return "/intro/aM01";
	}	
	
	@RequestMapping(value = "/intro/aM02.do")
	public String aM02(HttpServletRequest req, ModelMap modelMap) {
	
		return "/intro/aM02";
	}	
	
	@RequestMapping(value = "/intro/aM03.do")
	public String aM03(HttpServletRequest req, ModelMap modelMap) {
	
		return "/intro/aM03";
	}	
	

}
