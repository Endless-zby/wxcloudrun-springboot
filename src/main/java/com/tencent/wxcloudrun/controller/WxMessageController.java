package com.tencent.wxcloudrun.controller;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RestController("/wx/message")
public class WxMessageController {

    final Logger logger = LoggerFactory.getLogger(WxMessageController.class);

    @GetMapping(value = "/sendMessage")
    public void sendMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody WxMpXmlMessage wxMpXmlMessage) {
        String wxSource = request.getHeader("x-wx-source");
        if(StringUtils.isBlank(wxSource)){
            logger.info("WxMessageController.sendMessage this message not from Wechat, attention please !!!");
        }
        if(WxConsts.XML_MSG_TEXT.equals(wxMpXmlMessage.getMsgType())){
            // 文本消息处理
        }
        if(WxConsts.XML_MSG_EVENT.equals(wxMpXmlMessage.getMsgType())){
            // 事件消息处理
        }
        if(WxConsts.XML_MSG_LINK.equals(wxMpXmlMessage.getMsgType())){
            // 链接消息处理
        }
        if(WxConsts.XML_MSG_IMAGE.equals(wxMpXmlMessage.getMsgType())){
            // 图片消息处理
        }
        if(WxConsts.XML_MSG_VIDEO.equals(wxMpXmlMessage.getMsgType())){
            // 视频消息处理
        }

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService




    }

}
