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
 * 事件处理器
 */
@Component
public class SubscribeHandler implements WxMpMessageHandler {
    /**
     * 关注事件
     * @param wxMessage
     * @param context
     * @param wxMpService
     * @param sessionManager
     * @return
     * @throws WxErrorException
     */
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        // 构造响应消息对象
        return WxMpXmlOutMessage.TEXT().content("感谢关注鸹貔说").fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser()).build();
    }
}
