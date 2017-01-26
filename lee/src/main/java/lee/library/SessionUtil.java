package lee.library;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil
{
  public static boolean hasSession(HttpServletRequest req)
  {
    return req.getSession() != null;
  }

  //세션셍성
  public static void setAttribute(HttpServletRequest req, String key, Object obj)
  {
    HttpSession session = req.getSession(true);

    session.setAttribute(key, obj);
  }

  public static Object getAttribute(HttpServletRequest req, String key)
  {
    HttpSession session = req.getSession();

    return session == null ? null : session.getAttribute(key);
  }
}