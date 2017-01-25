package lee.comm.func.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lee.comm.func.service.FuncService;

import org.springframework.stereotype.Service;


@Service("funcService")
public class FuncServiceImpl implements FuncService{
	
	@Resource(name="funcDAO")
	private FuncDAO funcDAO;

	/** 분류 카테고리 목록 */
	public List cateList(Map params){
		return funcDAO.cateList(params);
	}
	/** 상품 카테고리 목록 */
	public List lgcateList(){
		return funcDAO.lgcateList();
	}
	

	//주소 목록
	public List zipCodeList(Map params) throws Exception {
		return funcDAO.zipCodeList(params);
	}

}
