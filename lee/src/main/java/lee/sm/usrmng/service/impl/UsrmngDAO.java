package lee.sm.usrmng.service.impl;

import java.util.List;
import java.util.Map;

import lee.sm.usrmng.service.UsrmngSearchVO;
import lee.sm.usrmng.service.UsrmngVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


@Repository("usrmngDAO") //Repositoy 에서 선언했으니 Service에서 이용할수 있겠구나
public class UsrmngDAO {
	 
	
	/*Autowired, @Resource두개다 사용가능 sqlMapClientTemplate 이 bean으로 등록되어 있기 때문!, 
	다른데서의 Resource는 .xml파일에 bean을 일일이 등록하지 않고 사용할수 있다는게 장점 이렬러고  어노테이션 쓰는구나*/
	/*@Resource */
   @Autowired
	 SqlMapClientTemplate sqlMapClientTemplate;
    
   public List usrmngList(UsrmngSearchVO usrmngSearchVO) {
		return sqlMapClientTemplate.queryForList("lee.sm.usrmngList", usrmngSearchVO);
	}
   
   public UsrmngVO usrmngView(long usr_no) {
	   return (UsrmngVO) sqlMapClientTemplate.queryForObject("lee.sm.usrmngView", usr_no);
	}
	
   public int usrmngMod(UsrmngVO usrmngVO){
		return (Integer)sqlMapClientTemplate.update(("lee.sm.usrmngMod"), usrmngVO);
	}

	public long usrmngAdd(UsrmngVO usrmngVO){
		return (Long) sqlMapClientTemplate.insert(("lee.sm.usrmngAdd"), usrmngVO);
	}
	
	public boolean usrmngDel(Map params){
		return sqlMapClientTemplate.delete("lee.sm.usrmngDel", params )>0;
	}
	
	public List userExcelList(Map params) {
		return sqlMapClientTemplate.queryForList("lee.sm.userExcelList", params);
	}
}







