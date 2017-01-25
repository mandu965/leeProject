package lee.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("mainDAO")
public class MainDAO {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;

	
	/** 메인공지사항 목록 */
	public List mainNoticeList(){
		return sqlMapClientTemplate.queryForList("lee.main.mainNoticeList", null);
	}

}
