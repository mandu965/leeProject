package lee.mp.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lee.login.web.LoginManager;
import lee.mp.service.MpService;
import lee.mp.service.OrderSearchVO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MpController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name="MpService")
	MpService MpService;
	

	//주문 상품 목록(장바구니 포함)
	@RequestMapping(value = "/mp/orderList.do")
	public String orderList(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("orderSearchVO") OrderSearchVO orderSearchVO, HttpSession session){
		String uri =req.getRequestURI(); 
		uri = uri.replace("/lee", "");   
		String jsp_path =uri.substring(0, uri.lastIndexOf("/")+1);
		
		orderSearchVO.setUsr_no(LoginManager.userNo(req));
		orderSearchVO.setCartYn("Y");
		List orderList =  MpService.orderList(orderSearchVO);
		
		///////paging : S//////////////////////////////
		
		int pageSize = orderSearchVO.getPageSize();
		int pageIndex = orderSearchVO.getPageIndex();
		int pageGroupSize = orderSearchVO.getPageGroupSize();
		

		int startRow = (pageIndex - 1) * pageSize;// 한 페이지의 시작글 번호
		int endRow = pageIndex * pageSize;// 한 페이지의 마지막 글번호
		int count = orderList.size();
		
		// 페이지그룹의 갯수
		// ex) pageGroupSize가 3일 경우 '[1][2][3]'가 pageGroupCount 개 만큼 있다.
		int pageGroupCount = count / (pageSize * pageGroupSize) + (count % (pageSize * pageGroupSize) == 0 ? 0 : 1);
		//                 = 24    /         10*2               +   24   % 20 = 0 ? 0: 1     

		// 페이지 그룹 번호
		// ex) pageGroupSize가 3일 경우 '[1][2][3]'의 페이지그룹번호는 1 이고 '[2][3][4]'의
		// 페이지그룹번호는 2 이다.
		int numPageGroup = (int) Math.ceil((double) pageIndex / pageGroupSize);
		
		ArrayList articleList = new ArrayList();

		if (count > 0) {
			if (endRow > count) //ex) count가 19일 경우 endRow를 19로 마춘다
				endRow = count;
			for (int i = startRow; i < endRow; i++) {
				articleList.add(orderList.get(i));
			}

		} else {
			articleList = null;
		}
		
		modelMap.put("pageIndex", pageIndex);
		modelMap.put("pageSize", pageSize);
		modelMap.put("count", count);
		modelMap.put("pageGroupSize", pageGroupSize);
		modelMap.put("numPageGroup", numPageGroup);
		modelMap.put("pageGroupCount", pageGroupCount);
		modelMap.put("articleList", articleList);
		///////paging : E//////////////////////////////
		
		return "/mp/orderList";
	}
	
	
	@RequestMapping(value = "/mp/usr_mod.do")
	public String usr_mod(HttpServletRequest req, ModelMap modelMap) {
	
		return "/mp/usr_mod";
	}	
}
