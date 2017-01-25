package lee.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lee.main.service.MainService;

import org.springframework.stereotype.Service;

@Service("mainService")
public class MainServiceImpl implements MainService{
	
	@Resource(name="mainDAO")
	private MainDAO mainDAO;
	
	/** 메인 공지사항 목록 */
	public List mainNoticeList() {
		return mainDAO.mainNoticeList();
	}

}
