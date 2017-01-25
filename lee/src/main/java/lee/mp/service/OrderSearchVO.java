package lee.mp.service;

import lee.comm.domain.ListPagingVO;

public class OrderSearchVO extends ListPagingVO{
	private String sh_orderSts;
	private String sh_dateTo;
	private String sh_dateFr;
	private String sh_prdtNm;
	private long usr_no;
	private String cartYn;
	
	public String getSh_orderSts() {
		return sh_orderSts;
	}
	public void setSh_orderSts(String sh_orderSts) {
		this.sh_orderSts = sh_orderSts;
	}
	public String getSh_dateTo() {
		return sh_dateTo;
	}
	public void setSh_dateTo(String sh_dateTo) {
		this.sh_dateTo = sh_dateTo;
	}
	public String getSh_dateFr() {
		return sh_dateFr;
	}
	public void setSh_dateFr(String sh_dateFr) {
		this.sh_dateFr = sh_dateFr;
	}
	public String getSh_prdtNm() {
		return sh_prdtNm;
	}
	public void setSh_prdtNm(String sh_prdtNm) {
		this.sh_prdtNm = sh_prdtNm;
	}
	public long getUsr_no() {
		return usr_no;
	}
	public void setUsr_no(long usr_no) {
		this.usr_no = usr_no;
	}
	public String getCartYn() {
		return cartYn;
	}
	public void setCartYn(String cartYn) {
		this.cartYn = cartYn;
	}
}
