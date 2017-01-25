package lee.comm.attach.web;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lee.LeeConstants;
import lee.comm.attach.service.AttachService;
import lee.comm.attach.service.AttachVO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AttachController {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name = "attachService")
	private AttachService attachService;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	/** 첨부파일 업로드 */
	@RequestMapping(value="/json/comm/attach/uploadifyUpload.json")
	public ModelAndView uploadifyUpload(HttpServletRequest req, HttpServletResponse res){
		System.out.println("############################# uploadifyUpload Controller");
		Map<String, String> params = new HashMap<String, String>();
		try{
			params = attachService.uploadify(req);
			if(params!=null){
				params.put("result", "success");
			}else{
				params = new HashMap<String, String>();
				params.put("result", "fail");
			}
		}catch(Exception e){
			params = new HashMap<String, String>();
			params.put("result", "fail");
		}
		return new ModelAndView("jsonView", params);
	}
	
	/** 게시판 파일 다운로드 */
	@RequestMapping(value="/comm/attach/attachBbsDown.do")
	public String attachBbsDown(HttpServletRequest req, HttpServletResponse res){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ 1=");
		InputStream is = null;
		BufferedOutputStream out = null;
		byte[] buffer = new byte[1024];
		try{
			Map params = new HashMap();
			String atch_file_sno_st = req.getParameter("atch_file_sno");
			String atch_file_no_st = req.getParameter("atch_file_no");
			
			System.out.println(atch_file_sno_st + atch_file_no_st);
			
			long atch_file_sno = Integer.parseInt(atch_file_sno_st);
			long atch_file_no = Integer.parseInt(atch_file_no_st);
			//long atch_file_sno = 21;
			//long atch_file_no = 1;
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ 2=");
			params.put("atch_file_sno",atch_file_sno);
			params.put("atch_file_no", atch_file_no);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ 3=");

			AttachVO attachVO = attachService.attachView(atch_file_sno, atch_file_no);
			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ 4=");
			String filename = new String(attachVO.getFile_kpn_nm().getBytes("UTF-8"),"8859_1");
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ 5=");
			File file = new File(LeeConstants.getAttachSaveDir()+attachVO.getKpn_lctn_cntn()+"/"+filename);
		    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ file=" + file);
		    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ file.exists()=" + file.exists());
		    
			if(file.exists()){
				is = new BufferedInputStream(new FileInputStream(file));
				res.setContentType("application/octet-stream");
				if (req.getHeader("User-Agent").indexOf("MSIE 5.5") > -1) {
					res.setHeader("Content-disposition", "filename="+URLEncoder.encode(attachVO.getFile_dsp_nm(),"UTF-8").replaceAll("\\+", "%20")+";");
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ 6");
				}else{
					res.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(attachVO.getFile_dsp_nm(),"UTF-8").replaceAll("\\+", "%20")+";");
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ 7");
				}
				out = new BufferedOutputStream(res.getOutputStream());
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ 8");
				int read = -1;
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ 9");
				while((read = is.read(buffer))!=-1){
					out.write(buffer);
				}
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ 10");
				out.flush(); 
				
				attachService.attachDwn(params);
				
				return null;
			}else
				return "/comm/attach/attachDownFail"; //TODO 다운로드 실패
		}catch(Exception e){
			return "/comm/attach/attachDownFail"; //TODO 다운로드 실패
		}finally{
			try{ if(is!=null) is.close(); }catch(IOException ignore){}
			try{ if(out!=null) out.close(); }catch(IOException ignore){}
		}
	}
	
	

}
