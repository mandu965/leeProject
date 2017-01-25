package lee.sm.ordermng.service;

import java.util.List;

public interface OrdermngService {
	
	/** 주문상품 수 */
	long ordermngCount(OrdermngSearchVO ordermngSearchVO);
	
	/** 주문상품 목록 */
	List ordermngList(OrdermngSearchVO ordermngSearchVO);
	
	/** 주문상품 등록 */
	long ordermngAdd(OrdermngVO ordermngVO);
	
	/** 주문상품 조회 */
	OrdermngVO ordermngView(long ordermng_sno);
	
	/** 주문상품 수정 */
	long ordermngMod(OrdermngVO ordermngVO);
	
	/** 주문상품 삭제 */
	boolean ordermngDel(long ordermng_sno);

}
