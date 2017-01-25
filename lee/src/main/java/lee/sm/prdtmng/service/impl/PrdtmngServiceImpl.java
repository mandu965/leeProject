package lee.sm.prdtmng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lee.sm.prdtmng.service.PrdtmngSearchVO;
import lee.sm.prdtmng.service.PrdtmngService;
import lee.sm.prdtmng.service.PrdtmngVO;

import org.springframework.stereotype.Service;

@Service("prdtmngService")
public class PrdtmngServiceImpl implements PrdtmngService{
	
	@Resource(name="prdtmngDAO")
	private PrdtmngDAO prdtmngDAO;

	public long prdtmngCount(PrdtmngSearchVO prdtmngSearchVO) {
		
		return 0;
	}
	
	public List prdtmngList(PrdtmngSearchVO prdtmngSearchVO) {
		
		return prdtmngDAO.prdtmngList(prdtmngSearchVO);
	}
	
	public long prdtmngAdd(PrdtmngVO prdtmngVO) {
		return prdtmngDAO.prdtmngAdd(prdtmngVO);
	}
	
	public PrdtmngVO prdtmngView(long prdtmng_sno) {
		return prdtmngDAO.prdtmngView(prdtmng_sno);
	}

	public long prdtmngMod(PrdtmngVO prdtmngVO) {
		return prdtmngDAO.prdtmngMod(prdtmngVO);
	}

	
	public boolean prdtmngDel(long prdtmng_sno) {
		
		return false;
	}
	

}
