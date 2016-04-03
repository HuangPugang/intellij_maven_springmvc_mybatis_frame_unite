package org.andy.shop.controller;

import com.google.gson.Gson;
import org.andy.shop.model.UserInfo;
import org.andy.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/showInfo/{userId}")
	public String showUserInfo(ModelMap modelMap, @PathVariable int userId){
		UserInfo userInfo = userService.getUserById(userId);
		modelMap.addAttribute("userInfo", userInfo);
		return "/user/showInfo";
	}
	
	@RequestMapping(value ="/showInfos.do" , method = RequestMethod.GET)
	@ResponseBody
	public void showUserInfos(HttpServletRequest req, HttpServletResponse rep) throws IOException {
		List<UserInfo> userInfos = userService.getUsers();
		rep.setContentType("text/plain;charset=utf-8");
		PrintWriter writer = rep.getWriter();
		Gson gson = new Gson();
		String sss = gson.toJson(userInfos);
		writer.println(sss);
		System.out.print(userInfos.toString());
		writer.flush();
		writer.close();


	}


	@RequestMapping(value = "/books.do", method = RequestMethod.GET)
	@ResponseBody
	public void getBooks(HttpServletRequest req, HttpServletResponse rep) throws IOException {
		rep.setContentType("text/plain;charset=utf-8");
		PrintWriter writer = rep.getWriter();
		writer.println("{\"haha\":\"你好啊\",\"xiix\":\"集合\"}");
		System.out.print("hahaha");
		writer.flush();
		writer.close();

	}
}
