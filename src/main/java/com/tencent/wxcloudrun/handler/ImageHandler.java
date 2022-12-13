package com.tencent.wxcloudrun.handler;

import com.tencent.wxcloudrun.controller.WxMessageController;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class ImageHandler implements WxMpMessageHandler {

    final Logger logger = LoggerFactory.getLogger(ImageHandler.class);

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        logger.info("into ImageHandler ---- fromUser: [{}]   toUser: [{}]   mediaId: [{}]",wxMessage.getFromUser(), wxMessage.getToUser(), wxMessage.getMediaId());
        // 原样返回收到的图片
        return WxMpXmlOutMessage.IMAGE().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).mediaId(wxMessage.getMediaId()).build();
    }
}
