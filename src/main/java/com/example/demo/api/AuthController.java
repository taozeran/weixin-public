package com.example.demo.api;

import com.example.demo.utils.SignatureUtils;
import com.example.demo.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;
import java.util.SortedSet;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
    @ApiOperation(value = "测试微信公众号的接口配置信息", notes = "接口配置信息", httpMethod = "GET")
    public String getWxUserInfo(final HttpServletRequest request,@RequestHeader
                                @ApiParam(value = "微信请求的 echostr") @RequestParam(required = true) String echostr,
                                @ApiParam(value = "微信请求的 timstamp") @RequestParam(required = true) String timestamp,
                                @ApiParam(value = "微信请求的 nonce") @RequestParam(required = true) String nonce,
                                @ApiParam(value = "微信请求的 signature") @RequestParam(required = true) String signature
    ) {
        try {
            //只需要把微信请求的 echostr, 返回给微信就可以
            log.info("测试来过===================" + echostr);
            boolean signValid = SignatureUtils.checkSign(echostr, timestamp, nonce, signature);
            if (signValid) {
                return echostr;
            } else {
                log.error("echostr: {}, nonce: {}, sign check failed",echostr,nonce);
                return null;
            }

        } catch (Exception e) {
            log.info("测试微信公众号的接口配置信息发生异常：", e);
            return "系统异常！";
        }
    }
}
