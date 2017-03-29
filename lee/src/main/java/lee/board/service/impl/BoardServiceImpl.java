package lee.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lee.board.service.BoardSearchVO;
import lee.board.service.BoardService;
import lee.board.service.BoardVO;
import lee.board.service.CmntVO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;


@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource(name ="boardDAO")
	private BoardDAO boardDAO;

	@Override
	public long boardAdd(BoardVO boardVO) throws Exception {
		boardVO.setDel_yn("N"); //등록시에는 삭제 N
		return boardDAO.boardAdd(boardVO);
	}

	
	//글개수
	public int boardCount(BoardSearchVO boardSearchVO){
		return boardDAO.boardCount(boardSearchVO);
	}

	@Override
	public List boardList(BoardSearchVO boardSearchVO) throws Exception {
		return boardDAO.boardList(boardSearchVO);
	}

	@Override
	public BoardVO boardView(long blt_rsrc_sno) throws Exception {
		return boardDAO.boardView(blt_rsrc_sno);
	}
	
	/** 게시글 수정 */
	public boolean boardMod(BoardVO boardVO) {
		return boardDAO.boardMod(boardVO);
	}
	
	/** 게시글 삭제 */
	public boolean boardDel(BoardVO boardVO) {
		return boardDAO.boardDel(boardVO);
	}

	@Override
	public void cntCount(BoardVO boardVO) throws Exception {
		boardVO.setBbs_cnt(boardVO.getBbs_cnt()+1);
		boardDAO.cntCount(boardVO);
		return;
		
	}
	
	/** 코멘트 목록*/
	public List cmntList(CmntVO cmntVO) {
		return boardDAO.cmntList(cmntVO);
	}

	/** 코멘트 등록 */
	public long cmntAdd(CmntVO cmntVO) {
		return boardDAO.cmntAdd(cmntVO);
	}
	
	/** 코멘트 조회 */
	public CmntVO cmntView(long cmnt_sno) {
		return boardDAO.cmntView(cmnt_sno);
	}

	/** 코멘트 수정*/
	public boolean cmntMod(CmntVO cmntVO) {
		return boardDAO.cmntMod(cmntVO);
	}
	
	/** 코멘트 삭제 */
	public boolean cmntDel(long cmnt_sno) {
		return boardDAO.cmntDel(cmnt_sno);
	}

}
