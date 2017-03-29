package lee.board.service.impl;

import java.util.List;

import lee.board.service.BoardSearchVO;
import lee.board.service.BoardVO;
import lee.board.service.CmntVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;


@Repository("boardDAO")
public class BoardDAO {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public long boardAdd(BoardVO boardVO){
		return (Long) sqlMapClientTemplate.insert(("lee.boardAdd"), boardVO);
	}
	
	//글개수
	public int boardCount(BoardSearchVO boardSearchVO){
		return (Integer) sqlMapClientTemplate.queryForObject("lee.boardCount", boardSearchVO);
	}
	
	public List boardList(BoardSearchVO boardSearchVO){
		
		return sqlMapClientTemplate.queryForList("lee.boardList", boardSearchVO);
	}
	
	public BoardVO boardView(long sno){
		
		return (BoardVO)sqlMapClientTemplate.queryForObject("lee.boardView", sno);
	}
	
	/** 게시글 수정 */
	public boolean boardMod(BoardVO vo) {
		return sqlMapClientTemplate.update("lee.boardMod", vo)>0;
	}
	
	/** 게시글 삭제 */
	public boolean boardDel(BoardVO vo) {
		return sqlMapClientTemplate.update("lee.boardDel", vo)>0;
	}
    
    public void cntCount(BoardVO boardVO){
    	 sqlMapClientTemplate.update(("lee.cntCount"), boardVO);
		return; 
	}
    
    /** 코멘트 목록*/
	public List cmntList(CmntVO cmntVO){
		return sqlMapClientTemplate.queryForList("lee.cmntList",cmntVO);
	}
	
	/** 코멘트 등록 */
	public long cmntAdd(CmntVO cmntVO){
		return (Long) sqlMapClientTemplate.insert("lee.cmntAdd", cmntVO);
	}
	
	/** 코멘트 조회 */
	public CmntVO cmntView(long cmnt_sno){
		Object obj = null;
		obj = (CmntVO)sqlMapClientTemplate.queryForObject("lee.cmntView", cmnt_sno);
		return obj == null ? null : (CmntVO)obj;
	}
	
	/** 코멘트 수정 */
	public boolean cmntMod(CmntVO cmntVO){
		return sqlMapClientTemplate.update("lee.cmntMod",cmntVO)>0;
	}
	
	/** 코멘트 삭제 */
	public boolean cmntDel(long cmnt_sno){
		return sqlMapClientTemplate.update("lee.cmntDel",cmnt_sno)>0;
	}	

}
