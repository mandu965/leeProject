package lee.prdt.service.impl;

import java.util.List;

import lee.prdt.service.OrderVO;
import lee.prdt.service.PrdtSearchVO;
import lee.prdt.service.PrdtVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


@Repository("prdtDAO")
public class PrdtDAO {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
    public List prdtList(PrdtSearchVO prdtSearchVO){
		return sqlMapClientTemplate.queryForList("lee.prdt.prdtList", prdtSearchVO);
	}
    
    public PrdtVO prdtView(long sno){
		return (PrdtVO)sqlMapClientTemplate.queryForObject("lee.prdt.prdtView", sno);
	}
    
    public long cartAdd(OrderVO orderVO){
		return (Long) sqlMapClientTemplate.insert("lee.prdt.cartAdd", orderVO);
    }
    
    public long orderAdd(OrderVO orderVO){
		return (Long) sqlMapClientTemplate.insert("lee.prdt.orderAdd", orderVO);
    }

}
