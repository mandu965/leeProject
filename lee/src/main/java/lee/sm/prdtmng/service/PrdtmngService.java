package lee.sm.prdtmng.service;

import java.util.List;

public interface PrdtmngService {
	
	/** 상품 수 */
	long prdtmngCount(PrdtmngSearchVO prdtmngSearchVO);
	
	/** 상품 목록 */
	List prdtmngList(PrdtmngSearchVO prdtmngSearchVO);
	
	/** 상품 등록 */
	long prdtmngAdd(PrdtmngVO prdtmngVO);
	
	/** 상품 조회 */
	PrdtmngVO prdtmngView(long prdtmng_sno);
	
	/** 상품 수정 */
	long prdtmngMod(PrdtmngVO prdtmngVO);
	
	/** 상품 삭제 */
	boolean prdtmngDel(long prdtmng_sno);

}
