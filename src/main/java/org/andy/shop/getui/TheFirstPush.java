package org.andy.shop.getui;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TheFirstPush {
    //首先定义一些常量, 修改成开发者平台获得的值
    private static String appId = "syVq0NWMgYAJFW9JeeJRw9";
    private static String appKey = "uvlZHxgO8Q7Ow2Zp6qRfm2";
    private static String masterSecret = "PSYAz7zJ156VHbHKZHwat3";
    private static String url = "http://sdk.open.api.igexin.com/serviceex";

    //魅族   e92e5982b5271ff90e80f41913817ef6

    public static void main(String[] args) throws IOException {
//        sendAll();

        sendOne();
    }
    private static void sendOne() throws IOException{
        // 推送主类
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        push.connect();

        try {
            ListMessage message = new ListMessage();

            //通知模版：支持TransmissionTemplate、LinkTemplate、NotificationTemplate，此处以NotificationTemplate为例
            //在通知栏显示一条含图标、标题等的通知，用户点击后激活您的应用
            NotificationTemplate template = new NotificationTemplate();

            template.setAppId(appId);                           //应用APPID
            template.setAppkey(appKey);                         //应用APPKEY

            //通知属性设置：如通知的标题，内容
            template.setTitle("此处填写通知标题");                    // 通知标题
            template.setText("此处填写通知内容");                 // 通知内容
            template.setLogo("push.png");               // 通知图标，需要客户端开发时嵌入
            template.setIsRing(false);                  // 收到通知是否响铃，可选，默认响铃
//          template.setIsVibrate(true);                    // 收到通知是否震动，可选，默认振动
            template.setIsClearable(true);              // 通知是否可清除，可选，默认可清除

            template.setTransmissionType(2);                // 收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
            template.setTransmissionContent("你需要透传的内容");  // 透传内容（点击通知后SDK将透传内容传给你的客户端，需要客户端做相应开发）

            message.setData(template);
//          message.setOffline(true);       //用户当前不在线时，是否离线存储，可选，默认不存储
//          message.setOfflineExpireTime(72 * 3600 * 1000);     //离线有效时间，单位为毫秒，可选

            // 接收者
            List<Target> targets = new ArrayList<Target>();
            Target target1 = new Target();
//          Target target2 = new Target();                      //如果需要可设置多个接收者
            target1.setAppId(appId);                            //接收者安装的应用的APPID
            target1.setClientId("e92e5982b5271ff90e80f41913817ef6");                      //接收者的ClientID

            //如需，可设置多个接收者
//          target2.setAppId(APPID2);                           //接收者2安装应用的APPID
//          target2.setClientId(CLIENTID2);                     //接收者2的ClientID

            targets.add(target1);
//          targets.add(target2);

            //推送前通过该接口申请“ContentID”
            String contentId = push.getContentId(message);
            IPushResult ret = push.pushMessageToList(contentId, targets);

            System.out.println(ret.getResponse().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static void sendAll() throws IOException {
        // 新建一个IGtPush实例，传入调用接口网址，appkey和masterSecret
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        push.connect();
        // 新建一个消息类型，根据app推送的话要使用AppMessage
        AppMessage message = new AppMessage();
        // 新建一个推送模版, 以链接模板为例子，就是说在通知栏显示一条含图标、标题等的通知，用户点击可打开您指定的网页
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("欢迎使用个推!");
        template.setText("这是一条推送消息~");
        template.setUrl("http://121.42.153.133/Polaris/");

        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        // 模板设置好后塞进message里并设置，同时设置推送的app id,还可以配置这条message是否支持离线，以及过期时间等
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        // 调用IGtPush实例的pushMessageToApp接口，参数就是上面我们配置的message
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }


}