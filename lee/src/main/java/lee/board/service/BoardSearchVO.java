package lee.board.service;

import lee.comm.domain.ListPagingVO;

public class BoardSearchVO extends ListPagingVO{
	
	private String sh_title;
	private String sh_reg_usr;
	private String sh_id;
	private long bbs_sno;
	
	public String getSh_title() {
		return sh_title;
	}
	public void setSh_title(String sh_title) {
		this.sh_title = sh_title;
	}
	public String getSh_reg_usr() {
		return sh_reg_usr;
	}
	public void setSh_reg_usr(String sh_reg_usr) {
		this.sh_reg_usr = sh_reg_usr;
	}
	public String getSh_id() {
		return sh_id;
	}
	public void setSh_id(String sh_id) {
		this.sh_id = sh_id;
	}
	public long getBbs_sno() {
		return bbs_sno;
	}
	public void setBbs_sno(long bbs_sno) {
		this.bbs_sno = bbs_sno;
	}
	
	
	
	

}
