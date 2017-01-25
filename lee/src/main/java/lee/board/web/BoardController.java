package lee.board.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lee.LeeCodeConstants;
import lee.board.service.BoardSearchVO;
import lee.board.service.BoardService;
import lee.board.service.BoardVO;
import lee.board.service.CmntVO;
import lee.comm.attach.service.AttachService;
import lee.comm.attach.service.AttachVO;
import lee.login.web.LoginManager;
import lee.test.spring.aop.service.CustomerService;
import lee.test.spring.bean.PrototypeBean;
import lee.test.spring.bean.RequestBean;
import lee.test.spring.bean.SessionBean;
import lee.test.spring.bean.SingletonBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;



@Controller
public class BoardController {
	

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "boardService")
	BoardService boardService;
	
	@Resource(name = "attachService")
	AttachService attachService;
	
	/*@Autowired
	private RequestBean requestBean01;
	
	@Autowired
	private RequestBean requestBean02;
	
	@Autowired
	private SessionBean sessionBean01;
	
	@Autowired
	private SessionBean sessionBean02;
	
	@Autowired
	private SingletonBean singletonBean01;
	
	@Autowired
	private SingletonBean singletonBean02;
	
	@Autowired
	private PrototypeBean prototypeBean01;
	
	@Autowired
	private PrototypeBean prototypeBean02;*/
	
	@RequestMapping(value = "/board/**/boardList.do") 
	public String boarderList(HttpServletRequest req, ModelMap modelMap, @ModelAttribute("boardSearch") BoardSearchVO boardSearchVO) throws Exception{
		/*System.out.println(singletonBean01);
		System.out.println(singletonBean02);
		
		System.out.println(prototypeBean01);
		System.out.println(prototypeBean02);
		
		System.out.println(requestBean01);
		System.out.println(requestBean02);
		
		System.out.println(sessionBean01);
		System.out.println(sessionBean02);*/
		
		String uri =req.getRequestURI(); 
		uri = uri.replace("/lee", "");   
		String jsp_path =uri.substring(0, uri.lastIndexOf("/")+1);

		long bbs_sno = boardSearchVO.getBbs_sno();
		System.out.println("bbs_sno====" + bbs_sno);
		
		List boardList = boardService.boardList(boardSearchVO);
		
		
		///////paging : S//////////////////////////////
		
		int pageSize = boardSearchVO.getPageSize();
		int pageIndex = boardSearchVO.getPageIndex();
		int pageGroupSize = boardSearchVO.getPageGroupSize();
		

		int startRow = (pageIndex - 1) * pageSize;// 한 페이지의 시작글 번호
		int endRow = pageIndex * pageSize;// 한 페이지의 마지막 글번호
		int count = boardList.size();
		
		System.out.println("pageIndex====" + pageIndex);
		System.out.println("pageSize====" + pageSize);
		System.out.println("pageGroupSize====" + pageGroupSize);
		System.out.println("count====" + count);
		System.out.println("startRow====" + startRow);
		System.out.println("endRow====" + endRow);
		
		// 페이지그룹의 갯수
		// ex) pageGroupSize가 3일 경우 '[1][2][3]'가 pageGroupCount 개 만큼 있다.
		int pageGroupCount = count / (pageSize * pageGroupSize) + (count % (pageSize * pageGroupSize) == 0 ? 0 : 1);
		//                 = 24    /         10*2               +   24   % 20 = 0 ? 0: 1     
		
		System.out.println("pageGroupCount====" + pageGroupCount);
		
		
		
		// 페이지 그룹 번호
		// ex) pageGroupSize가 3일 경우 '[1][2][3]'의 페이지그룹번호는 1 이고 '[2][3][4]'의
		// 페이지그룹번호는 2 이다.
		int numPageGroup = (int) Math.ceil((double) pageIndex / pageGroupSize);
		System.out.println("numPageGroup====" + numPageGroup);
		

		ArrayList articleList = new ArrayList();

		if (count > 0) {
			if (endRow > count) //ex) count가 19일 경우 endRow를 19로 마춘다
				endRow = count;
			for (int i = startRow; i < endRow; i++) {
				articleList.add(boardList.get(i));
			}

		} else {
			articleList = null;
		}
		
		modelMap.put("pageIndex", pageIndex);
		modelMap.put("pageSize", pageSize);
		modelMap.put("count", count);
		modelMap.put("pageGroupSize", pageGroupSize);
		modelMap.put("numPageGroup", numPageGroup);
		modelMap.put("pageGroupCount", pageGroupCount);
		modelMap.put("articleList", articleList);
		///////paging : E//////////////////////////////
		
		modelMap.put("bbs_sno", bbs_sno);
		
		return jsp_path+"boardList";
	}
	
	@RequestMapping(value = "/board/**/boardAdd.do",  method = RequestMethod.GET)
	public String boarderAdd(HttpServletRequest req, Locale locale, @ModelAttribute("boardVO") BoardVO boardVO, Model model) {
		String uri =req.getRequestURI(); //    /lee/board/notice/list.do
		uri = uri.replace("/lee", "");
		String jsp_path =uri.substring(0, uri.lastIndexOf("/")+1); //  /board/notice/
		
		System.out.println("boardVO.getBbs_sno()====" + boardVO.getBbs_sno());

		return jsp_path+"boardAdd";
	}
	@RequestMapping(value = "/board/**/boardAdd.do",  method = RequestMethod.POST)
	public String boarderAddPro(HttpServletRequest req, @ModelAttribute("boardVO") BoardVO boardVO, Model model, HttpSession session) throws Exception {
		String uri =req.getRequestURI(); //    /lee/board/notice/list.do
		uri = uri.replace("/lee", "");
		String jsp_path =uri.substring(0, uri.lastIndexOf("/")+1); //  /board/notice/
		System.out.println("boardAdd, jsp_path=====" + jsp_path);

		long bbs_sno = boardVO.getBbs_sno();

		boardVO.setReg_usr_no(LoginManager.userNo(req));
		
		//공지방법 팝업날짜 디폴드 값
		if(boardVO.getMain_ntc_yn()==null){boardVO.setMain_ntc_yn("N");};
		if(boardVO.getNtc_yn()==null){boardVO.setNtc_yn("N");};
		
		//팝업 날짜 저장
		if(boardVO.getMain_ntc_yn()!=null){
			if(boardVO.getMain_ntc_yn().equals("Y")){
				String popup_stt_dt=boardVO.getPopup_stt_dt();
				String popup_end_dt=boardVO.getPopup_end_dt();
		
				popup_end_dt = popup_end_dt.substring(0,4) + popup_end_dt.substring(5,7) + popup_end_dt.substring(8);
				popup_stt_dt = popup_stt_dt.substring(0,4) + popup_stt_dt.substring(5,7) + popup_stt_dt.substring(8);
						
				boardVO.setPopup_stt_dt(popup_stt_dt);
				boardVO.setPopup_end_dt(popup_end_dt);
				
				//width 기본값:450
				if(boardVO.getPopup_width()==null || boardVO.getPopup_width()==""){
					boardVO.setPopup_width("450");
				 }	
			}
		}
		
		String webView ="";

		long a =0;
		
		long atch_file_sno = attachService.attachAdd(req, 0, LeeCodeConstants.BOARD, 0, "N");
		
		boardVO.setAtch_file_sno(atch_file_sno);
		a = boardService.boardAdd(boardVO);
		
		if(a>0){
			System.out.println("성공");
			//지금 경로의 boardList로 가게해야 한다.
			webView = "redirect:"+jsp_path + "boardList.do?bbs_sno="+bbs_sno;
		}
		else{
			System.out.println("실패");
			webView = jsp_path+"boardAdd";
		}
		
		return webView;
	}
	
	@RequestMapping(value = "/board/**/boardview.do",  method = RequestMethod.GET)
	public String boarderView(HttpServletRequest req, HttpSession reqSession, ModelMap modelMap,  @ModelAttribute("boardVO") BoardVO boardVO) throws Exception {
		String uri =req.getRequestURI(); //    /lee/board/notice/list.do
		uri = uri.replace("/lee", "");
		String jsp_path =uri.substring(0, uri.lastIndexOf("/")+1); //  /board/notice/
		
		String blt_rsrc_sno = "";
		
		long blt_rsrc_sno_number=0;
		blt_rsrc_sno = req.getParameter("blt_rsrc_sno");
		blt_rsrc_sno_number = Integer.parseInt(blt_rsrc_sno);
		
		BoardVO vo = boardService.boardView(blt_rsrc_sno_number); //view 얻어오기
		boardService.cntCount(vo); //조회수 증가
		
		//첨부파일
		List<AttachVO> atchFileList = attachService.attachList(vo.getAtch_file_sno(),"N");
		if(atchFileList!=null && atchFileList.size()>0) modelMap.put("atchFileList", atchFileList);
		
		modelMap.put("boardVO", vo);
		modelMap.put("blt_rsrc_sno", blt_rsrc_sno);

		return jsp_path+"boardView";
	}
	
	/** 게시글 수정화면 */
	@RequestMapping(value="/board/**/boardMod.do" ,method = RequestMethod.GET)
	public String modForm(HttpServletRequest req, @ModelAttribute("boardSearchForm")BoardVO boardVO, ModelMap modelMap,SessionStatus status) throws Exception {
		
		String uri = req.getRequestURI();
		uri = uri.replace("/lee", "");
		String jsp_path =uri.substring(0, uri.lastIndexOf("/")+1);
		
		boardVO = boardService.boardView(boardVO.getBlt_rsrc_sno());
		
		if(boardVO.getPopup_stt_dt()!=null && boardVO.getPopup_end_dt()!=null){
			String popup_stt_dt = boardVO.getPopup_stt_dt();
			String popup_end_dt = boardVO.getPopup_end_dt();
			
			popup_stt_dt = popup_stt_dt.substring(0, 4) + "-" + popup_stt_dt.substring(4, 6) +"-" + popup_stt_dt.substring(6, 8);
			popup_end_dt = popup_end_dt.substring(0, 4) + "-" + popup_end_dt.substring(4, 6) +"-" + popup_end_dt.substring(6, 8);
			
			boardVO.setPopup_stt_dt(popup_stt_dt);
			boardVO.setPopup_end_dt(popup_end_dt);
		}
		
		List<AttachVO> atchFileList = attachService.attachList(boardVO.getAtch_file_sno(), "N");
		if(atchFileList != null && atchFileList.size() > 0) modelMap.put("atchFileList", atchFileList);
		
		modelMap.put("boardVO",boardVO);
		
		return jsp_path+"boardMod";
	}
	/** 게시글 수정 프로세스 */
	@RequestMapping(value="/board/**/boardMod.do", method = RequestMethod.POST)
	public String modFormSubmit(HttpServletRequest req, @ModelAttribute("boardVO")BoardVO boardVO, ModelMap modelMap,SessionStatus status) throws Exception {
		
		String uri = req.getRequestURI();
		uri = uri.replace("/lee", "");
		String jsp_path = uri.substring(0, uri.lastIndexOf("/")+1);
		
		if(boardVO.getNtc_yn() == null) boardVO.setNtc_yn(LeeCodeConstants.NO);
		if(boardVO.getMain_ntc_yn() == null) boardVO.setMain_ntc_yn(LeeCodeConstants.NO);
		
		long atch_file_sno = attachService.attachAdd(req, boardVO.getAtch_file_sno(), LeeCodeConstants.BOARD, boardVO.getBlt_rsrc_sno(), "N");
		boardVO.setAtch_file_sno(atch_file_sno);
		
		//팝업 날짜 저장
		if(boardVO.getPopup_stt_dt()!=null && boardVO.getPopup_end_dt()!=null){
			String popup_stt_dt=boardVO.getPopup_stt_dt();
			String popup_end_dt=boardVO.getPopup_end_dt();
	
			popup_end_dt = popup_end_dt.substring(0,4) + popup_end_dt.substring(5,7) + popup_end_dt.substring(8);
			popup_stt_dt = popup_stt_dt.substring(0,4) + popup_stt_dt.substring(5,7) + popup_stt_dt.substring(8);
					
			boardVO.setPopup_stt_dt(popup_stt_dt);
			boardVO.setPopup_end_dt(popup_end_dt);
		}
		
		if(boardVO.getMain_ntc_yn().equals("Y")){
			if(boardVO.getPopup_width()==null || boardVO.getPopup_width()==""){
				boardVO.setPopup_width("450");
			 }	
		}
		
		//long atch_file_sno = attachService.attachAdd(req, boardVO.getAtch_file_sno(), boardVO.getTech_sno_cd(), 0, "Y");
		
		//boardVO.setAtch_file_sno(atch_file_sno);
		//boardVO.setMod_usr_no(LoginManager.userNo(req));
		boolean blt_rsrc_sno = boardService.boardMod(boardVO);
		
		modelMap.put("boardVO",boardVO);
		
		//modelMap.put("tech_sno_cd", boardSearchVO.getSh_tech_sno_cd());
		
		/*if(blt_rsrc_sno==true){
			SessionMessage.setMessage(req, messageSource.getMessage("click.comm.crud.success", new String[]{"게시글","수정"}, Locale.KOREAN ));	
		}else{
			SessionMessage.setMessage(req, messageSource.getMessage("click.comm.crud.fail", new String[]{"게시글","수정"}, Locale.KOREAN ));
			status.setComplete();
			return jsp_path+"boardMod";
		}*/
		
		return "redirect:"+jsp_path + "boardList.do?bbs_sno="+boardVO.getBbs_sno();
	}
	
	/** 게시글 삭제 */
	@RequestMapping(value="/board/**/boardDel.do",method = RequestMethod.POST)
	public String DelForm(HttpServletRequest req, @ModelAttribute("boardVO")BoardVO boardVO, ModelMap modelMap,SessionStatus status) throws Exception {
		
		String uri = req.getRequestURI();
		uri = uri.replace("/lee", "");
		String jsp_path = uri.substring(0, uri.lastIndexOf("/")+1); 
		
		boolean blt_rsrc_sno = boardService.boardDel(boardVO);
		
		/*if(blt_rsrc_sno==true){
			SessionMessage.setMessage(req, messageSource.getMessage("click.comm.crud.success", new String[]{"게시글","삭제"}, Locale.KOREAN ));	
		}else{
			SessionMessage.setMessage(req, messageSource.getMessage("click.comm.crud.fail", new String[]{"게시글","삭제"}, Locale.KOREAN ));
			status.setComplete();
			return jsp_path+"boardView";
		}*/
		System.out.println(boardVO.getBlt_rsrc_sno()+"######################" + boardVO.getBbs_sno());
		return "redirect:"+jsp_path + "boardList.do?bbs_sno="+boardVO.getBbs_sno();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	/** 코멘트 목록 */
	@RequestMapping(value="/ajax/comm/cmntList.do", method= RequestMethod.POST) 
	public String cmntList(HttpServletRequest req,@ModelAttribute("cmntVO")CmntVO cmntVO ,ModelMap modelMap,SessionStatus status) throws Exception{
		
		String blt_rsrc_sno = req.getParameter("blt_rsrc_sno");
		String cmnt_contents = req.getParameter("cmnt_cntn");
		String gubun = req.getParameter("gubun");

		cmntVO.setBlt_rsrc_sno(Long.parseLong(blt_rsrc_sno));
		
		if(cmnt_contents!=null && cmnt_contents.length()!=0 && gubun.equals("Add")){
			cmntVO.setCmnt_contents(cmnt_contents);
			cmntVO.setReg_usr_no(LoginManager.userNo(req));
			long cmnt_sno=boardService.cmntAdd(cmntVO);
		}

		List cmntList=boardService.cmntList(cmntVO);
		
		modelMap.put("cmntList", cmntList);
		modelMap.put("loginUsrNo", LoginManager.userNo(req));
		
		return "comm/ajax/cmntList";
	}
	
	/** 코멘트 수정화면 */
	@RequestMapping(value="/ajax/comm/cmntMod.do", method= RequestMethod.POST) 
	public String cmntMod(HttpServletRequest req,@ModelAttribute("cmntVO")CmntVO cmntVO ,ModelMap modelMap,SessionStatus status) throws Exception{

		String cmnt_sno = req.getParameter("cmnt_sno");
		cmntVO = boardService.cmntView(Long.parseLong(cmnt_sno));
		
		modelMap.put("cmntVO", cmntVO);
		
		return "comm/ajax/cmntMod";
	}
	
	/** 코멘트 수정 프로세스 */
	@RequestMapping(value="/comm/**/**/cmntModPro.do", method= RequestMethod.POST) 
	public String cmntModPro(HttpServletRequest req,@ModelAttribute("cmntVO")CmntVO cmntVO ,ModelMap modelMap,SessionStatus status) throws Exception{
		
		String uri = req.getRequestURI();
		uri = uri.replace("/lee/comm/", "");
		String jsp_path = uri.substring(0, uri.lastIndexOf("/")+1); 
		
		//String sh_tech_sno_cd =req.getParameter("sh_tech_sno_cd");
System.out.println(cmntVO.getCmnt_sno()+cmntVO.getCmnt_contents()+"###############################1" + jsp_path);
		boolean result= boardService.cmntMod(cmntVO);
		
		/*if(result){
			SessionMessage.setMessage(req, messageSource.getMessage("click.comm.crud.success", new String[]{"코멘트","수정"}, Locale.KOREAN ));	
		}else{
			SessionMessage.setMessage(req, messageSource.getMessage("click.comm.crud.fail", new String[]{"코멘트","수정"}, Locale.KOREAN ));
			status.setComplete();
		}*/
		//+cmntVO.getblt_rsrc_sno();
		return "redirect:"+jsp_path+"boardview.do?blt_rsrc_sno="+cmntVO.getBlt_rsrc_sno();
	}
	
	/** 코멘트 삭제 */
	@RequestMapping(value="/comm/**/**/cmntDel.do", method= RequestMethod.POST) 
	public String cmntDel(HttpServletRequest req,@ModelAttribute("cmntVO")CmntVO cmntVO ,ModelMap modelMap,SessionStatus status) throws Exception{
		
		String uri = req.getRequestURI();
		uri = uri.replace("/lee/comm/", "");
		String jsp_path = uri.substring(0, uri.lastIndexOf("/")+1); 
		
		boolean result = boardService.cmntDel(cmntVO.getCmnt_sno());
		System.out.println("cmntVO.getBlt_rsrc_sno()===============" +cmntVO.getBlt_rsrc_sno());
		/*if(result){
			SessionMessage.setMessage(req, messageSource.getMessage("click.comm.crud.success", new String[]{"코멘트","삭제"}, Locale.KOREAN ));	
		}else{
			SessionMessage.setMessage(req, messageSource.getMessage("click.comm.crud.fail", new String[]{"코멘트","삭제"}, Locale.KOREAN ));
			status.setComplete();
		}*/
		return "redirect:"+jsp_path+"boardview.do?blt_rsrc_sno="+cmntVO.getBlt_rsrc_sno();
	}
}
