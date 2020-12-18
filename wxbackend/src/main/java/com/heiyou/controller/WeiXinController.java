package com.heiyou.controller;

import com.heiyou.config.WeiXinConfig;
import com.heiyou.util.WeiXinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/18 9:07
 */
@Controller()
@RequestMapping("weixin")
public class WeiXinController {
    @Autowired
    WeiXinConfig weiXinConfig;

    @Autowired
    WeiXinUtil weiXinUtil;

    @RequestMapping("show")
    public String show(@RequestParam String value) {
        System.out.println("进来了aaaa");
        return "redirect:/weixin/getAuthorize?value="+value;
    }

    @GetMapping("getAuthorize")
    public String getAuthorize(@RequestParam String value) {
        System.out.println(value);
        String redirectURL = "http://lszxydh.cn/";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + weiXinConfig.getAppid()
                + "&redirect_uri=" + redirectURL
                + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        System.out.println(url);

        return "redirect:" + url;
    }

}
