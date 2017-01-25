package lee.library;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	public static long getLong(HttpServletRequest req, String val){
		long result=0;
		
		if(req.getParameter(val) != null){
			result = Long.parseLong(req.getParameter(val));
		}else{
			result=0;
		}
		//result = (req.getParameter(val) != null)?Long.getLong(req.getParameter(val)):0; 
		return result;
	}
	
	public static String getString(HttpServletRequest req, String val){
		String result="";
		result = (req.getParameter(val) != null?req.getParameter(val):""); 
		return result;
	}

}
