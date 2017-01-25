package lee.sm.prdtmng.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lee.LeeCodeConstants;
import lee.comm.attach.service.AttachService;
import lee.comm.attach.service.AttachVO;
import lee.comm.func.service.FuncService;
import lee.login.web.LoginManager;
import lee.sm.prdtmng.service.PrdtmngSearchVO;
import lee.sm.prdtmng.service.PrdtmngService;
import lee.sm.prdtmng.service.PrdtmngVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PrdtmngController {
	
	@Resource(name="prdtmngService")
	private PrdtmngService prdtmngService;
	
	@Resource(name="funcService")
	private FuncService funcService;
	
	@Resource(name="attachService")
	private AttachService attachService;
	
	
	@RequestMapping(value = "/sm/prdtmng/prdtmngList.do")
	public String requestList(HttpServletRequest req, @ModelAttribute("prdtmngSearchVO") PrdtmngSearchVO prdtmngSearchVO, ModelMap modelMap) throws Exception {

		List prdtmngList = prdtmngService.prdtmngList(prdtmngSearchVO);

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

		if(prdtmngList!=null){
			count = prdtmngList.size();
		}
		
		int pageGroupCount = count / (pageSize * pageGroupSize)
				+ (count % (pageSize * pageGroupSize) == 0 ? 0 : 1);

		int numPageGroup = (int) Math.ceil((double) currentPage / pageGroupSize);

		ArrayList articleList = new ArrayList();

		if (count > 0) {
			if (endRow > count)
				endRow = count;
			for (int i = startRow; i < endRow; i++) {
				articleList.add(prdtmngList.get(i));
			}

		} else {
			articleList = null;
		}

		modelMap.put("listCount", listCount);
		modelMap.put("prdtList", prdtmngList);
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
		

		return "/sm/prdtmng/prdtmngList";
	}
	
	@RequestMapping(value = "/sm/prdtmng/prdtmngAdd.do")
	public String prdtmngAdd(HttpServletRequest req, ModelMap modelMap) {
		Map params = new HashMap();
		
		PrdtmngVO prdtmngVO = new PrdtmngVO();
		
		modelMap.put("lgcateList", funcService.lgcateList());  //대분류 리스트
		modelMap.put("cateList", funcService.cateList(params));  //중분류 리스트

		modelMap.put("prdtmngVO", prdtmngVO);
		
		return "/sm/prdtmng/prdtmngAdd";
	}
	
	@RequestMapping(value = "/sm/prdtmng/prdtmngAdd.do", method = RequestMethod.POST)
	public String prdtmngAddPro(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("prdtmngVO") PrdtmngVO prdtmngVO) throws Exception {
		
		long result=0;
		
		long atch_file_sno = attachService.attachAdd(req, 0, LeeCodeConstants.PRODUCT, 0, "N");
		
		prdtmngVO.setAtch_file_sno(atch_file_sno);
		
		prdtmngVO.setReg_usr_no(LoginManager.userNo(req));
		result = prdtmngService.prdtmngAdd(prdtmngVO);
		
		return "redirect:/sm/prdtmng/prdtmngList.do";
	}
	
	@RequestMapping(value = "/sm/prdtmng/prdtmngView.do")
	public String prdtmngView(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("prdtmngSearchVO") PrdtmngSearchVO prdtmngSearchVO) throws Exception {
		
		PrdtmngVO prdtmngVO = prdtmngService.prdtmngView(prdtmngSearchVO.getPrdt_sno());
		
		//첨부파일
		List<AttachVO> atchFileList = attachService.attachList(prdtmngVO.getAtch_file_sno(),"N");
		if(atchFileList!=null && atchFileList.size()>0) modelMap.put("atchFileList", atchFileList);
		
		modelMap.put("prdtmngVO", prdtmngVO);

		
		return "/sm/prdtmng/prdtmngView";
	}
	
	@RequestMapping(value = "/sm/prdtmng/prdtmngMod.do")
	public String prdtmngMod(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("prdtmngSearchVO") PrdtmngSearchVO prdtmngSearchVO) throws Exception{
		Map params = new HashMap();
		
		PrdtmngVO prdtmngVO = prdtmngService.prdtmngView(prdtmngSearchVO.getPrdt_sno());
		
		params.put("lg_cate_sno", prdtmngVO.getLg_cate_sno());

		modelMap.put("lgcateList", funcService.lgcateList());  //대분류 리스트
		modelMap.put("cateList", funcService.cateList(params));  //중분류 리스트
		
		//첨부파일
		List<AttachVO> atchFileList = attachService.attachList(prdtmngVO.getAtch_file_sno(),"N");
		if(atchFileList!=null && atchFileList.size()>0) modelMap.put("atchFileList", atchFileList);
		
		modelMap.put("prdtmngVO", prdtmngVO);

		
		return "/sm/prdtmng/prdtmngMod";
	}
	
	@RequestMapping(value = "/sm/prdtmng/prdtmngMod.do", method = RequestMethod.POST)
	public String prdtmngModPro(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("prdtmngVO") PrdtmngVO prdtmngVO) throws Exception{
		
		long result=0;
		result = prdtmngService.prdtmngMod(prdtmngVO);
		
		return "redirect:/sm/prdtmng/prdtmngList.do";
	}

}
