package lee.comm.service.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import lee.LeeConstants;
import lee.comm.attach.service.AttachVO;
import lee.comm.service.service.ServiceService;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class ServiceServiceImpl implements ServiceService {
	
	/** 외부메일발송 */
	public boolean outMailSend(Map params, List<AttachVO> fileList) throws Exception {
		
		try {
			
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			
			sender.setHost(LeeConstants.getMailServerHost());
			sender.setPort(LeeConstants.getMailServerPort());
			
			if(LeeConstants.getMailServerSmtpAuth()){
				sender.setUsername(LeeConstants.getMailUserName());
				sender.setPassword(LeeConstants.getMailPassword());
			}
			
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setFrom(params.get("from_email").toString(),params.get("from_name").toString());
			helper.setTo(params.get("to_email").toString());
			
			String title =  params.get("title").toString();
			String content = params.get("cntn").toString() + "<br/><br/>" ;
			String fromName = params.get("from_name").toString() + " (" + params.get("from_mbl_tel").toString() + ")";
			
			helper.setSubject(title);
			
			helper.setText("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>" +
					"<html xmlns='http://www.w3.org/1999/xhtml'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />" +
					"<title>메일</title></head><body style='margin:0'><table width='600' border='0' cellspacing='0' cellpadding='0'><tr>" +
					"<td align='center'><img src='cid:identifier1'/></td>" +
					"</tr><tr><td><img src='cid:identifier2'/></td></tr><tr> " +
					"<td style='font-weight:bold; font-family:돋움; font-size:12px; padding:10px 10px ;color:#373737;' height='30'>"+title+"</td>" +
					"</tr><tr> <td style=' padding:0px 10px 30px 10px;font-family:돋움; font-size:12px;color:#585858; ' height='100' valign='top' >" +
					""+content+"</td></tr><tr>" +
					"<td style=' padding:10px 10px ;font-family:돋움; font-size:12px; color:#26837B;' align='right'>"+fromName+"</td></tr><tr> " +
					"<td><img src='cid:identifier3' /></td></tr><tr> <td ></td></tr>" +
					"</table></body></html>", true);

			FileSystemResource fs1 = new FileSystemResource(new File(LeeConstants.getImgUrl()+"/mail/logo.gif"));
			helper.addInline("identifier1", fs1);
			FileSystemResource fs2 = new FileSystemResource(new File(LeeConstants.getImgUrl()+"/mail/line1.gif"));
			helper.addInline("identifier2", fs2);
			FileSystemResource fs3 = new FileSystemResource(new File(LeeConstants.getImgUrl()+"/mail/line2.gif"));
			helper.addInline("identifier3", fs3);
			
			if(fileList != null && fileList.size() > 0){
				for (int i = 0; i < fileList.size(); i++) {
					AttachVO fvo = fileList.get(i);
					FileSystemResource af = new FileSystemResource(new File(LeeConstants.getAttachSaveDir()+fvo.getKpn_lctn_cntn()+"/"+fvo.getFile_kpn_nm()));
					helper.addAttachment(MimeUtility.encodeText(fvo.getFile_dsp_nm(),"UTF-8", "B"), af);
				}
			}
			
			sender.send(message);
			
			return true;
			
		} catch (Exception e) {
			//logger.error(e);
			return false;
		}
		
	}

}
