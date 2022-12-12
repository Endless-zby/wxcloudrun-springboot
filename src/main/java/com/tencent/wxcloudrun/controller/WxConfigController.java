package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.Counter;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController("/wx/config")
public class WxConfigController {

    final Logger logger = LoggerFactory.getLogger(WxConfigController.class);

    /**
     * 获取token
     * @return API response json
     */
    @GetMapping(value = "/checkServer")
    public void checkServer(HttpServletRequest request, HttpServletResponse response) {
        logger.info("/wx/checkServer checkServer request");

        //微信服务器get传递的参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //微信工具服务类
        WxMpService wxService=new WxMpServiceImpl();
        //注入token的配置参数
        /**
         * 生产环境 建议将WxMpInMemoryConfigStorage持久化
         */
        WxMpInMemoryConfigStorage wxConfigProvider=new WxMpInMemoryConfigStorage();
        //注入token值
        wxConfigProvider.setToken("bnfi4kjsh5bdfji56oHO6H0jof3hoGhi");
        wxService.setWxMpConfigStorage(wxConfigProvider);
        boolean flag=wxService.checkSignature(timestamp, nonce, signature); //验证token跟微信配置的是否一样
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("/wx/checkServer checkServer error",e);
            return;
        }
        if(flag){
            out.print(echostr);
        }
        out.close();
    }

}
