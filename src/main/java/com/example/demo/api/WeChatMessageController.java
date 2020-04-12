package com.example.demo.api;

import com.example.demo.config.WeChatProperties;
import com.example.demo.utils.SignatureUtils;
import com.example.demo.web.dto.req.WechatMessage;
import com.example.demo.web.dto.res.OutTextMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.SecurityDefinition;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpCardService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.card.WxMpCardCreateRequest;
import me.chanjar.weixin.mp.bean.card.WxMpCardCreateResult;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeChatMessageController {
    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/test")
    public String test(final HttpServletRequest request, final HttpServletResponse response) throws WxErrorException {
        HttpSession session = request.getSession();
        Object skdjg = session.getAttribute("skdjg");
        session.setAttribute("a", "b");
        System.out.println(session);

        WxMpCardService cardService = wxMpService.getCardService();
        WxMpCardCreateRequest cardCreateMessage = WxMpCardCreateRequest.fromJson("");
        WxMpCardCreateResult card = cardService.createCard(WxMpCardCreateRequest.fromJson(""));

        return "你好";
    }

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
    public String wxUserInfo(@RequestBody final String xml, @RequestHeader
                             @ApiParam(value = "微信请求的 openid") @RequestParam(required = false) String openid,
                             @ApiParam(value = "微信请求的 encrypt_type") @RequestParam(required = false) String encrypt_type,
                             @ApiParam(value = "微信请求的 msg_signature") @RequestParam(required = false) String msg_signature,
                             @ApiParam(value = "微信请求的 timstamp") @RequestParam(required = true) String timestamp,
                             @ApiParam(value = "微信请求的 nonce") @RequestParam(required = true) String nonce,
                             @ApiParam(value = "微信请求的 signature") @RequestParam(required = true) String signature
    ){
        try {
            boolean signValid = isSignValid(timestamp, nonce, signature);

            if (signValid) {
                log.info(xml);
                if (encrypt_type != null) {

                    //WxMpXmlMessage.fromEncryptedXml(, , , , )
                }
                return null;
                //        OutTextMessage.builder()
                //        .fromUserName(m.getToUserName())
                //        .toUserName(m.getFromUserName())
                //        .content("你好")
                //        .msgType(m.getMsgType())
                //        .timestamp(Instant.now().getEpochSecond())
                //        .build();
            } else {
                log.error("nonce: {}, sign check failed",nonce);
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
