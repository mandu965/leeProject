package lee.comm.domain;


public class Paging {

	// 한 페이지 당 보여줄 글 갯수
	private int pageSize = 10;
	// 페이지그룹안의 페이지 갯수 ex) [이전] 1 2 3 4 5 [다음] 일 경우 페이지 갯수는 5
	private int pageGroupSize = 5;

	// 현재 페이지 번호 or 선택한 페이지 번호
	private String pageNum;
	private int currentPage;

	// 총 레코드
	private int count;

	private int startRow;
	private int endRow;

	private int pageGroupCount;
	private int numPageGroup;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageGroupSize() {
		return pageGroupSize;
	}

	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
		setCurrentPage(pageNum);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String pageNum) {
		this.currentPage = Integer.parseInt(pageNum);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int currentPage, int pageSize) {
		this.startRow = (currentPage - 1) * pageSize + 1;// 한 페이지의 시작글 번호
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int currentPage, int pageSize) {
		this.endRow = currentPage * pageSize;// 한 페이지의 마지막 글번호;
	}

	public void setEndRow(int count) {
		this.endRow = count;
	}

	public int getPageGroupCount() {
		return pageGroupCount;
	}

	public void setPageGroupCount(int count, int pageSize, int pageGroupSize) {
		// 페이지그룹의 갯수
		// ex) pageGroupSize가 3일 경우 '[1][2][3]'가 pageGroupCount 개 만큼 있다.
		this.pageGroupCount = count / (pageSize * pageGroupSize)
				+ (count % (pageSize * pageGroupSize) == 0 ? 0 : 1);
	}

	public int getNumPageGroup() {
		return numPageGroup;
	}

	public void setNumPageGroup(int currentPage, int pageGroupSize) {
		// 페이지 그룹 번호
		// ex) pageGroupSize가 3일 경우 '[1][2][3]'의 페이지그룹번호는 1 이고 '[2][3][4]'의
		// 페이지그룹번호는 2 이다.
		this.numPageGroup = (int) Math.ceil((double) currentPage/ pageGroupSize);
	}
}
