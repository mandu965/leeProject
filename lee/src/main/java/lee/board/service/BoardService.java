package lee.board.service;

import java.util.List;

public interface BoardService {
	
	//글등록
	long boardAdd(BoardVO boardVO) throws Exception;
	
	//게시판 리스트 
	List boardList(BoardSearchVO boardSearchVO) throws Exception;;
	
	//게시판 View   일련번호로 선택하기 
	BoardVO boardView(long blt_rsrc_sno) throws Exception;
	
	/** 게시글 수정 */
	boolean boardMod(BoardVO boardVO); 
	
	/** 게시글 삭제 */
	boolean boardDel(BoardVO boardVO);
	
	//조회수 증가 Count
	void cntCount(BoardVO boardVO) throws Exception;
	
	/** 코멘트 목록*/
	List cmntList(CmntVO cmntVO);
	
	/** 코멘트 등록 */
	long cmntAdd(CmntVO cmntVO);
	
	/** 코멘트 조회 */
	CmntVO cmntView(long cmnt_sno);
	
	/** 코멘트 수정 */
	boolean cmntMod(CmntVO cmntVO);
	
	/** 코멘트 삭제 */
	boolean cmntDel(long cmnt_sno);

}
