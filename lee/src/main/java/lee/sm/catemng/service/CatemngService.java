package lee.sm.catemng.service;


public interface CatemngService {

	/** 분류카테고리 등록 */
	long lgcatemngAdd(LgcatemngVO lgcemngVO);
	
	/** 분류카테고리 조회 */
	LgcatemngVO lgcatemngView(long lg_catemng_sno);
	
	/** 분류카테고리 수정 */
	long lgcatemngMod(LgcatemngVO lgcatemngVO);
	
	/** 분류카테고리 삭제 */
	boolean lgcatemngDel(long lg_catemng_sno);
	
	
	/** 상품카테고리 등록 */
	long catemngAdd(CatemngVO catemngVO);
	
	/** 상품카테고리 조회 */
	CatemngVO catemngView(long catemng_sno);
	
	/** 상품카테고리 수정 */
	long catemngMod(CatemngVO catemngVO);
	
	/** 상품카테고리 삭제 */
	boolean catemngDel(long catemng_sno);
}
