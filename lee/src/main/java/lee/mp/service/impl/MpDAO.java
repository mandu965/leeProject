package lee.mp.service.impl;

import java.util.List;

import lee.mp.service.OrderSearchVO;
import lee.prdt.service.OrderVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


@Repository("mypageDAO")
public class MpDAO {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public List orderList(OrderSearchVO orderSearchVO){
		return sqlMapClientTemplate.queryForList("lee.mp.orderList", orderSearchVO);
	}
}
