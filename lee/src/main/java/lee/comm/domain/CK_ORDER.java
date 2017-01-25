package lee.comm.domain;

public class CK_ORDER {
	
	private long order_sno; //주문 일련번호
	private long usr_no;
	private long prdt_sno;
	private long prdt_count; //상품 수량
	private long total_price;
	private String addr;
	private String order_date;
	private String order_state; // 주문 상태
	private String del_yn;
	private String cart_reg_date;
	private String pay_date;
	private String pay_gubun;
	private String pay_gubun2;
	private String arv_date;
	private String usr_nm;
	private String usr_hp;
	
	public long getOrder_sno() {
		return order_sno;
	}
	public void setOrder_sno(long order_sno) {
		this.order_sno = order_sno;
	}
	public long getUsr_no() {
		return usr_no;
	}
	public void setUsr_no(long usr_no) {
		this.usr_no = usr_no;
	}
	public long getPrdt_sno() {
		return prdt_sno;
	}
	public void setPrdt_sno(long prdt_sno) {
		this.prdt_sno = prdt_sno;
	}
	
	public long getPrdt_count() {
		return prdt_count;
	}
	public void setPrdt_count(long prdt_count) {
		this.prdt_count = prdt_count;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getCart_reg_date() {
		return cart_reg_date;
	}
	public void setCart_reg_date(String cart_reg_date) {
		this.cart_reg_date = cart_reg_date;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getPay_gubun() {
		return pay_gubun;
	}
	public void setPay_gubun(String pay_gubun) {
		this.pay_gubun = pay_gubun;
	}
	public String getPay_gubun2() {
		return pay_gubun2;
	}
	public void setPay_gubun2(String pay_gubun2) {
		this.pay_gubun2 = pay_gubun2;
	}
	public String getArv_date() {
		return arv_date;
	}
	public void setArv_date(String arv_date) {
		this.arv_date = arv_date;
	}
	public long getTotal_price() {
		return total_price;
	}
	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}
	public String getUsr_nm() {
		return usr_nm;
	}
	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}
	public String getUsr_hp() {
		return usr_hp;
	}
	public void setUsr_hp(String usr_hp) {
		this.usr_hp = usr_hp;
	}
	
	
}
