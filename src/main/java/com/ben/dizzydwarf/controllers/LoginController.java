package com.ben.dizzydwarf.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ben.dizzydwarf.App;
import com.ben.dizzydwarf.User;
import com.ben.dizzydwarf.UserDao;

/**
 * Action dealing with signing up and login
 * 
 * @author Xuben
 *
 */
@Controller
public class LoginController {

	@RequestMapping("/login")
	@ResponseBody
	public App login(HttpServletRequest req, @RequestParam String username, @RequestParam String password)
			throws IOException {
		App app = new App();
		if (null == username || null == password || username.trim().length() <= 0 || password.trim().length() <= 0) {
			app.setMsg("invalid username or password");
			return app;
		}
		UserDao userDao = new UserDao();
		User user = userDao.getUser(username);
		if (null == user || null == user.getPassword() || !user.getPassword().equals(password)) {
			app.setMsg("wrong username or password");
		} else {
			app.setMsg("success");
		}

		return app;
	}

	@RequestMapping("/register")
	@ResponseBody
	public String register(@ModelAttribute User user, HttpServletRequest req) throws IOException {
		String username = user.getUsername();
		String password = user.getPassword();
		if (null == username || null == password || username.trim().length() <= 0 || password.trim().length() <= 0) {
			return "invalid username or password";
		}
		UserDao userDao = new UserDao();
		if (userDao.createUser(user) > 0) {
			return "success";
		} else {
			return "failed";
		}
	}

	@RequestMapping("/upload")
	public void upload(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// get the folder to save all the images, create if not exist
		String folder = req.getServletContext().getRealPath("tmp");
		new File(folder).mkdir();
		
		req.setCharacterEncoding("utf-8");
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			String filename = part.getSubmittedFileName();
			if (null == filename || filename.trim().length() <= 0) {
				continue;
			}
			File file = new File(folder + File.separator + filename);
			try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));) {
				InputStream in = part.getInputStream();
				byte[] temp = new byte[1024];
				int bytesRead = -1;
				while ((bytesRead = in.read(temp)) > 0) {
					out.write(temp, 0, bytesRead);
				}
				out.flush();
			} finally {
			}
		}
	}
}
