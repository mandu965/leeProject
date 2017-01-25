package lee.sm.ordermng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("ordermngDAO")
public class OrdermngDAO {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	

}
