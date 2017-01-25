package lee.comm.func.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

@Service("funcDAO")
public class FuncDAO {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	/** 분류 카테고리 목록 */
	public List lgcateList(){
		return sqlMapClientTemplate.queryForList("funcDAO.lgcateList");
	}
	
	/** 상품 카테고리 목록 */
	public List cateList(Map params){
		return sqlMapClientTemplate.queryForList("funcDAO.cateList", params);
	}
	
	public List zipCodeList(Map params) throws Exception{
		return sqlMapClientTemplate.queryForList("funcDAO.zipCodeList", params);
	}

}
