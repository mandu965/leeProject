package lee.prdt.service.impl;

import java.util.List;

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

import org.springframework.stereotype.Service;


@Service("prdtService")
public class PrdtServiceImpl implements PrdtService  {
	
	@Resource(name ="prdtDAO")
	private PrdtDAO prdtDAO;

	public List prdtList(PrdtSearchVO prdtSearchVO) throws Exception {
		return prdtDAO.prdtList(prdtSearchVO);
	}

	public PrdtVO prdtView(long prdt_sno) throws Exception {
		return prdtDAO.prdtView(prdt_sno);
	}


	public long cartAdd(HttpServletRequest req, PrdtVO prdtVO) {
		OrderVO orderVO = new OrderVO();
		
		long usr_no = LoginManager.userNo(req);
		
		
		long prdt_count = RequestUtil.getLong(req, "prdt_count");
		long total_price =prdtVO.getPrdt_price() *  prdt_count;
		
		orderVO.setOrder_state(LeeCodeConstants.PRDT_STATUS_CART);
		orderVO.setUsr_no(usr_no);
		orderVO.setPrdt_count(prdt_count);
		orderVO.setPrdt_sno(prdtVO.getPrdt_sno());
		orderVO.setTotal_price(total_price);
		orderVO.setDel_yn("N");
		
		
		return prdtDAO.cartAdd(orderVO);
	}
	
	@Override
	public long orderAdd(OrderVO orderVO) {
		
		orderVO.setOrder_state(LeeCodeConstants.PRDT_STATUS_ORDER);
		return prdtDAO.orderAdd(orderVO);
	}
	
	
	
	

}
