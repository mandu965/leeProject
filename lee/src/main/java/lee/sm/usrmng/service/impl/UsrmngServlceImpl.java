package lee.sm.usrmng.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lee.sm.usrmng.service.UsrmngSearchVO;
import lee.sm.usrmng.service.UsrmngService;
import lee.sm.usrmng.service.UsrmngVO;

import org.springframework.stereotype.Service;



/*@Service('usrService') 할 경우
@Service("usrService")
bean id가 usrService 된다.*/
@Service("usrmngService") //여기서 이렇게 선언하면 Controller에서 usrService를 이용해서 Resource를 이용할수 있다.
public class UsrmngServlceImpl implements UsrmngService{
	
	@Resource(name="usrmngDAO") // 여기usrDAO는 @Repository에 등록된 것을 가져와서 선언해야 한다. 
	private UsrmngDAO usrmngDAO;

	public long usrmngCount(UsrmngSearchVO usrmngSearchVO) {
		
		return 0;
	}
	
	public List usrmngList(UsrmngSearchVO usrmngSearchVO) {
		return usrmngDAO.usrmngList(usrmngSearchVO);
	}
	
	public UsrmngVO usrmngView(long usr_no) {
		return usrmngDAO.usrmngView(usr_no);
	}

	public long usrmngMod(UsrmngVO usrmngVO) {
		return usrmngDAO.usrmngMod(usrmngVO);
	}

	public long usrmngAdd(UsrmngVO usrmngVO) {
		return usrmngDAO.usrmngAdd(usrmngVO);
	}
	
	public boolean usrmngDel(Map params){
		return usrmngDAO.usrmngDel(params);
	}
	
	public List userExcelList(Map params) {
		return usrmngDAO.userExcelList(params);
	}


	



}
