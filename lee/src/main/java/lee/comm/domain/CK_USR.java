package lee.comm.domain;

import java.io.Serializable;

public class CK_USR implements Serializable{  //implements Serializable 의미파악해서 써놓기
	
	/** 사용자 번호 */
	private long usr_no;
	/** 사용자 명 */
	private String usr_nm;
	/** 사용자 권한 코드 */
	private String usr_auth_cd;
	/** 사용자 주소 */
	private String usr_addr;
	/** 사용자 생일 */
	private String usr_birth;
	/** 사용자 id */
	private String usr_id;
	/** 사용자 적립 포인트 */
	private long usr_point;
	/** 사용자 성별 */
	private String usr_sex;
	/** 사용자 pw */
	private String usr_pw;
	/** 가입일자  */
	private String reg_date;
	/** 핸드폰번호 */
	private String usr_hp;
	
	
	public long getUsr_no() {
		return usr_no;
	}
	public void setUsr_no(long usr_no) {
		this.usr_no = usr_no;
	}
	public String getUsr_nm() {
		return usr_nm;
	}
	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}
	public String getUsr_auth_cd() {
		return usr_auth_cd;
	}
	public void setUsr_auth_cd(String usr_auth_cd) {
		this.usr_auth_cd = usr_auth_cd;
	}
	public String getUsr_addr() {
		return usr_addr;
	}
	public void setUsr_addr(String usr_addr) {
		this.usr_addr = usr_addr;
	}
	public String getUsr_birth() {
		return usr_birth;
	}
	public void setUsr_birth(String usr_birth) {
		this.usr_birth = usr_birth;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public long getUsr_point() {
		return usr_point;
	}
	public void setUsr_point(long usr_point) {
		this.usr_point = usr_point;
	}
	public String getUsr_sex() {
		return usr_sex;
	}
	public void setUsr_sex(String usr_sex) {
		this.usr_sex = usr_sex;
	}
	public String getUsr_pw() {
		return usr_pw;
	}
	public void setUsr_pw(String usr_pw) {
		this.usr_pw = usr_pw;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUsr_hp() {
		return usr_hp;
	}
	public void setUsr_hp(String usr_hp) {
		this.usr_hp = usr_hp;
	}
	
	

}
