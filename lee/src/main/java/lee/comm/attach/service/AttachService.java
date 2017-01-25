package lee.comm.attach.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface AttachService {
	
	/** 파일 업로드 */
	Map<String, String> uploadify(HttpServletRequest req);
	
	public long attachAdd(HttpServletRequest req, long atch_file_sno, String svc_dman_cd, long svc_sno, String open_yn) throws Exception;
	
	/**
	 * 첨부파일 목록
	 * @param atch_file_sno : 첨부파일 일련번호
	 * @param dtl_cntn_yn : 세부내용여부 (Y:세부내용만 가져옴, N:세부내용을 제외하고 가져옴, A:모두 가져옴)
	 * @return List<AttachVO> : 첨부파일 목록
	 */
	List<AttachVO> attachList(long atch_file_sno, String dtl_cntn_yn) throws Exception;
	
	/**
	 * 첨부파일 조회
	 * @param atch_file_sno : 첨부파일 일련번호
	 * @param atch_file_no : 첨부파일 번호
	 * @return AttachVO : 첨부파일 정보
	 */
	AttachVO attachView(long atch_file_sno, long atch_file_no) throws Exception;
	
	/** 첨부파일 조회수 수정 */
	boolean attachDwn(Map params) throws Exception;

}
