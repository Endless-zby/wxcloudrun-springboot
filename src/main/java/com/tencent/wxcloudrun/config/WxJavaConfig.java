package com.tencent.wxcloudrun.config;

import com.tencent.wxcloudrun.handler.ImageHandler;
import com.tencent.wxcloudrun.handler.SubscribeHandler;
import com.tencent.wxcloudrun.handler.TextHandler;
import com.tencent.wxcloudrun.handler.UnSubscribeHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxJavaConfig {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private TextHandler textHandler;
    @Autowired
    private ImageHandler imageHandler;
    @Autowired
    private SubscribeHandler subscribeHandler;
    @Autowired
    private UnSubscribeHandler unSubscribeHandler;

    @Bean
    public WxMpMessageRouter messageRouter() {
        // 创建消息路由
        final WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
        // 添加文本消息路由
        router.rule().async(false).msgType(WxConsts.XmlMsgType.TEXT).handler(textHandler).end();
        router.rule().async(false).msgType(WxConsts.XmlMsgType.IMAGE).handler(imageHandler).end();
        router.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT).event(WxConsts.EventType.SUBSCRIBE).handler(subscribeHandler).end();
        router.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT).event(WxConsts.EventType.UNSUBSCRIBE).handler(unSubscribeHandler).end();
        return router;
    }

}
