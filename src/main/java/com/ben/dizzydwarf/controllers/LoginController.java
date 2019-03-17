package com.ben.dizzydwarf.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ben.dizzydwarf.App;

/**
 * Action dealing with signing up and login
 * @author Xuben
 *
 */
@Controller
public class LoginController {

	@RequestMapping("/login")
	@ResponseBody
	public App login(HttpServletRequest req, 
			@RequestParam String username, 
			@RequestParam String password) throws IOException {
		App app = new App();
		if (null == username || null == password 
				|| username.trim().length() <= 0
				|| password.trim().length() <= 0) {
			app.setMsg("invalid username or password");
			return app;
		}
		HttpSession session = req.getSession(true);
		String uname = (String) session.getAttribute("uname");
		String passwd = (String) session.getAttribute("passwd");
		if (null == uname || null == passwd) {
			app.setMsg("you haven't registered");
			return app;
		}
		if (uname.equals(username) && passwd.equals(password)) {
			app.setMsg("success");
		} else {
			app.setMsg("wrong username or password");
		}
		
		return app;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public String register(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (null == username || null == password 
				|| username.trim().length() <= 0 || password.trim().length() <= 0) {
			return "invalid username or password";
		}
		HttpSession session = req.getSession(true);
		session.setAttribute("uname", username);
		session.setAttribute("passwd", password);
		return "success";
	}
}
