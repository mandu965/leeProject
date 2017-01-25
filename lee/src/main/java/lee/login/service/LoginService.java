package lee.login.service;

import java.util.List;

import lee.sm.usrmng.service.UsrmngVO;


public interface LoginService {
	
	
	List idCheck(String id);
	List pwdCheck(String pwd);
	
	/** 로그인 사용자 정보(로그인시 사용) */
	UsrmngVO loginProc(String lgin_id)  throws Exception;
	
	/** 세션 사용자 정보 */
	/*UsrVO userSessionView(long usrNo)  throws Exception;*/
	
	/** 접속정보 기록 */
	void usrHistAdd(String usr_no, String ip)  throws Exception;
	

}
