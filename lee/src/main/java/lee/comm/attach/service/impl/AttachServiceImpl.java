package lee.comm.attach.service.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import lee.LeeConstants;
import lee.comm.attach.service.AttachService;
import lee.comm.attach.service.AttachVO;
import lee.login.web.LoginManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("attachService")
public class AttachServiceImpl implements AttachService{
	
	@Resource(name="attachDAO")  
	private AttachDAO attachDAO;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	
	/** 파일 유형 구분 ArrayList, CommonsMultipartFile 사용이유 재검토 */
	public boolean checkClassName(Object obj, String name){
		Object classObj = null;
		String className = "";
		boolean result = false;
		
		if(obj!=null) classObj = obj.getClass();
		if(classObj!=null) className = classObj.toString();
		if(className.indexOf(name)!=-1) result = true;
		
		return result;
	}
	
	/** 확장자 추출 */
	public String getFileExtension(String fileName){
		int idx = fileName.lastIndexOf(".");
		if(idx>-1)
			return fileName.substring(idx+1);
		else return "";
	}
	
	/** 파일 이름을 랜덤 숫자로 변경 */
	public String getNumericName(String path, String fileName){
		Random random = new Random();
		String newName = "";
		String ext = getFileExtension(fileName);
		File file = null;
		do {
			newName = ""+random.nextInt(99999999);
			newName = "".equals(ext) ? newName : newName +"."+ext;
			file = new File(path+"/"+newName);
		} while(file.exists());
		return newName;
	}
	
	/** 현재 년월 ex)201501, 201511 */
	public String getDateStr(){
		Calendar cal = Calendar.getInstance();
		String calStr = "";
		if(cal.get(Calendar.MONTH)+1<10)
			calStr = cal.get(Calendar.YEAR)+"0"+(cal.get(Calendar.MONTH)+1);
		else
			calStr = cal.get(Calendar.YEAR)+""+(cal.get(Calendar.MONTH)+1);
		return calStr;
	}
	
	public AttachVO attachView(long atch_file_sno, long atch_file_no) throws Exception {
		Map params = new HashMap();
		params.put("atch_file_sno", atch_file_sno);
		params.put("atch_file_no", atch_file_no);
		return attachDAO.attachView(params);
	}
	
	/** 시스템에서 파일 삭제하기 */
	public static boolean attachSysDel(String kpn_lctn_cntn, String file_kpn_nm) throws Exception {
		File tmp = new File(LeeConstants.getAttachSaveDir()+kpn_lctn_cntn+"/"+file_kpn_nm);
		if(tmp.exists()) tmp.delete();
		return true;
	}
	
	/*public boolean attachDel(long atch_file_sno, String dtl_cntn_yn) throws Exception {
		List<AttachVO> list = attachList(atch_file_sno, dtl_cntn_yn);
		if(attachDAO.attachDel(atch_file_sno)){
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					AttachVO attach = list.get(i);
					attachSysDel(attach.getKpn_lctn_cntn(), attach.getFile_kpn_nm());
				}
			}
			return true;
		}else{
			return false;
		}
	}*/
	
	/** 파일 DB에서 삭제 */
	public boolean attachDel(long atch_file_sno, long atch_file_no) throws Exception {
		AttachVO attach = attachView(atch_file_sno, atch_file_no);//////////sno, no 해당 AttachVO 가지고 오기 왜 가져오는가? 시스템에서 지우려고
		if(attachDAO.attachDel(atch_file_sno, atch_file_no)){
			return attachSysDel(attach.getKpn_lctn_cntn(), attach.getFile_kpn_nm());
		}else{
			return false;
		}
	}
	
	/*public List<AttachVO> attachList(long atch_file_sno, String dtl_cntn_yn) throws Exception {
		return attachDAO.attachList(atch_file_sno, dtl_cntn_yn);
	}*/
	
	
	/** 파일 업로드 */
	public Map<String, String> uploadify(HttpServletRequest req){
		File realFile = null;
		boolean result = false;
		Map<String, String> params = new HashMap<String, String>();
		String kpn_lctn_cntn = "";
		String kpn_url = "";
		String file_kpn_nm = "";
		String file_estn_nm = "";
		String file_tp_nm = "";
		long file_sz_byte = 0;
		int ext_check = 0;
		String ext_check_db = "";
		
		try{
			MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req; // MultipartHttpServletRequest 파일 업로드 관련
			String folder = mreq.getParameter("folder")!=null ? mreq.getParameter("folder") : "default";
			folder = folder.indexOf("/")!=-1 ? folder.substring(folder.lastIndexOf("/")+1) : folder; //    /lee/board/notice/leeFile 에서 leeFile만 가지고 온다
			Map files = (Map)mreq.getFileMap();
			
			if(folder.equals("product") ){
				kpn_lctn_cntn = "/"+folder; //저장위치내용
			}else{
				kpn_lctn_cntn = "/"+folder+"/"+getDateStr(); //저장위치내용	
			}
			
			String dirStr = ""; //파일 저장 경로
			dirStr = LeeConstants.getAttachSaveDir()+kpn_lctn_cntn;

			File directory = new File(dirStr);
			if(!directory.exists()) directory.mkdirs(); //해당 디렉토리가 존재하지 않는다면 만들기
			
			ext_check_db = "xlsx,xls,hwp,pdf,dwg,jpg,jpeg,gif,png,bmp,zip,xls,xlsx,doc,docx,ppt,pptx";
			
			if(!files.isEmpty()){
				Iterator iter = files.keySet().iterator();
				while(iter.hasNext()){
					String name = iter.next().toString();
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ iter.next().toString(), name=" + name);
					if(checkClassName(files.get(name), "ArrayList")){
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 파일 클래스 종류 ArrayList");
						List list = (List)files.get(name);
						for(int i=0;i<list.size();i++){ 
							if(checkClassName(list.get(i), "CommonsMultipartFile")){
								MultipartFile mfile = (MultipartFile)list.get(i);
								file_tp_nm = mfile.getContentType();
								file_kpn_nm = getNumericName(dirStr, mfile.getOriginalFilename());
								file_estn_nm = getFileExtension(file_kpn_nm);
								realFile = new File(dirStr+"/"+file_kpn_nm);
								mfile.transferTo(realFile);
								if(realFile.exists()){
									ext_check = (ext_check_db.toLowerCase()).indexOf(file_estn_nm.toLowerCase());	// 첨부 가능한 확장자인지 체크 (-1 이면 첨부 불가능)
									result = true;
									file_sz_byte = realFile.length();
								}
							}
						}
					}else if(checkClassName(files.get(name), "CommonsMultipartFile")){
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 파일 클래스 종류 CommonsMultipartFile"); //name = Filedata 
						MultipartFile mfile = (MultipartFile)files.get(name);
						file_tp_nm = mfile.getContentType(); //파일유형
						file_kpn_nm = getNumericName(dirStr, mfile.getOriginalFilename()); // 파일 저장명
						file_estn_nm = getFileExtension(file_kpn_nm); //파일 확장자
						realFile = new File(dirStr+"/"+file_kpn_nm); //실제 파일 저장하는 코드
						mfile.transferTo(realFile); // 해당 path로 파일 이동
						if(realFile.exists()){
							ext_check = (ext_check_db.toLowerCase()).indexOf(file_estn_nm.toLowerCase());	// 첨부 가능한 확장자인지 체크 (-1 이면 첨부 불가능)
							result = true;
							file_sz_byte = realFile.length();
						}
						
						
						if(folder.equals("product") ){//상품이면 썸네일 이미지 만들기
							if(file_estn_nm.equals("jpg") || file_estn_nm.equals("jpeg")){
								
								try {
						            //썸네일 가로사이즈
						            int thumbnail_width = 200;
						            //썸네일 세로사이즈
						            int thumbnail_height = 200;
						            String tumbnaildirStr = dirStr + "/thumb";
						            //원본이미지파일의 경로+파일명
						            File origin_file_name = new File(dirStr+"/"+file_kpn_nm);
						            //썸네일파일의 경로+썸네일파일명
						            File thumb_file_name = new File(tumbnaildirStr + "/" + file_kpn_nm);
						            
						            File thumbDirectory = new File(tumbnaildirStr);
									if(!thumbDirectory.exists()) thumbDirectory.mkdirs(); //해당 디렉토리가 존재하지 않는다면 만들기
						 
						            BufferedImage buffer_original_image = ImageIO.read(origin_file_name);
						            BufferedImage buffer_thumbnail_image = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_3BYTE_BGR);
						            Graphics2D graphic = buffer_thumbnail_image.createGraphics();
						            graphic.drawImage(buffer_original_image, 0, 0, thumbnail_width, thumbnail_height, null);
						            ImageIO.write(buffer_thumbnail_image, "jpg", thumb_file_name);
						            System.out.println("썸네일 생성완료");
						        } catch (Exception e) {
						            e.printStackTrace();
						        }
								
							}
							 
						}
						
						
					}
				}//while
			}//if
			
			if(result){
				params.put("kpn_lctn_cntn", kpn_lctn_cntn);
				params.put("kpn_url", kpn_url);
				params.put("file_kpn_nm", file_kpn_nm);
				params.put("file_estn_nm", file_estn_nm);
				params.put("file_tp_nm", file_tp_nm);
				params.put("file_sz_byte", ""+file_sz_byte);
				params.put("ext_check", ""+ext_check);
				params.put("ext_check_db", ""+ext_check_db);
				return params;
			}else return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public long attachAdd(HttpServletRequest req, long atch_file_sno, String svc_dman_cd, long svc_sno, String open_yn) throws Exception {
		List<AttachVO> list = new ArrayList<AttachVO>();
		/*String[] kpn_lctn_cntn = RequestUtil.getStringArray(req, "kpn_lctn_cntn");
		String[] kpn_url = RequestUtil.getStringArray(req, "kpn_url");
		String[] file_dsp_nm = RequestUtil.getStringArray(req, "file_dsp_nm");
		String[] file_kpn_nm = RequestUtil.getStringArray(req, "file_kpn_nm");
		String[] file_estn_nm = RequestUtil.getStringArray(req, "file_estn_nm");
		String[] file_tp_nm = RequestUtil.getStringArray(req, "file_tp_nm");
		String[] file_sz_byte = RequestUtil.getStringArray(req, "file_sz_byte");
		String[] rsrc_cmnt_cntn = RequestUtil.getStringArray(req, "rsrc_cmnt_cntn");*/
		
		String[] kpn_lctn_cntn = req.getParameterValues("kpn_lctn_cntn"); //저장위치내용
		String[] kpn_url = req.getParameterValues("kpn_url"); // 저장URL
		String[] file_dsp_nm = req.getParameterValues("file_dsp_nm"); //파일표시명
		String[] file_kpn_nm = req.getParameterValues("file_kpn_nm"); //파일저장명
		String[] file_estn_nm = req.getParameterValues("file_estn_nm"); //파일확장명
		String[] file_tp_nm = req.getParameterValues("file_tp_nm"); //파일유형명
		String[] file_sz_byte = req.getParameterValues("file_sz_byte"); //파일크기바이트
		String[] rsrc_cmnt_cntn = req.getParameterValues("rsrc_cmnt_cntn"); //자료코멘트내용
		
		long atch_file_sno2 = atch_file_sno;
		
		if(file_kpn_nm!=null && file_kpn_nm.length>0){
			for(int i=0;i<file_kpn_nm.length;i++){
				AttachVO vo = new AttachVO();
				
				vo.setReg_usr_no(LoginManager.userNo(req));
				vo.setAtch_file_sno(atch_file_sno2);
				vo.setSvc_dman_cd(svc_dman_cd);
				vo.setSvc_sno(svc_sno);
				vo.setAtch_file_ver(1);
				vo.setKpn_lctn_cntn(kpn_lctn_cntn[i]);
				vo.setKpn_url(kpn_url[i]);
				vo.setFile_dsp_nm(file_dsp_nm[i]);
				vo.setFile_kpn_nm(file_kpn_nm[i]);
				vo.setFile_estn_nm(file_estn_nm[i]);
				vo.setFile_tp_nm(file_tp_nm[i]);
				vo.setFile_sz_byte(Integer.parseInt(file_sz_byte[i]));
				vo.setOpen_yn(open_yn);
				vo.setRsrc_cmnt_cntn(rsrc_cmnt_cntn[i]);
				list.add(vo);
			}
			atch_file_sno2 = attachDAO.attachListAdd(atch_file_sno2, list);
		}
		
		if(atch_file_sno2 > 0){
			String[] del_file_no = req.getParameterValues("del_file_no");
			if(del_file_no!=null && del_file_no.length>0){
				for(int i=0;i<del_file_no.length;i++){
					attachDel(atch_file_sno2, Integer.parseInt(del_file_no[i]));
				}
			}
		}
		return atch_file_sno2;
	}
	
	public List<AttachVO> attachList(long atch_file_sno, String dtl_cntn_yn) throws Exception {
		return attachDAO.attachList(atch_file_sno, dtl_cntn_yn);
	}
	
	/** 첨부파일 조회수 수정 */
	public boolean attachDwn(Map params) throws Exception{
		return attachDAO.attachDwn(params);
	}

}
