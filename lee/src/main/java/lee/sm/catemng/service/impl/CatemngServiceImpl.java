package lee.sm.catemng.service.impl;

import javax.annotation.Resource;

import lee.sm.catemng.service.CatemngService;
import lee.sm.catemng.service.CatemngVO;
import lee.sm.catemng.service.LgcatemngVO;

import org.springframework.stereotype.Service;


@Service("catemngService")
public class CatemngServiceImpl implements CatemngService{
	
	@Resource(name="catemngDAO")
	private CatemngDAO CatemngmDAO;

	
	public long lgcatemngAdd(LgcatemngVO lgcemngVO) {
		
		return 0;
	}

	
	public LgcatemngVO lgcatemngView(long lg_catemng_sno) {
		return CatemngmDAO.lgcatemngView(lg_catemng_sno);
	}

	
	public long lgcatemngMod(LgcatemngVO lgcatemngVO) {
		return CatemngmDAO.lgcatemngMod(lgcatemngVO);
	}

	
	public boolean lgcatemngDel(long lg_catemng_sno) {
		return CatemngmDAO.lgcatemngDel(lg_catemng_sno);
	}

	
	public long catemngAdd(CatemngVO catemngVO) {
		return 0;
	}

	
	public CatemngVO catemngView(long catemng_sno) {
		return CatemngmDAO.catemngView(catemng_sno);
	}

	
	public long catemngMod(CatemngVO catemngVO) {
		return CatemngmDAO.catemngMod(catemngVO);
	}

	
	public boolean catemngDel(long catemng_sno) {
		return CatemngmDAO.catemngDel(catemng_sno);
	}

	


}
