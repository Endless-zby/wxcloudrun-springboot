package com.tencent.wxcloudrun.controller;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/wx/message", produces = "application/xml; charset=UTF-8")
public class WxMessageController {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WxMpMessageRouter wxMpMessageRouter;
    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;

    final Logger logger = LoggerFactory.getLogger(WxMessageController.class);

    @PostMapping(value = "/sendMessage", produces = "application/xml; charset=UTF-8")
    public String sendMessage(HttpServletRequest request,
                              @RequestBody String requestBody,
                              @RequestParam(value = "signature", required = false) String signature,
                              @RequestParam(value = "timestamp", required = false) String timestamp,
                              @RequestParam(value = "nonce", required = false) String nonce) {
        String wxSource = request.getHeader("x-wx-source");
        if(StringUtils.isBlank(wxSource)){
            // 非微信内网请求  需要进行校验
            logger.info("WxMessageController.sendMessage this message not from Wechat, attention please !!!");
            if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
                throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
            }

        }
        // 解析消息体，封装为对象
        WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
        WxMpXmlOutMessage outMessage;
        try {
            // 将消息路由给对应的处理器，获取响应
            outMessage = wxMpMessageRouter.route(inMessage);
        } catch (Exception e) {
            logger.error("微信消息路由异常", e);
            outMessage = null;
        }
        // 将响应消息转换为xml格式返回
        return outMessage == null ? "" : outMessage.toXml();

    }

}
