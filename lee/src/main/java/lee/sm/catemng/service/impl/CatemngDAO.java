package lee.sm.catemng.service.impl;

import lee.sm.catemng.service.CatemngVO;
import lee.sm.catemng.service.LgcatemngVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


@Repository("catemngDAO")
public class CatemngDAO {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public long lgcatemngAdd(LgcatemngVO lgcemngVO) {
		return (Long) sqlMapClientTemplate.insert("lee.sm.lgcatemngAdd", lgcemngVO);
	}

	
	public LgcatemngVO lgcatemngView(long lg_catemng_sno) {
		return (LgcatemngVO) sqlMapClientTemplate.queryForObject("lee.sm.lgcatemngView", lg_catemng_sno);
	}

	
	public long lgcatemngMod(LgcatemngVO lgcatemngVO) {
		return sqlMapClientTemplate.update("lee.sm.lgcatemngMod", lgcatemngVO);
	}

	
	public boolean lgcatemngDel(long lg_catemng_sno) {
		return sqlMapClientTemplate.delete("lee.sm.lgcatemngDel", lg_catemng_sno) >0;
	}

	
	public long catemngAdd(CatemngVO catemngVO) {
		return (Long) sqlMapClientTemplate.insert("lee.sm.catemngAdd", catemngVO);
	}

	
	public CatemngVO catemngView(long catemng_sno) {
		return (CatemngVO) sqlMapClientTemplate.queryForObject("lee.sm.catemngView", catemng_sno);
	}

	
	public long catemngMod(CatemngVO catemngVO) {
		return sqlMapClientTemplate.update("lee.sm.catemngMod", catemngVO);
	}

	
	public boolean catemngDel(long catemng_sno) {
		return sqlMapClientTemplate.delete("lee.sm.catemngDel", catemng_sno) >0;
	}
	

}
