package com.example.demo.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
    @ApiOperation(value = "测试微信公众号的接口配置信息", notes = "接口配置信息", httpMethod = "GET")
    public String getWxUserInfo(HttpServletRequest request,
                                @ApiParam(value = "微信求的 echostr") @RequestParam(required = true) String echostr
    ) {
        try {
            //只需要把微信请求的 echostr, 返回给微信就可以
            log.info("测试来过===================" + echostr);
            return echostr;
        } catch (Exception e) {
            log.info("测试微信公众号的接口配置信息发生异常：", e);
            return "系统异常！";
        }
    }
}
