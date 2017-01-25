package lee.login.service.impl;

import java.util.List;

import lee.sm.usrmng.service.UsrmngVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


@Repository("loginDAO")
public class LoginDAO {
	
	@Autowired
    SqlMapClientTemplate sqlMapClientTemplate;
	
	public List idCheck(String id){
		Object object = new Object();
		object =sqlMapClientTemplate.queryForList("lee.selID", id);
		return (List)object;
	}
	
	public List pwdCheck(String pwd){
		return sqlMapClientTemplate.queryForList("lee.selPWD", pwd);
	}
	
	/** 로그인 사용자 정보 */
	public UsrmngVO loginProc(String id) throws Exception {
		return (UsrmngVO) sqlMapClientTemplate.queryForObject("lee.loginInfo", id);
		//return (UsrVO) selectByPk("loginDAO.userLoginView", id);
	}
	
	/** 세션 사용자 정보 */
	/*public UsrVO userSessionView(long usrNo) throws Exception {
		return (UsrVO)sqlMapClientTemplate.queryForObject("lee.usrSessionView", usrNo);
	}*/
	
	/*public void usrHistAdd(Map params) {
		insert("loginDAO.usrHistAdd", params);	
	}*/

}
