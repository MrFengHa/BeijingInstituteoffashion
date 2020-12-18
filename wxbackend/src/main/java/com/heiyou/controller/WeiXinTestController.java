package com.heiyou.controller;

import com.heiyou.config.WeiXinConfig;
import com.heiyou.util.AesException;
import com.heiyou.util.SHA1;
import com.heiyou.util.WeiXinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/17 20:54
 */
@RestController
public class WeiXinTestController {
    @Autowired
    WeiXinConfig weiXinConfig;

    @Autowired
    WeiXinUtil weiXinUtil;

    @GetMapping("wxtest")
    public String test(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) {
        try {
            String msgSignature = SHA1.getSHA1(weiXinConfig.getToken(), timestamp, nonce, "");
            if (msgSignature.equals(signature)) {
                return echostr;
            } else {
                System.out.println("失败");
            }
        } catch (AesException e) {
            e.printStackTrace();
        }
        return "你好失败了";

    }

    @RequestMapping("hello")
    public String helloPage(){

        return "hello";
    }
}
