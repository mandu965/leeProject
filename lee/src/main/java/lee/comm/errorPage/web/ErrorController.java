package lee.comm.errorPage.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	@RequestMapping(value="/error.do")
	public String error(Model model) throws Exception{
		return "/comm/error/error";
	}

}