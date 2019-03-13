package com.ben.dizzydwarf.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action dealing with signing up and login
 * @author Xuben
 *
 */
public class LoginAction {

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (null == username || null == password) {
			resp.getWriter().write("invalid username or password");
			resp.getWriter().flush();
			return;
		}
		if ("ben".equals("username") && "123".equals("password")) {
			resp.setStatus(200);
			resp.flushBuffer();
		} else {
			resp.getWriter().write("wrong username or password");
			resp.getWriter().flush();
		}
	}
}
