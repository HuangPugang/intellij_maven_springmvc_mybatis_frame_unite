package org.andy.shop.service;

import java.util.List;

import org.andy.shop.model.UserInfo;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建时间：2015-1-27 下午10:45:38
 *
 * @author andy
 * @version 2.2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc.xml"})
public class TestUserService {

    private static final Logger LOGGER = Logger
            .getLogger(TestUserService.class);

    @Autowired
    private UserService userService;

    @Test
    public void testQueryById1() {
        UserInfo userInfo = userService.getUserById(1);
        LOGGER.info(JSON.toJSON(userInfo));
    }

    @Test
    public void testQueryAll() {
        List<UserInfo> userInfos = userService.getUsers();
        LOGGER.info(JSON.toJSON(userInfos));
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testInsert() {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(4);
            userInfo.setUname("黄浦");
            userInfo.setUnumber(4);
            int result = userService.insert(userInfo);
            int result2 = userService.insert(userInfo);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出错啦");
        }
    }
}
