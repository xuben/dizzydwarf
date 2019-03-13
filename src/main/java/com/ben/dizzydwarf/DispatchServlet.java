package com.ben.dizzydwarf;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ben.dizzydwarf.action.LoginAction;

/**
 * the servlet that dispatches urls to different actions
 * 
 * @author Xuben
 *
 */
public class DispatchServlet extends HttpServlet {

	private LoginAction loginAction = new LoginAction();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String uri = req.getRequestURI();
			if (null == uri || uri.trim().length() == 0) {
				resp.sendError(404);
				return;
			}
			loginAction.login(req, resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}
}
