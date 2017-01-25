package lee.sm.usrmng.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lee.comm.domain.CK_USR;


public interface UsrmngService {
	
	/** 사용자 수 */
	long usrmngCount(UsrmngSearchVO usrmngSearchVO);
	
	/** 사용자 목록 */
	List usrmngList(UsrmngSearchVO usrmngSearchVO);
	
	/** 사용자 조회 */
	UsrmngVO usrmngView(long usr_no);
	
	/** 사용자 수정 */
	long usrmngMod(UsrmngVO usrmngVO);
	
	/** 회원가입  */
	long usrmngAdd(UsrmngVO usrmngVO);
	
	/** 회원탈퇴  */
	boolean usrmngDel(Map params);
	
	/** 사용자 목록 엑셀 다운로드*/
	List userExcelList(Map params);
}
