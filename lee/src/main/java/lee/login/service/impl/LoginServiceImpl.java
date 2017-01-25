package lee.login.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lee.login.service.LoginService;
import lee.sm.usrmng.service.UsrmngVO;

import org.springframework.stereotype.Service;


@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Resource(name="loginDAO")
	private LoginDAO loginDAO;

	@Override
	public List idCheck(String id) {
		// TODO Auto-generated method stub
		return loginDAO.idCheck(id);
	}

	@Override
	public List pwdCheck(String pwd) {
		// TODO Auto-generated method stub
		return loginDAO.pwdCheck(pwd);
	}

	@Override/** 로그인 사용자 정보 */
	public UsrmngVO loginProc(String id) throws Exception {
		// TODO Auto-generated method stub
		return loginDAO.loginProc(id);
	}

	//@Override/** 세션 사용자 정보 */
	/*public UsrVO userSessionView(long usrNo) throws Exception {
		// TODO Auto-generated method stub
		return (UsrVO) loginDAO.userSessionView(usrNo);
	}*/

	@Override/** 접속정보 기록 */
	public void usrHistAdd(String usr_no, String ip) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
