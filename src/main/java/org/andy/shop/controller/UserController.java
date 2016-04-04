package org.andy.shop.controller;

import com.google.gson.Gson;
import org.andy.shop.model.UserInfo;
import org.andy.shop.result.BaseResult;
import org.andy.shop.result.ListResult;
import org.andy.shop.result.ResponseListResult;
import org.andy.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "/showInfos.do", method = RequestMethod.GET)
    @ResponseBody
    public void showUserInfos(HttpServletRequest req, HttpServletResponse rep) throws IOException {
        rep.setContentType("text/plain;charset=utf-8");
        PrintWriter writer = rep.getWriter();
        Gson gson = new Gson();
        String sss = null;
        List<UserInfo> userInfos = userService.getUsers();
        sss = gson.toJson(userInfos);
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
    @RequestMapping(value = "/hello.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8" )
    @ResponseBody
    public String hello(@RequestParam(value = "a",required = false) String a, HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();
        System.out.println(a);
        String sss = null;
        List<UserInfo> userInfos = userService.getUsers();
        ListResult listResult = new ListResult();
        listResult.setList(userInfos);
        ResponseListResult baseResult = new ResponseListResult();
        baseResult.setObject(listResult);
        sss = gson.toJson(baseResult);
        return sss;

    }
}
