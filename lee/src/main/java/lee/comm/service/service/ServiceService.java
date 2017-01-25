package lee.comm.service.service;

import java.util.List;
import java.util.Map;

import lee.comm.attach.service.AttachVO;

public interface ServiceService {
	
	/** 외부메일발송 */
	boolean outMailSend(Map params, List<AttachVO> fileList) throws Exception;

}
