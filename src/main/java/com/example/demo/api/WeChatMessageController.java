package com.example.demo.api;

import com.example.demo.config.WeChatProperties;
import com.example.demo.utils.SignatureUtils;
import com.example.demo.web.dto.req.WechatMessage;
import com.example.demo.web.dto.res.ResponseWechatMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeChatMessageController {
    @Autowired
    private WeChatProperties weChatProperties;

    @ResponseBody
    @GetMapping(value = "/message", produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "测试微信公众号的接口配置信息", notes = "接口配置信息", httpMethod = "GET")
    public String getEchostr(final HttpServletRequest request,
                                @ApiParam(value = "微信请求的 echostr") @RequestParam(required = true) String echostr,
                                @ApiParam(value = "微信请求的 timstamp") @RequestParam(required = true) String timestamp,
                                @ApiParam(value = "微信请求的 nonce") @RequestParam(required = true) String nonce,
                                @ApiParam(value = "微信请求的 signature") @RequestParam(required = true) String signature
    ) {
        try {
            boolean signValid = isSignValid(timestamp, nonce, signature);

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

    @ResponseBody
    @PostMapping(value = "/message", consumes = {MediaType.TEXT_XML_VALUE}, produces = MediaType.TEXT_XML_VALUE)
    @ApiOperation(value = "测试微信公众号的接口配置信息", notes = "接口配置信息", httpMethod = "POST")
    public ResponseWechatMessage wxUserInfo(@RequestBody final WechatMessage m, @RequestHeader
                             @ApiParam(value = "微信请求的 openid") @RequestParam(required = true) String openid,
                             @ApiParam(value = "微信请求的 timstamp") @RequestParam(required = true) String timestamp,
                             @ApiParam(value = "微信请求的 nonce") @RequestParam(required = true) String nonce,
                             @ApiParam(value = "微信请求的 signature") @RequestParam(required = true) String signature
    ){
        try {
            boolean signValid = isSignValid(timestamp, nonce, signature);

            if (signValid) {
                log.info(m.toString());
                return ResponseWechatMessage.builder()
                        .fromUserName(m.getToUserName())
                        .toUserName(m.getFromUserName())
                        .content(m.getContent())
                        .msgType(m.getMsgType())
                        .timestamp(Instant.now().getEpochSecond())
                        .build();
            } else {
                log.error("openid: {}, nonce: {}, sign check failed",openid,nonce);
                return null;
            }

        } catch (Exception e) {
            log.error("测试微信公众号的接口配置信息发生异常：", e);
            return null;
        }
    }

    private boolean isSignValid(String timestamp, String nonce,String signature) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //增加认证报文的时效性 todo uncomment
        //if (Long.valueOf(timestamp) - System.currentTimeMillis() / 1000 > weChatProperties.getDelayThreshold()) {
        //    log.error("the wechat request nonce: {}, timestamp: {}, is too late", nonce, timestamp);
        //    return false;
        //}
        return SignatureUtils.checkSign(weChatProperties.getToken(), timestamp, nonce, signature);
    }
}
