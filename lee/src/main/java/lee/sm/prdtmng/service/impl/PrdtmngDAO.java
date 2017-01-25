package lee.sm.prdtmng.service.impl;

import java.util.List;

import lee.sm.prdtmng.service.PrdtmngSearchVO;
import lee.sm.prdtmng.service.PrdtmngVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("prdtmngDAO")
public class PrdtmngDAO {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
public long prdtmngCount(PrdtmngSearchVO prdtmngSearchVO) {
		
		return 0;
	}

	
	public List prdtmngList(PrdtmngSearchVO prdtmngSearchVO) {
		return sqlMapClientTemplate.queryForList("lee.sm.prdtmngList", prdtmngSearchVO);
	}

	
	public long prdtmngAdd(PrdtmngVO prdtmngVO) {
		return (Long) sqlMapClientTemplate.insert("lee.sm.prdtmngAdd", prdtmngVO);
	}

	
	public PrdtmngVO prdtmngView(long prdtmng_sno) {
		return (PrdtmngVO) sqlMapClientTemplate.queryForObject("lee.sm.prdtmngView", prdtmng_sno);
	}

	
	public long prdtmngMod(PrdtmngVO prdtmngVO) {
		return sqlMapClientTemplate.update("lee.sm.prdtmngMod", prdtmngVO);
	}

	
	public boolean prdtmngDel(long prdtmng_sno) {
		
		return false;
	}
	

}
