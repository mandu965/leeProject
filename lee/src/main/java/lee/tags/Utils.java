package lee.tags;

import java.io.File;

import javax.servlet.jsp.PageContext;

public class Utils {
	
	/** 파일 유형 아이콘 파일명 */
	public static String extensionIcon(PageContext ctx, String basePath, String fileName) {
		return file2img(ctx.getServletContext().getRealPath(basePath), fileName.toLowerCase());
	}
	
	/** 파일사이즈 문자열 */
	public static String attachSizeStr(long file_sz_byte){
		try{
			if(file_sz_byte > 1099511627776.0){
				return Double.toString(convertUnit(file_sz_byte, "t", 4)) + " TB";
			}else if(file_sz_byte > 1073741824.0){
				return Double.toString(convertUnit(file_sz_byte, "g", 3)) + " GB";
			}else if(file_sz_byte > 1048576.0){
				return Double.toString(convertUnit(file_sz_byte, "m", 2)) + " MB";
			}else if(file_sz_byte > 1024.0){
				return Double.toString(convertUnit(file_sz_byte, "k", 1)) + " KB";
			}else{
				return ((long) convertUnit(file_sz_byte, "b", 0)) + " bytes";
			}
		}catch(Exception e){
			System.out.println("exp:"+e.getMessage());
			return "";
		}
	}
	
	
	
	public static double convertUnit(long fileSize, String unit, int point)
	  {
	    if ((unit == null) || (fileSize < 1L)) {
	      return 0.0D;
	    }
	    unit = unit.toLowerCase();

	    double value = 0.0D;
	    if (unit.equals("b"))
	      value = fileSize;
	    else if (unit.equals("k"))
	      value = fileSize / 1024.0D;
	    else if (unit.equals("m"))
	      value = fileSize / 1048576.0D;
	    else if (unit.equals("g"))
	      value = fileSize / 1073741824.0D;
	    else if (unit.equals("t"))
	      value = fileSize / 1099511627776.0D;
	    else {
	      value = 0.0D;
	    }

	    if (point > 0) {
	      value = Math.round(value * Math.pow(10.0D, point)) / Math.pow(10.0D, point);
	    }
	    return value;
	  }
	
	 public static String file2img(String basePath, String fileName)
	  {
	    String extension = null;
	    if (fileName.lastIndexOf('.') > 0) {
	      extension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
	      File f = new File(basePath + "/" + extension + ".gif");
	      if (f.isFile()) {
	        return extension + ".gif";
	      }
	      return "unknown.gif";
	    }

	    return "unknown.gif";
	  }

}
