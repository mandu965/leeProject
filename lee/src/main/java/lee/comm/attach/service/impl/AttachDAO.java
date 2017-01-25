package lee.comm.attach.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lee.comm.attach.service.AttachVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("attachDAO")
public class AttachDAO {
	
	@Autowired
	 SqlMapClientTemplate sqlMapClientTemplate;

	public long attachListAdd(long atch_file_sno, List<AttachVO> list) throws Exception {
		Object obj = null;
		long atch_file_sno2 = atch_file_sno;
		if(list!=null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				AttachVO vo = list.get(i);
				vo.setAtch_file_sno(atch_file_sno2);
				obj = sqlMapClientTemplate.insert("attachDAO.attachAdd", vo);
				if(obj!=null) atch_file_sno2 = (Long) obj;
			}
		}
		return obj==null ? 0 : (Long) obj;
	}
	
	/*public boolean attachDel(long atch_file_sno) throws Exception {
		Map params = new HashMap();
		params.put("atch_file_sno", atch_file_sno);
		int num = sqlMapClientTemplate.delete("attachDAO.attachDel", params);
		return true;
	}*/
	
	/*public boolean attachDel(long atch_file_sno, String flag, long usr_no) throws Exception {
		Map params = new HashMap();
		params.put("atch_file_sno", atch_file_sno);
		params.put("del_yn", flag);
		params.put("usr_no", usr_no);
		int num = sqlMapClientTemplate.update("attachDAO.attachFlagMod", params);
		return true;
	}*/
	
	/** 파일 DB에서 삭제 */
	public boolean attachDel(long atch_file_sno, long atch_file_no) throws Exception {
		Map params = new HashMap();
		params.put("atch_file_sno", atch_file_sno);
		params.put("atch_file_no", atch_file_no);
		int num = sqlMapClientTemplate.delete("attachDAO.attachDel", params);
		return true;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<AttachVO> attachList(long atch_file_sno, String dtl_cntn_yn) throws Exception {
		Map params = new HashMap();
		params.put("atch_file_sno", atch_file_sno);
		params.put("dtl_cntn_yn", dtl_cntn_yn);
		return sqlMapClientTemplate.queryForList("attachDAO.attachList", params);
	}*/
	
	public AttachVO attachView(Map params) throws Exception {
		Object obj = sqlMapClientTemplate.queryForObject("attachDAO.attachView", params);
		return obj==null ? null : (AttachVO)obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<AttachVO> attachList(long atch_file_sno, String dtl_cntn_yn) throws Exception {
		Map params = new HashMap();
		params.put("atch_file_sno", atch_file_sno);
		params.put("dtl_cntn_yn", dtl_cntn_yn);
		return sqlMapClientTemplate.queryForList("attachDAO.attachList", params);
	}
	
	/** 첨부파일 조회수 수정 */
	public boolean attachDwn(Map params) throws Exception{
		int num = sqlMapClientTemplate.update("attachDAO.attachDwn", params); 
		return num == 1;
	}
	
	
	
}
