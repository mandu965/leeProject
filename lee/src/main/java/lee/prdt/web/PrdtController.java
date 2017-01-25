package lee.prdt.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lee.LeeCodeConstants;
import lee.library.RequestUtil;
import lee.login.web.LoginManager;
import lee.prdt.service.OrderVO;
import lee.prdt.service.PrdtSearchVO;
import lee.prdt.service.PrdtService;
import lee.prdt.service.PrdtVO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class PrdtController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name="prdtService")
	PrdtService prdtService;
	
	@RequestMapping(value = "/prdt/prdtList.do")
	public String prdtList(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("prdtSearchVO")PrdtSearchVO prdtSearchVO ) throws Exception {
		List prdtList = prdtService.prdtList(prdtSearchVO);

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

		count = prdtList.size();
		
		int pageGroupCount = count / (pageSize * pageGroupSize)
				+ (count % (pageSize * pageGroupSize) == 0 ? 0 : 1);

		int numPageGroup = (int) Math.ceil((double) currentPage / pageGroupSize);

		ArrayList articleList = new ArrayList();

		if (count > 0) {
			if (endRow > count)
				endRow = count;
			for (int i = startRow; i < endRow; i++) {
				articleList.add(prdtList.get(i));
			}

		} else {
			articleList = null;
		}

		modelMap.put("listCount", listCount);
		modelMap.put("prdtList", prdtList);
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
		
		return "/prdt/prdtList";
	}
	
	@RequestMapping(value = "/prdt/prdtView.do")
	public String prdtView(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("prdtSearchVO") PrdtSearchVO prdtSearchVO) throws Exception {
		
		PrdtVO prdtVO = prdtService.prdtView(prdtSearchVO.getPrdt_sno());
		
		//첨부파일
		/*List<AttachVO> atchFileList = attachService.attachList(prdtVO.getAtch_file_sno(),"N");
		if(atchFileList!=null && atchFileList.size()>0) modelMap.put("atchFileList", atchFileList);*/
		
		modelMap.put("prdtVO", prdtVO);

		
		return "/prdt/prdtView";
	}
	
	@RequestMapping(value ="/ajax/prdt/cartAdd.do", method=RequestMethod.POST)
	public ModelAndView cartAdd(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("prdtVO") PrdtVO prdtVO) throws Exception {
		
		Map<String, Boolean> params = new HashMap<String, Boolean>();
		
		long result = prdtService.cartAdd(req, prdtVO);
		if(result > 0){
			params.put("result", true);	
		}else{
			params.put("result", false);
		}

		return new ModelAndView("jsonView", params);
	}
	
	@RequestMapping(value = "/prdt/prdtOrderView.do" )
	public String orderAdd(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("prdtVO") PrdtVO prdtVO) throws Exception {

		long prdt_count = RequestUtil.getLong(req, "prdt_count");
		long total_price =  prdt_count*prdtVO.getPrdt_price();
		OrderVO orderVO = new OrderVO();

		modelMap.put("usr_no", LoginManager.userNo(req));
		modelMap.put("usr_nm", LoginManager.userNm(req));
		modelMap.put("usr_hp", LoginManager.userHp(req));
		modelMap.put("usr_addr", LoginManager.userAddr(req));
		
		modelMap.put("prdt_nm", prdtVO.getPrdt_nm());
		modelMap.put("prdt_count", prdt_count);
		modelMap.put("prdt_sno", prdtVO.getPrdt_sno());
		modelMap.put("total_price", total_price);

		modelMap.put("orderVO", orderVO);

		return "/prdt/prdtOrder";
	}
	
	@RequestMapping(value = "/prdt/orderAdd.do", method=RequestMethod.POST)
	public String orderAddPro(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("orderVO") OrderVO orderVO, HttpSession session) throws Exception {
		
		
		long order_sno = 0;
		
		order_sno = prdtService.orderAdd(orderVO);
		
		//상품정보
		//주문자
		//logger.info("###########" + orderVO.getPrdt_sno());
		//logger.info("###########" + orderVO.getPrdt_count());
		//logger.info("###########" + orderVO.getTotal_price());
		
		
		//사용자 및 배송지
		//logger.info("###########" + orderVO.getUsr_nm());
		//logger.info("###########" + orderVO.getAddr());
		//logger.info("###########" + orderVO.getUsr_hp());
		
		//결제방법
		//logger.info("###########" + orderVO.getPay_gubun());
		//logger.info("###########" + orderVO.getPay_gubun2());
	
		modelMap.put("orderVO", orderVO);

		
		return "redirect:/prdt/prdtList.do";
	}
	

}
