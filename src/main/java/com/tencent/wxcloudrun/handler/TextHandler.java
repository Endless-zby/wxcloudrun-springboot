package com.tencent.wxcloudrun.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消息处理器
 */
@Component
public class TextHandler implements WxMpMessageHandler {


    /**
     * 文本消息处理器
     * @param wxMessage
     * @param context
     * @param wxMpService
     * @param wxSessionManager
     * @return
     * @throws WxErrorException
     */
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // 接收的消息内容
        String inContent = wxMessage.getContent();
        // 响应的消息内容
        String outContent;
        // 根据不同的关键字回复消息
        if (inContent.contains("游戏")){
            outContent = "仙剑奇侠传";
        }else if (inContent.contains("动漫")){
            outContent = "进击的巨人";
        }else {
            outContent = inContent;
        }
        // 构造响应消息对象
        return WxMpXmlOutMessage.TEXT().content("这是一个测试消息").fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser()).build();
    }
}
