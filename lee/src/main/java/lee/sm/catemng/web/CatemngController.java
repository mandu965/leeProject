package lee.sm.catemng.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lee.comm.func.service.FuncService;
import lee.library.RequestUtil;
import lee.sm.catemng.service.CatemngService;
import lee.sm.catemng.service.CatemngVO;
import lee.sm.catemng.service.LgcatemngVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CatemngController {
	
	@Resource(name = "catemngService")
	CatemngService catemngService;
	
	@Resource(name="funcService")
	private FuncService funcService;


	
	@RequestMapping(value = "/sm/**/catemngList.do")
	public String catemngAllList(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("CatemngVO") CatemngVO catemngVO) throws Exception {
		Map<String, Long> params = new HashMap<String, Long>();
		modelMap.put("lgcateList", funcService.lgcateList());
		modelMap.put("cateList", funcService.cateList(params));
		
		return "/sm/catemng/catemngList";
	}
	
	/** 상품카테고리 목록(Ajax) */
	@RequestMapping("/ajax/sm/catemng/catemngList.do")
	public String catemngList(HttpServletRequest req, ModelMap modelMap) throws Exception {
		Map<String, Long> params = new HashMap<String, Long>();
		
		long lg_cate_sno = RequestUtil.getLong(req, "lg_cate_sno");
		params.put("lg_cate_sno", lg_cate_sno);
		modelMap.put("cateList", funcService.cateList(params));
		
		return "/sm/catemng/catemngList02";
	}
	
	/** 분류카테고리 조회(Ajax) */
	@RequestMapping("/ajax/sm/lgcatemng/lgcatemngMod.do")
	public String lgcatemngView(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("lgcatemngVO")LgcatemngVO lgcatemngVO) throws Exception {

		
		long lg_cate_sno = RequestUtil.getLong(req, "lg_cate_sno");
		
		lgcatemngVO = catemngService.lgcatemngView(lg_cate_sno);
		modelMap.put("lgcatemngVO", lgcatemngVO);
		
		return "/sm/catemng/lgcatemngAdd";
	}
	
	/** 상품카테고리 조회(Ajax) */
	@RequestMapping("/ajax/sm/catemng/catemngMod.do")
	public String catemngView(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("catemngVO")CatemngVO catemngVO) throws Exception {

		
		long cate_sno = RequestUtil.getLong(req, "cate_sno");
		
		catemngVO = catemngService.catemngView(cate_sno);
		modelMap.put("catemngVO", catemngVO);
		modelMap.put("lgcateList", funcService.lgcateList());
		
		return "/sm/catemng/catemngAdd";
	}
	
	
	
	
	
	
	@RequestMapping(value = "sm/**/catemngAdd.do")
	public String catemngAdd(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("CateVO") CatemngVO cateVO) {

		
		return "sm/catemng/catemngAdd";
	}
	@RequestMapping(value = "sm/**/catemngAdd.do", method= RequestMethod.POST)
	public String catemngAddPro(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("CatemngVO") CatemngVO catemngVO) {
	
		System.out.println("카테고리 등록");
		
		System.out.println("성별 : " + catemngVO.getCate_sex());
		System.out.println("카테고리명 : " + catemngVO.getCate_nm());
		
		catemngService.catemngAdd(catemngVO);
		
		return "sm/catemng/catemngList";
	}
	
	

}
