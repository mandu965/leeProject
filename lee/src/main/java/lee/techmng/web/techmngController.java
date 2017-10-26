package lee.techmng.web;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import lee.sm.usrmng.web.UsrmngController;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class techmngController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsrmngController.class);
	
	//java ��¥ ���
	public void dateCal() throws ParseException{
		
		String testDate = "2016-04-21";
		//String testDate = "20160421";
		
        SimpleDateFormat  formatter01 = new SimpleDateFormat("yyyy�� MM�� dd�� hh�� mm�� ss��");
        SimpleDateFormat  formatter02 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat  formatter03 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat  formatter04 = new SimpleDateFormat("yyyy-MM-dd");
        
        //���� ��¥ ���ϱ�
        String todate01=  formatter01.format(new Date());
        String todate02 =  formatter02.format(new Date());
        String todate03 =  formatter03.format(new Date());
        String todate04 =  formatter04.format(new Date());
        
        logger.info("����ð� ����� = " + todate01);
        logger.info("����ð� ����� = " + todate02);
        logger.info("����ð� ����� = " + todate03);
        
       Date todate_date = formatter04.parse(todate04);
        Date test_date = formatter04.parse(testDate);
        long diff = todate_date.getTime() - test_date.getTime();
        // �ð����̸� �ð�,��,�ʸ� ���� ������ ������ �Ϸ� ������ ����
     	long diffDays = diff / (24 * 60 * 60 * 1000);

		logger.info(todate_date + "====== " + test_date);
		logger.info("��¥������� : " + diff);
		logger.info("������ : " + diffDays);
		logger.info("������ : " + diffDays / 365);
        
       
      /* Date todate_date =  formatter03.parse(todate03);
        Date test_date =  formatter03.parse(testDate);
       
		logger.info(todate_date + "====== " + test_date);
		long diff = todate_date.getTime() - test_date.getTime();
		// �ð����̸� �ð�,��,�ʸ� ���� ������ ������ �Ϸ� ������ ����
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		logger.info("��¥������� : " + diff);
		logger.info("������ : " + diffDays);
		logger.info("������ : " + diffDays / 365);*/

		

		
		
		return;
	}
	
	private static COSDocument parseDocument(RandomAccessRead is) throws IOException {   
        PDFParser parser = new PDFParser(is);
       
        parser.parse();   
        return parser.getDocument();   
    }   

	
	@RequestMapping(value = "/techmng/tech01.do")
	public String tech01View(HttpServletRequest req, ModelMap modelMap) throws Exception {
		//���۰����� �̿��Ͽ� ���� ������
		/*JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername("junghun350@gmail.com");
        sender.setPassword("wjdqls14");
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        sender.setJavaMailProperties(prop);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo("leejh965@naver.com");
        helper.setText("Thank you for ordering!");
        sender.send(message);*/
		
		Map param = new HashMap();
		//HashMap reference Test
		/*List<Map> testList = new ArrayList(); 
		
		for(int i=0; i<5;i++){
			param = new HashMap();
			param.put("one", i);
			param.put("two", i);
			param.put("three", i);
			param.put("four", i);
			param.put("five", i);
			
			testList.add(param);
		}
		
		for(int i=0; i<testList.size(); i++){
			logger.info("--------"+i+"��°----------------------------------------");
			logger.info("@@@@@@@@@@@@" + testList.get(i).get("one").toString());
			logger.info("@@@@@@@@@@@@" + testList.get(i).get("two").toString());
			logger.info("@@@@@@@@@@@@" + testList.get(i).get("three").toString());
			logger.info("------------------------------------------------");
		}*/
		
		
		
		/*param.put("key01", "value01");
		param.put("key02", "value02");
		param.put("key03", "value03");
		param.put("key04", "value04");
		param.put("key05", "value05");
		
		Set key = param.keySet();
		
		for (Iterator iterator = key.iterator(); iterator.hasNext();) {
            String keyName = (String) iterator.next();
            String valueName = (String) param.get(keyName);
            

            System.out.println(keyName +" = " +valueName);
		}
		
		
		ArrayList testList = new ArrayList();
		
		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.add(5);
		
		Iterator iter = testList.iterator();
		
		while(iter.hasNext()){
			System.out.println("@@@@@@@@" + iter.next());
		}
*/

		/*File fileT = new File("D:\\www\\click\\files\\request\\test.pdf");
		//pdf text 추출
		String text = null;
		COSDocument cosDoc = null;   
		PDDocument pdDocument = null;
		try{   
            
			File file  = new File("D:\\www\\click\\files\\request\\test.pdf");
			pdDocument = PDDocument.load(file);
			if (!pdDocument.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();
                text= Tstripper.getText(pdDocument);
              
            }
			   pdDocument.close();
 
            System.out.println(text);   
        }catch(IOException e){   
            e.printStackTrace();   
        }finally{
        	
        }*/

		
		File a = new File("E:\\www\\ktg\\kiss\\test.tif");
		// extractingText(fileT); //pdf -> txt 파일 전환
		
		 File inputFile = new File("E:\\www\\ktg\\kiss\\test.tif");
		  File outputFile = new File("E:\\www\\ktg\\kiss\\test.bmp");
		  
		  String str_TiffFileUrl = "E:\\www\\ktg\\kiss\\test.tif";
		  String str_JPGFileUrl= "E:\\www\\ktg\\kiss\\test.jpg";
		 // ConvertTiffToJpg(str_TiffFileUrl, str_JPGFileUrl);
		 
		  File inFile = new File("E:\\www\\ktg\\kiss\\test.tif");
		  File outFile = new File("E:\\www\\ktg\\kiss\\test.bmp");

		  BufferedImage image = ImageIO.read(inFile);
		  BufferedImage convertedImage = new BufferedImage(image.getWidth(), 
		      image.getHeight(), BufferedImage.TYPE_INT_RGB);
		  convertedImage.createGraphics().drawRenderedImage(image, null);
		  ImageIO.write(convertedImage, "jpg", outFile);
		return "/techmng/tech01";
	}
	
	public static boolean extractingText(File targetFile) {
        
        boolean result = true;
        String tttt = targetFile.getPath();
        logger.info("####################" + tttt);
        tttt = tttt.replaceAll("pdf", "txt");
        File tfile = new File(tttt);
        String extractTxtFile = tfile.getPath();
       
        PDDocument pdDocument = null;
        try {
               
                pdDocument = PDDocument.load(targetFile);
                pdDocument.getClass();
                if (!pdDocument.isEncrypted()) {
                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.setSortByPosition(true);
                    PDFTextStripper Tstripper = new PDFTextStripper();
                    String st = Tstripper.getText(pdDocument);
                    System.out.println("Text:" + st);
                }

               
        } catch (IOException e) {
                e.printStackTrace();
        }
       
        FileOutputStream fileOutputStream = openOutputStream(new File(extractTxtFile));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
       
        BufferedWriter bufferedWriter = null;
        bufferedWriter = new BufferedWriter(outputStreamWriter);
       
        PDFTextStripper stripper = null;
        try {
               
                stripper = new PDFTextStripper();
               
        } catch (IOException e) {
               
                System.out.println("TextExtraction-extractingText ERROR: PDFTextStripper 객체생성 실패");
        }
       
        stripper.setStartPage(1);
        //stripper.setEndPage(11);
        try {
               
                stripper.writeText(pdDocument, bufferedWriter);
               
        } catch (IOException e) {
               
                System.out.println("TextExtraction-extractingText ERROR: text 추출 실패");
        }
       
        try {
               
                pdDocument.close();
               
        } catch (IOException e) {
               
                System.out.println("TextExtraction-extractingText ERROR: PDDocument close 실패");
        }
       
        IOUtils.closeQuietly(bufferedWriter);
       
        return result;
 }

 public static FileOutputStream openOutputStream(File file) {
       
        FileOutputStream fileOutputStream = null;

        try {
               
                fileOutputStream = new FileOutputStream(file);
               
        } catch (Exception e) {
               
                System.out.println("TextExtraction-openOutputStream ERROR: " + e.getMessage());
        }
       
        return fileOutputStream;
 }

	
	public void readFileName(String parentPath) {
	    File file = new File(parentPath);
	    String[] fnameList = file.list();
	    int fCnt = fnameList.length;
	    String childPath = "";
	    
	    for(int i = 0; i < fCnt; i++) {
	      childPath = parentPath+"/"+fnameList[i];
	      File f = new File(childPath);
	      if( ! f.isDirectory()) {
	        f.delete();   //파일이면 바로 삭제
	        System.out.println(f.getName()+"###########");
	      }
	      else {
	    	  readFileName(childPath);
	      }
	    }
	    
	  }
	
	private static JavaMailSenderImpl getSender(){
	    JavaMailSenderImpl sender = new JavaMailSenderImpl();
	    sender.setHost("");
	    sender.setUsername("");
	    sender.setPassword("");
	     
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	     
	    sender.setJavaMailProperties(props);
	    return sender;
	}
	
	@RequestMapping(value = "/techmng/tech02.do")
	public String tech02View(HttpServletRequest req, ModelMap modelMap) throws Exception {
		
		

		return "/techmng/tech02";
	}
	
	@RequestMapping(value = "/techmng/html5Tag.do")
	public String html5TagView(HttpServletRequest req, ModelMap modelMap) throws Exception {
		
		return "/techmng/html5Tag";
	}
	
	@RequestMapping(value = "/techmng/mobileImg.do")
	public String mobileImg(HttpServletRequest req, ModelMap modelMap) throws Exception {
		
		return "/techmng/mobileImg";
	}
	
	@RequestMapping(value = "/techmng/pdfCreate.do")
	public String pdfCreate(HttpServletRequest req, ModelMap modelMap) throws Exception {
		/*String fileName="";
		String dir="D:/www/kipes/kipes/report/report";
		fileName = "simple_table.pdf";
		
		File directory = new File(dir);
		if(!directory.exists()) directory.mkdirs(); //파일경로 없으면 생성
		
		 Document document = new Document();
	     PdfWriter.getInstance(document, new FileOutputStream(dir+"/"+fileName));
	      
	      document.open();
	      PdfPTable table = new PdfPTable(4);

	      for(int i = 0; i < 16; i++){
	    	  table.addCell("cellNumber:" + i);
	      }
	      document.add(table);
	      document.close();*/

		return "/techmng/tech02";
	}
	public static void ConvertTiffToJpg(String str_TiffUrl, String str_JpgFileDestinationUrl) throws Exception
	  {
	    /*try
	    {
	      FileSeekableStream obj_FileSeekableStream = new FileSeekableStream(new File(str_TiffUrl));
	      ImageDecoder obj_ImageDecoder = ImageCodec.createImageDecoder("tiff", obj_FileSeekableStream, null);
	      RenderedImage obj_RenderedImage = obj_ImageDecoder.decodeAsRenderedImage();
	      JAI.create("filestore",obj_RenderedImage,str_JpgFileDestinationUrl, "jpeg");
	      obj_RenderedImage = null;
	      obj_ImageDecoder = null;
	      obj_FileSeekableStream.close();
	    }
	    catch(Exception ex)
	    {
	      throw ex;
	    }*/
	 
	  }
}
