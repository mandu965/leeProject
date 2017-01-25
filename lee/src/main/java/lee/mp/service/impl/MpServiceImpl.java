package lee.mp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lee.mp.service.MpService;
import lee.mp.service.OrderSearchVO;
import lee.prdt.service.OrderVO;

import org.springframework.stereotype.Service;


@Service("MpService")
public class MpServiceImpl implements MpService{
	
	@Resource(name="mypageDAO")
	private MpDAO mypageDAO;

	public List orderList(OrderSearchVO orderSearchVO){
		return mypageDAO.orderList(orderSearchVO);
	}
	
	

}
