package lee.prdt.service;

import lee.comm.domain.CK_PRDT;

public class PrdtVO extends CK_PRDT{
	private String lg_cate_nm;
	private String cate_nm;
	private String thumb;
	
	public String getLg_cate_nm() {
		return lg_cate_nm;
	}
	public void setLg_cate_nm(String lg_cate_nm) {
		this.lg_cate_nm = lg_cate_nm;
	}
	public String getCate_nm() {
		return cate_nm;
	}
	public void setCate_nm(String cate_nm) {
		this.cate_nm = cate_nm;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	
	

}
