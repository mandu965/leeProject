package lee.comm.domain;

import java.io.Serializable;

public class CK_PRDT implements Serializable{
	
	
	private long prdt_sno;//상품번호
	private long prdt_price;//상품가격
	private String prdt_expl;//상품 설명
	private long cate_sno; // 카테고리 번호
	private long lg_cate_sno;
	private String prdt_nm; // 상품명
	private String reg_date; //등록 일시
	private String use_yn; //사용여부(삭제여부)
	private long  reg_usr_no;
	private long atch_file_sno;
	public long getPrdt_sno() {
		return prdt_sno;
	}
	public void setPrdt_sno(long prdt_sno) {
		this.prdt_sno = prdt_sno;
	}
	public long getPrdt_price() {
		return prdt_price;
	}
	public void setPrdt_price(long prdt_price) {
		this.prdt_price = prdt_price;
	}
	public String getPrdt_expl() {
		return prdt_expl;
	}
	public void setPrdt_expl(String prdt_expl) {
		this.prdt_expl = prdt_expl;
	}
	public long getCate_sno() {
		return cate_sno;
	}
	public void setCate_sno(long cate_sno) {
		this.cate_sno = cate_sno;
	}
	public long getLg_cate_sno() {
		return lg_cate_sno;
	}
	public void setLg_cate_sno(long lg_cate_sno) {
		this.lg_cate_sno = lg_cate_sno;
	}
	public String getPrdt_nm() {
		return prdt_nm;
	}
	public void setPrdt_nm(String prdt_nm) {
		this.prdt_nm = prdt_nm;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public long getReg_usr_no() {
		return reg_usr_no;
	}
	public void setReg_usr_no(long reg_usr_no) {
		this.reg_usr_no = reg_usr_no;
	}
	public long getAtch_file_sno() {
		return atch_file_sno;
	}
	public void setAtch_file_sno(long atch_file_sno) {
		this.atch_file_sno = atch_file_sno;
	}
	
	
	
}
