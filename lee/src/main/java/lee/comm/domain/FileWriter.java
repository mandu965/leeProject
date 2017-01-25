package lee.comm.domain;

import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileWriter{
	
	Logger logger = Logger.getLogger(getClass());
	
	private FileOutputStream fos;
	
	public String writeFile(MultipartFile file, String path, String fileName){
		
		logger.info("uploadPath========" + path);
		logger.info("fileName========" + fileName);
		logger.info("fullPathAndFileName========" + path + fileName);
		
		try{
			byte fileDate[] = file.getBytes();
			fos = new FileOutputStream(path + "\\" + fileName);
			fos.write(fileDate);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fos != null){
				try{
					fos.close();
				}catch(Exception e){
					
				}
			}
		}
	
		return path + fileName;
		
	}

}
