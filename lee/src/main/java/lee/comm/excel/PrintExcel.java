package lee.comm.excel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractExcelView;

@Service("printExcel")
public class PrintExcel extends AbstractExcelView{

	protected void buildExcelDocument(Map params, HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res) throws Exception {
		/** 사용자 정보 엑셀다운로드 */
		if (params.containsKey("userExcelList")) {
			userExcelList(params, workbook, req, res);
		}
		
	}
	
	private void userExcelList(Map params, HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res) {
		try {
			
			BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
			
			
			List usrmngList = (List)params.get("userExcelList");
			//String mm_dvs =(String)((Map) investXlsList.get(0)).get("mm_dvs").toString();
			
			String fileName = "사용자 목록.xls"; //StringUtil.urlEncode( mm_dvs + "_조사의뢰분" + ".xls", "UTF-8");	// 파일명 : 의원요구자료_주제어(소)_20130430.xls
			fileName = "userList.xls";
			
			res.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
			res.setHeader("Content-Transfer-Encoding", "binary");
			
			HSSFSheet sheet = workbook.createSheet("사용자목록");
			
			// 기본 스타일 (가운데 정렬)
			HSSFCellStyle bacis = workbook.createCellStyle();
			bacis.setBorderTop(HSSFCellStyle.BORDER_THIN);
			bacis.setBorderRight(HSSFCellStyle.BORDER_THIN);
			bacis.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			bacis.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			bacis.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			// 기본 스타일 (가운데 정렬, 글자 굵게)
			HSSFCellStyle basic_bold = workbook.createCellStyle();
			basic_bold.setBorderTop(HSSFCellStyle.BORDER_THIN);
			basic_bold.setBorderRight(HSSFCellStyle.BORDER_THIN);
			basic_bold.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			basic_bold.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			basic_bold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont basic_bold_font = workbook.createFont();
			basic_bold_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			basic_bold.setFont(basic_bold_font);
			
			// 제목(크기 12, 글자 굵게)
			HSSFCellStyle title_style = workbook.createCellStyle();
			title_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			title_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFFont title_bold_font = workbook.createFont();
			title_bold_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			title_bold_font.setFontHeightInPoints((short)14);
			title_style.setFont(title_bold_font);
			title_style.setWrapText(true);
			
			// 메뉴 스타일 (가운데 정렬, 글자 굵게, 배경 LIGHT_GREEN)			
			HSSFCellStyle menu_style = workbook.createCellStyle();
			menu_style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
			menu_style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
			menu_style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
			menu_style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
			menu_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			menu_style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			menu_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont menu_style_font = workbook.createFont();
			menu_style_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			menu_style.setFont(basic_bold_font);
			menu_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			menu_style.setWrapText(true);
			
			HSSFRow title =  sheet.createRow(0);
			 sheet.addMergedRegion(new Region(0, (short)0, 0, (short)8));
			 
			title.createCell(0).setCellValue("사용자 현황");
			title.createCell(1).setCellValue("사용자 현황");
			title.createCell(2).setCellValue("사용자 현황");
			title.createCell(3).setCellValue("사용자 현황");
			title.createCell(4).setCellValue("사용자 현황");
			title.createCell(5).setCellValue("사용자 현황");
			title.createCell(6).setCellValue("사용자 현황");
			title.createCell(7).setCellValue("사용자 현황");
			title.createCell(8).setCellValue("사용자 현황");
				
			title.getCell(0).setCellStyle(title_style);
			title.getCell(1).setCellStyle(title_style);
			title.getCell(2).setCellStyle(title_style);
			title.getCell(3).setCellStyle(title_style);
			title.getCell(4).setCellStyle(title_style);
			title.getCell(5).setCellStyle(title_style);
			title.getCell(6).setCellStyle(title_style);
			title.getCell(7).setCellStyle(title_style);
			title.getCell(8).setCellStyle(title_style);
			
			title =  sheet.createRow(1);
			
			title.createCell(0).setCellValue("이름");
			title.createCell(1).setCellValue("ID");
			title.createCell(2).setCellValue("성별");
			title.createCell(3).setCellValue("생일");
			title.createCell(4).setCellValue("주소");
			title.createCell(5).setCellValue("적립포인트");
			title.createCell(6).setCellValue("가입일자");
			title.createCell(7).setCellValue("연락처");
			title.createCell(8).setCellValue("권한");
				
			
			
			title.getCell(0).setCellStyle(basic_bold);
			title.getCell(1).setCellStyle(basic_bold);
			title.getCell(2).setCellStyle(basic_bold);
			title.getCell(3).setCellStyle(basic_bold);
			title.getCell(4).setCellStyle(basic_bold);
			title.getCell(5).setCellStyle(basic_bold);
			title.getCell(6).setCellStyle(basic_bold);
			title.getCell(7).setCellStyle(basic_bold);
			title.getCell(8).setCellStyle(basic_bold);
			
			if(usrmngList != null && usrmngList.size() > 0) {
				for(int i = 0 ; i < usrmngList.size(); i++) {
					HSSFRow row =  sheet.createRow(i+2);
					Map resultMap = (Map)usrmngList.get(i);

					row.createCell(0).setCellValue(resultMap.get("usr_nm").toString());
					
					if(resultMap.get("usr_id") != null) {
						row.createCell(1).setCellValue(resultMap.get("usr_id").toString());
					} else {
						row.createCell(1).setCellValue("");
					}
					
					if(resultMap.get("usr_sex") != null) {
						row.createCell(2).setCellValue(resultMap.get("usr_sex").toString());
					} else {
						row.createCell(2).setCellValue("");
					}
					
					if(resultMap.get("usr_birth") != null) {
						row.createCell(3).setCellValue(resultMap.get("usr_birth").toString());
					} else {
						row.createCell(3).setCellValue("");
					}
					
					if(resultMap.get("usr_addr") != null) {
						row.createCell(4).setCellValue(resultMap.get("usr_addr").toString());
					} else {
						row.createCell(4).setCellValue("");
					}
					
					if(resultMap.get("usr_point") != null) {
						row.createCell(5).setCellValue(resultMap.get("usr_point").toString());
					} else {
						row.createCell(5).setCellValue("");
					}
					
					if(resultMap.get("reg_date") != null) {
						row.createCell(6).setCellValue(resultMap.get("reg_date").toString());
					} else {
						row.createCell(6).setCellValue("");
					}
					
					if(resultMap.get("usr_hp") != null) {
						row.createCell(7).setCellValue(resultMap.get("usr_hp").toString());
					} else {
						row.createCell(7).setCellValue("");
					}
					
					if(resultMap.get("usr_auth_cd") != null) {
						row.createCell(8).setCellValue(resultMap.get("usr_auth_cd").toString());
					} else {
						row.createCell(8).setCellValue("");
					}
	
					
					
					row.getCell(0).setCellStyle(bacis);
					row.getCell(1).setCellStyle(bacis);
					row.getCell(2).setCellStyle(bacis);
					row.getCell(3).setCellStyle(bacis);
					row.getCell(4).setCellStyle(bacis);
					row.getCell(5).setCellStyle(bacis);
					row.getCell(6).setCellStyle(bacis);
					row.getCell(7).setCellStyle(bacis);
					row.getCell(8).setCellStyle(bacis);
				}
				
				// 컬럼 width값 자동조절
				for (int j = 0; j < 10; j++){
					sheet.autoSizeColumn((short)j);
					sheet.setColumnWidth(j, (sheet.getColumnWidth(j)) + 2048);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String msg ="엑셀파일 생성 및 다운로드에 실패하였습니다.";
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.write("<script type='text/javascript'>");
				out.write("		alert('"+msg+"');");
				out.write("		history.back(-1);");
				out.write("</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	

}
