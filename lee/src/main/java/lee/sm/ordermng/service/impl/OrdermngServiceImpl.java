package lee.sm.ordermng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lee.sm.ordermng.service.OrdermngSearchVO;
import lee.sm.ordermng.service.OrdermngService;
import lee.sm.ordermng.service.OrdermngVO;

import org.springframework.stereotype.Service;

@Service("ordermngService")
public class OrdermngServiceImpl implements OrdermngService{
	
	@Resource(name="ordermngDAO")
	private OrdermngDAO ordermngDAO;

	
	public long ordermngCount(OrdermngSearchVO ordermngSearchVO) {
		
		return 0;
	}

	
	public List ordermngList(OrdermngSearchVO ordermngSearchVO) {
		
		return null;
	}

	
	public long ordermngAdd(OrdermngVO ordermngVO) {
		
		return 0;
	}

	
	public OrdermngVO ordermngView(long ordermng_sno) {
		
		return null;
	}

	
	public long ordermngMod(OrdermngVO ordermngVO) {
		
		return 0;
	}

	
	public boolean ordermngDel(long ordermng_sno) {
		
		return false;
	}
	

}
