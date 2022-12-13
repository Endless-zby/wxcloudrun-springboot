package com.tencent.wxcloudrun.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/wx/config")
public class WxConfigController {

    @Autowired
    private WxMpService wxMpService;


    final Logger logger = LoggerFactory.getLogger(WxConfigController.class);

    /**
     * 获取token
     * @return API response json
     */
    @GetMapping(value = "/checkServer")
    public String checkServer(String signature,String timestamp,String nonce,String echostr) {
        logger.info("/wx/checkServer signature:{} , timestamp:{}, nonce:{}, echostr:{}", signature, timestamp, nonce, echostr);
        // 校验签名
        if (wxMpService.checkSignature(timestamp, nonce, signature)){
            // 校验成功原样返回echostr
            return echostr;
        }
        // 校验失败
        return null;

    }

}
