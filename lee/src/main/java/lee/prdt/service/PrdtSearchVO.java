package lee.prdt.service;

import lee.comm.domain.ListPagingVO;

public class PrdtSearchVO extends ListPagingVO{
	private long sh_lg_cate_sno;
	private long sh_cate_sno;
    private long prdt_sno;
	
	//가격
	
	public long getSh_lg_cate_sno() {
		return sh_lg_cate_sno;
	}
	public void setSh_lg_cate_sno(long sh_lg_cate_sno) {
		this.sh_lg_cate_sno = sh_lg_cate_sno;
	}
	public long getSh_cate_sno() {
		return sh_cate_sno;
	}
	public void setSh_cate_sno(long sh_cate_sno) {
		this.sh_cate_sno = sh_cate_sno;
	}
	public long getPrdt_sno() {
		return prdt_sno;
	}
	public void setPrdt_sno(long prdt_sno) {
		this.prdt_sno = prdt_sno;
	}
	
	
	

}
