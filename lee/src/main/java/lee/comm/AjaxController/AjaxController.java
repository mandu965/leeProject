package lee.comm.AjaxController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lee.comm.func.service.FuncService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class AjaxController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name="funcService")
	private FuncService funcService;
	
	
	/** 주소 Layer*/
	@RequestMapping("/ajax/comm/zipCodeLayer.do")
	public String zipCodeLayer(HttpServletRequest req, ModelMap modelMap) throws Exception {
		
		logger.info("########Layer########");
		
		return "/comm/ajax/zipCodeLayer";
	}
	
	/** 주소 List*/
	@RequestMapping("/ajax/comm/zipCodeList.do")
	public String zipCodeList(HttpServletRequest req, ModelMap modelMap) throws Exception {
		
		String dong = req.getParameter("dong");
		
		Map params = new HashMap();
		
		params.put("dong", dong);
		
		List zipCodelist = funcService.zipCodeList(params);		
		
		modelMap.put("zipCodelist", zipCodelist);
		
		logger.info("#########List#######" + dong+"**" + zipCodelist.size());
		
		return "/comm/ajax/zipCodeList";
	}
	
	/** 결제방법 : cash*/
	@RequestMapping("/ajax/comm/cashLayer.do")
	public String cashLayer(HttpServletRequest req, ModelMap modelMap) throws Exception {
		
		
		
		return "/comm/ajax/cashLayer";
	}
	
	/** 결제방법 : mobile*/
	@RequestMapping("/ajax/comm/mobileLayer.do")
	public String mobileLayer(HttpServletRequest req, ModelMap modelMap) throws Exception {
		
		
		
		return "/comm/ajax/mobileLayer";
	}
	
	/** 결제방법 : card*/
	@RequestMapping("/ajax/comm/cardLayer.do")
	public String cardLayer(HttpServletRequest req, ModelMap modelMap) throws Exception {
		
		
		
		return "/comm/ajax/cardLayer";
	}
	

}
