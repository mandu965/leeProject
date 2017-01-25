package lee.comm.func.service;

import java.util.List;
import java.util.Map;

public interface FuncService {
	
	/** 분류 카테고리 목록 */
	List lgcateList();
	
	/** 상품 카테고리 목록 */
	List cateList(Map params);
	
	/** 주소 목록 */
	List zipCodeList(Map params)throws Exception;

}
