package org.andy.shop.controller;

import com.google.gson.Gson;
import org.andy.shop.model.UserInfo;
import org.andy.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("user")
public class UserController {
	private ConcurrentHashMap<String,String> mCacheMap = new ConcurrentHashMap<String, String>();
	@Resource(name = "userService")
	private UserService userService;
	
	@RequestMapping(value ="/showInfos.do" , method = RequestMethod.GET)
	@ResponseBody
	public void showUserInfos(HttpServletRequest req, HttpServletResponse rep) throws IOException {
		rep.setContentType("text/plain;charset=utf-8");
		PrintWriter writer = rep.getWriter();
		Gson gson = new Gson();
		String sss = null;
		if(mCacheMap.containsKey("sss")){
			sss = mCacheMap.get("sss");
			System.out.print("form cache");
		}else {
			List<UserInfo> userInfos = userService.getUsers();
			sss=gson.toJson(userInfos);
			mCacheMap.put("sss",sss);
			System.out.print("form db");

		}
		writer.println(sss);
		writer.flush();
		writer.close();


	}


	@RequestMapping(value = "/books.do", method = RequestMethod.GET)
	@ResponseBody
	public void getBooks(HttpServletRequest req, HttpServletResponse rep) throws IOException {
		rep.setContentType("text/plain;charset=utf-8");
		System.out.print(req.getParameter("haha"));
		PrintWriter writer = rep.getWriter();
		writer.println("{\"haha\":\"你好啊\",\"xiix\":\"集合\"}");
		System.out.print("hahaha");
		writer.flush();
		writer.close();

	}


}
