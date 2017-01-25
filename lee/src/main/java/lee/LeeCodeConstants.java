package lee;

public class LeeCodeConstants {
	
	/** 여부 */
	public static final String YES = "Y";
	public static final String NO = "N";
	
	/** 권한코드 */
	public static final String USR_NORMAL = "101"; //일반 사용자
	public static final String USR_MANAGER = "102"; //매니저(팀장)
	public static final String USR_ADMIN = "103"; //관리자
	
	/** 성별*/
	public static final String USER_SEX_MAN = "M";
	public static final String USER_SEX_WOMAN = "W";
	
	/** 상품처리상태 */
	public static final String PRDT_STATUS_CART = "101"; //장바구니
	public static final String PRDT_STATUS_ORDER = "102"; //주문
	public static final String PRDT_STATUS_DEPOSIT = "103"; //입금
	public static final String PRDT_STATUS_DELIVERY = "104"; //배송중
	public static final String PRDT_STATUS_COMPLETE = "105"; //배송완료
	
	/**결제방법 */
	public static final String PAY_GB = "2010";
	public static final String PAY_GB_CASH = "1010"; //현금
	public static final String PAY_GB_CARD = "1020"; //카드
	public static final String PAY_GB_MOBILE = "1030"; //모바일 소액결재
	
	/**현금 결제방법 */
	public static final String CASH_STATUS="1010";
	public static final String CASH_NON_ACCOUNT="101";//무통장 입금
	public static final String CASH_ACCOUNT="102";//실시간 계좌이체
	
	/**카드 결제방법 */
	public static final String CARD_STATUS="1020";
	public static final String CARD_NHN="101";//농협
	public static final String CARD_KB="102";//국민은행
	public static final String CARD_WOORI="103";//우리은행
	public static final String CARD_HANA="104";//하나은행
	
	/**휴대폰 결제방법 */
	public static final String MOBILE_STATUS="1030";
	public static final String MOBILE_SOECK="101";//휴대폰 소액결제
	public static final String MOBILE_YELOPAY="102";//옐로페이
	public static final String MOBILE_TMONEY="103";//모바일티머니
	
	
	/** 첨부파일 서비스 */
	public static final String BOARD = "10";
	public static final String PRODUCT = "20";
	
	
	
	
	
	

}
