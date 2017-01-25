package lee.sm.usrmng.service;

import lee.comm.domain.ListPagingVO;

public class UsrmngSearchVO extends ListPagingVO{
	
	public UsrmngSearchVO(){
		System.out.println("SearchVO들어옴");
	}
	
	/** 사용자 명 */
	private String sh_usr_nm;
	
	/** 아이디 */
	private String sh_usr_id;
	
	//성별
	 private String[] sh_usr_sexList;
	//권항
	private String[] sh_usr_athList;
	
	public String getSh_usr_nm() {
		return sh_usr_nm;
	}
	public void setSh_usr_nm(String sh_usr_nm) {
		this.sh_usr_nm = sh_usr_nm;
	}
	public String getSh_usr_id() {
		return sh_usr_id;
	}
	public void setSh_usr_id(String sh_usr_id) {
		this.sh_usr_id = sh_usr_id;
	}
	public String[] getSh_usr_athList() {
		return sh_usr_athList;
	}
	public void setSh_usr_athList(String[] sh_usr_athList) {
		this.sh_usr_athList = sh_usr_athList;
	}
	public String[] getSh_usr_sexList() {
		return sh_usr_sexList;
	}
	public void setSh_usr_sexList(String[] sh_usr_sexList) {
		this.sh_usr_sexList = sh_usr_sexList;
	}

}
