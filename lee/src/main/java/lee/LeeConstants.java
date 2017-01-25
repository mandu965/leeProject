package lee;

public class LeeConstants {
	
	/** 세션 키 : 로그인 정보 */
	public static final String SESSION_KEY_LOGIN = "lee_loginSession";
	
	
	/** 파일 업로드 경로 */
	private static String serverUrl;
	private static String attachSaveDir;
	private static String bbsSaveDir;
	private static String bbsSaveUrl;
	
	/** 메일발송용 */
	private static String mailServerHost;
	private static int mailServerPort;
	private static boolean mailServerSmtpAuth;
	private static String mailUserName;
	private static String mailPassword;
	
	/** 첨부파일 다운로드 URL */
	private static String leeUrl;
	
	/** 이미지 경로 */
	private static String imgUrl;

	public static String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		LeeConstants.serverUrl = serverUrl;
	}

	public static String getAttachSaveDir() {
		return attachSaveDir;
	}

	public void setAttachSaveDir(String attachSaveDir) {
		LeeConstants.attachSaveDir = attachSaveDir;
	}

	public static String getLeeUrl() {
		return leeUrl;
	}

	public void setLeeUrl(String leeUrl) {
		LeeConstants.leeUrl = leeUrl;
	}

	public static String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		LeeConstants.imgUrl = imgUrl;
	}

	public static String getBbsSaveDir() {
		return bbsSaveDir;
	}

	public void setBbsSaveDir(String bbsSaveDir) {
		LeeConstants.bbsSaveDir = bbsSaveDir;
	}

	public static String getBbsSaveUrl() {
		return bbsSaveUrl;
	}

	public void setBbsSaveUrl(String bbsSaveUrl) {
		LeeConstants.bbsSaveUrl = bbsSaveUrl;
	}

	public static String getMailServerHost() {
		return mailServerHost;
	}

	public static void setMailServerHost(String mailServerHost) {
		LeeConstants.mailServerHost = mailServerHost;
	}

	public static int getMailServerPort() {
		return mailServerPort;
	}

	public static void setMailServerPort(int mailServerPort) {
		LeeConstants.mailServerPort = mailServerPort;
	}

	public static boolean getMailServerSmtpAuth() {
		return mailServerSmtpAuth;
	}

	public static void setMailServerSmtpAuth(boolean mailServerSmtpAuth) {
		LeeConstants.mailServerSmtpAuth = mailServerSmtpAuth;
	}

	public static String getMailUserName() {
		return mailUserName;
	}

	public static void setMailUserName(String mailUserName) {
		LeeConstants.mailUserName = mailUserName;
	}

	public static String getMailPassword() {
		return mailPassword;
	}

	public static void setMailPassword(String mailPassword) {
		LeeConstants.mailPassword = mailPassword;
	}

	
}
