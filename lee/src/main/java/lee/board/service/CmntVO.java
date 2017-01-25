package lee.board.service;

public class CmntVO {
	
	private long cmnt_sno;	//코멘트일련번호
	private long blt_rsrc_sno;	//게시자료일련번호
	private String cmnt_contents;	//코멘트내용
	private long reg_usr_no;	//등록사용자번호
	private String reg_date;	//등록일시
	private String del_yn;	//삭제여부
	private String usr_nm;	//작성자 이름
	
	public long getCmnt_sno() {
		return cmnt_sno;
	}
	public void setCmnt_sno(long cmnt_sno) {
		this.cmnt_sno = cmnt_sno;
	}
	
	public long getBlt_rsrc_sno() {
		return blt_rsrc_sno;
	}
	public void setBlt_rsrc_sno(long blt_rsrc_sno) {
		this.blt_rsrc_sno = blt_rsrc_sno;
	}
	public String getCmnt_contents() {
		return cmnt_contents;
	}
	public void setCmnt_contents(String cmnt_contents) {
		this.cmnt_contents = cmnt_contents;
	}
	
	public long getReg_usr_no() {
		return reg_usr_no;
	}
	public void setReg_usr_no(long reg_usr_no) {
		this.reg_usr_no = reg_usr_no;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getUsr_nm() {
		return usr_nm;
	}
	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}
	
}
