package lee.prdt.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface PrdtService {
	
	//상품 리스트
	List prdtList(PrdtSearchVO prdtSearchVO) throws Exception;
	
	PrdtVO prdtView(long prdt_sno) throws Exception;
	
	long cartAdd(HttpServletRequest req, PrdtVO prdtVO);
	
	long orderAdd(OrderVO orderVO);
	

}
