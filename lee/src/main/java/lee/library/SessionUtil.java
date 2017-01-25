package lee.library;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil
{
  public static boolean hasSession(HttpServletRequest req)
  {
    return req.getSession() != null;
  }

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

  public static Object popAttribute(HttpServletRequest req, String key)
  {
    HttpSession session = req.getSession();
    if (session == null) {
      return null;
    }
    Object obj = session.getAttribute(key);
    session.removeAttribute(key);

    return obj;
  }

  public static void removeAttribute(HttpServletRequest req, String key)
  {
    HttpSession session = req.getSession();
    if (session == null) {
      return;
    }
    session.removeAttribute(key);
  }

  public static void clearSession(HttpServletRequest req)
  {
    HttpSession session = req.getSession();
    if (session == null) {
      return;
    }
    Enumeration em = session.getAttributeNames();
    if (em == null) {
      return;
    }
    while (em.hasMoreElements()) {
      String key = (String)em.nextElement();
      session.removeAttribute(key);
    }
  }

  public static boolean hasAttribute(HttpServletRequest req, String key)
  {
    HttpSession session = req.getSession();
    if (session == null) {
      return false;
    }
    return session.getAttribute(key) != null;
  }
}