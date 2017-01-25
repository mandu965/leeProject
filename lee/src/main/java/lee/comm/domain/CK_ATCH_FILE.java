package lee.comm.domain;

public class CK_ATCH_FILE {
	
	/** 첨부파일일련번호 */
	protected long atch_file_sno;

	/** 첨부파일번호 */
	protected String atch_file_no;

	/** 서비스영역코드3050 */
	protected String svc_dman_cd;

	/** 서비스일련번호 */
	protected long svc_sno;

	/** 첨부파일버전 */
	protected float atch_file_ver;

	/** 저장위치내용 */
	protected String kpn_lctn_cntn;

	/** 저장URL */
	protected String kpn_url;

	/** 파일표시명 */
	protected String file_dsp_nm;

	/** 파일저장명 */
	protected String file_kpn_nm;

	/** 파일확장명 */
	protected String file_estn_nm;

	/** 파일유형명 */
	protected String file_tp_nm;

	/** 파일크기바이트 */
	protected double file_sz_byte;

	/** 자료제목 */
	protected String rsrc_tlt;

	/** 자료코멘트내용 */
	protected String rsrc_cmnt_cntn;

	/** 다운로드수 */
	protected long dwn_num;

	/** 공개여부 */
	protected String open_yn;

	/** 세부내용여부 */
	protected String dtl_cntn_yn;

	/** 삭제여부 */
	protected String del_yn;

	/** 등록사용자번호 */
	protected long reg_usr_no;

	/** 등록일시 */
	protected String reg_dttm;

	/** 수정사용자번호 */
	protected long mod_usr_no;

	/** 수정일시 */
	protected String mod_dttm;

	/** 삭제사용자번호 */
	protected long del_usr_no;

	/** 삭제일시 */
	protected String del_dttm;

	public long getAtch_file_sno() {
		return atch_file_sno;
	}

	public void setAtch_file_sno(long atch_file_sno) {
		this.atch_file_sno = atch_file_sno;
	}

	public String getAtch_file_no() {
		return atch_file_no;
	}

	public void setAtch_file_no(String atch_file_no) {
		this.atch_file_no = atch_file_no;
	}

	public String getSvc_dman_cd() {
		return svc_dman_cd;
	}

	public void setSvc_dman_cd(String svc_dman_cd) {
		this.svc_dman_cd = svc_dman_cd;
	}

	public long getSvc_sno() {
		return svc_sno;
	}

	public void setSvc_sno(long svc_sno) {
		this.svc_sno = svc_sno;
	}

	public float getAtch_file_ver() {
		return atch_file_ver;
	}

	public void setAtch_file_ver(float atch_file_ver) {
		this.atch_file_ver = atch_file_ver;
	}

	public String getKpn_lctn_cntn() {
		return kpn_lctn_cntn;
	}

	public void setKpn_lctn_cntn(String kpn_lctn_cntn) {
		this.kpn_lctn_cntn = kpn_lctn_cntn;
	}

	public String getKpn_url() {
		return kpn_url;
	}

	public void setKpn_url(String kpn_url) {
		this.kpn_url = kpn_url;
	}

	public String getFile_dsp_nm() {
		return file_dsp_nm;
	}

	public void setFile_dsp_nm(String file_dsp_nm) {
		this.file_dsp_nm = file_dsp_nm;
	}

	public String getFile_kpn_nm() {
		return file_kpn_nm;
	}

	public void setFile_kpn_nm(String file_kpn_nm) {
		this.file_kpn_nm = file_kpn_nm;
	}

	public String getFile_estn_nm() {
		return file_estn_nm;
	}

	public void setFile_estn_nm(String file_estn_nm) {
		this.file_estn_nm = file_estn_nm;
	}

	public String getFile_tp_nm() {
		return file_tp_nm;
	}

	public void setFile_tp_nm(String file_tp_nm) {
		this.file_tp_nm = file_tp_nm;
	}

	public double getFile_sz_byte() {
		return file_sz_byte;
	}

	public void setFile_sz_byte(double file_sz_byte) {
		this.file_sz_byte = file_sz_byte;
	}

	public String getRsrc_tlt() {
		return rsrc_tlt;
	}

	public void setRsrc_tlt(String rsrc_tlt) {
		this.rsrc_tlt = rsrc_tlt;
	}

	public String getRsrc_cmnt_cntn() {
		return rsrc_cmnt_cntn;
	}

	public void setRsrc_cmnt_cntn(String rsrc_cmnt_cntn) {
		this.rsrc_cmnt_cntn = rsrc_cmnt_cntn;
	}

	public long getDwn_num() {
		return dwn_num;
	}

	public void setDwn_num(long dwn_num) {
		this.dwn_num = dwn_num;
	}

	public String getOpen_yn() {
		return open_yn;
	}

	public void setOpen_yn(String open_yn) {
		this.open_yn = open_yn;
	}

	public String getDtl_cntn_yn() {
		return dtl_cntn_yn;
	}

	public void setDtl_cntn_yn(String dtl_cntn_yn) {
		this.dtl_cntn_yn = dtl_cntn_yn;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public long getReg_usr_no() {
		return reg_usr_no;
	}

	public void setReg_usr_no(long reg_usr_no) {
		this.reg_usr_no = reg_usr_no;
	}

	public String getReg_dttm() {
		return reg_dttm;
	}

	public void setReg_dttm(String reg_dttm) {
		this.reg_dttm = reg_dttm;
	}

	public long getMod_usr_no() {
		return mod_usr_no;
	}

	public void setMod_usr_no(long mod_usr_no) {
		this.mod_usr_no = mod_usr_no;
	}

	public String getMod_dttm() {
		return mod_dttm;
	}

	public void setMod_dttm(String mod_dttm) {
		this.mod_dttm = mod_dttm;
	}

	public long getDel_usr_no() {
		return del_usr_no;
	}

	public void setDel_usr_no(long del_usr_no) {
		this.del_usr_no = del_usr_no;
	}

	public String getDel_dttm() {
		return del_dttm;
	}

	public void setDel_dttm(String del_dttm) {
		this.del_dttm = del_dttm;
	}
	
	
	

}
