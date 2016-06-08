package org.andy.shop.controller;

import com.google.gson.Gson;
import org.andy.shop.model.CourseInfo;
import org.andy.shop.model.UserInfo;
import org.andy.shop.result.RespResult;
import org.andy.shop.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/showInfos", method = RequestMethod.GET)
    @ResponseBody
    public void showUserInfos(HttpServletRequest req, HttpServletResponse rep) throws IOException {
        rep.setContentType("text/plain;charset=utf-8");
        PrintWriter writer = rep.getWriter();
        CourseInfo info = new CourseInfo();
        info.setId(1);
        info.setCaddress("dizhi");
        info.setCname("woshi");
        writer.println(generateResult(info));
        writer.flush();
        writer.close();
    }

    @ResponseBody
    @RequestMapping(value = "/books.do", method = RequestMethod.GET)
    public RespResult getBooks(int id) throws IOException {
        System.out.println(id);
        CourseInfo info = new CourseInfo();
        info.setCaddress("hah");
        info.setCname("fdsa");
        info.setId(111);
        return new RespResult(info);

    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String hello(@RequestParam(value = "a", required = false) String a, HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();
        System.out.println(a);
        String sss = null;
        List<UserInfo> userInfos = userService.getUsers();
        return generateResult(userInfos);
    }


    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/fileUpload.do", method = RequestMethod.POST)
    public void fileUpload(@RequestParam("file") MultipartFile file,
                           HttpServletRequest request, HttpServletResponse response)
            throws IOException {// 此处参数与表单参数一致
        System.out.println(request.getParameter("versionName") + request.getParameter("apkSize"));
        if (!file.isEmpty()) {
            try {
                String filePath = request.getSession().getServletContext()
                        .getRealPath("/")
                        + "upload/" + file.getOriginalFilename();
                System.out.println(filePath);
                File destFile = new File(filePath);
                FileUtils
                        .copyInputStreamToFile(file.getInputStream(), destFile);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("哈哈");
        writer.flush();
    }

    /**
     * 多文件上传
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/mulitiFilesUpload.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String filesUpload(@RequestParam("files") MultipartFile[] files) {// 此处参数与表单参数一致
        // 判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            // 循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                // 判断文件是否为空
                if (!file.isEmpty()) {
                    try {
                        // 文件保存路径
                        String filePath = request.getSession()
                                .getServletContext().getRealPath("/")
                                + "upload/" + file.getOriginalFilename();
                        // 转存文件
                        File destFile = new File(filePath);
                        FileUtils.copyInputStreamToFile(file.getInputStream(),
                                destFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "hahaha";
    }
}
