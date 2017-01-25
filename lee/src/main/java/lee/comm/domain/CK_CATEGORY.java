package lee.comm.domain;

import java.io.Serializable;

public class CK_CATEGORY implements Serializable{
	private long cate_sno;
	private long lg_cate_sno;
	private String cate_nm;
	private String cate_sex;
	private String reg_date;
	private String use_yn;
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
	public String getCate_nm() {
		return cate_nm;
	}
	public void setCate_nm(String cate_nm) {
		this.cate_nm = cate_nm;
	}
	public String getCate_sex() {
		return cate_sex;
	}
	public void setCate_sex(String cate_sex) {
		this.cate_sex = cate_sex;
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
}
