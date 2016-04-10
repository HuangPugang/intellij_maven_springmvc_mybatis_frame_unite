package org.andy.shop.service;

import java.util.List;
import java.util.Set;

import org.andy.common.StatusCode;
import org.andy.redis.RedisService;
import org.andy.shop.model.UserInfo;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("redisService")
    private RedisService redisService;

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

    @Test
    public void test(){
        System.out.println(StatusCode.SUCCESS.getCode()+" "+StatusCode.ERROR.getMsg());
    }

    @Test
    public void redisTest() throws Exception {
        String ping = redisService.ping();//测试是否连接成功,连接成功输出PONG
        System.out.println(ping);

        //首先,我们看下redis服务里是否有数据
        long dbSizeStart = redisService.dbSize();
        System.out.println(dbSizeStart);

        redisService.set("username", "oyhk");//设值(查看了源代码,默认存活时间30分钟)
        String username = redisService.get("username");//取值
        System.out.println(username);
        redisService.set("username1", "oyhk1", 1);//设值,并且设置数据的存活时间(这里以秒为单位)
        String username1 = redisService.get("username1");
        System.out.println(username1);
        Thread.sleep(2000);//我睡眠一会,再去取,这个时间超过了,他的存活时间
        String liveUsername1 = redisService.get("username1");
        System.out.println(liveUsername1);//输出null

        //是否存在
        boolean exist = redisService.exists("username");
        System.out.println(exist);

        //查看keys
        Set<String> keys = redisService.keys("*");//这里查看所有的keys
        System.out.println(keys);//只有username username1(已经清空了)


        //删除
        redisService.set("username2", "oyhk2");
        String username2 = redisService.get("username2");
        System.out.println(username2);
        redisService.del("username2");
        String username2_2 = redisService.get("username2");
        System.out.println(username2_2);//如果为null,那么就是删除数据了

        //dbsize
        long dbSizeEnd = redisService.dbSize();
        System.out.println(dbSizeEnd);

        //清空reids所有数据
        //redisService.flushDB();

    }

}
