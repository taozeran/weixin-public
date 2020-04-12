package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by taozeran on 2020/3/25/17:24
 */
@Data
@Component
public class WeChatProperties {
    @Value("${wechat.appID}")
    private String appID;
    @Value("${wechat.appSecret}")
    private String appSecret;
    @Value("${wechat.token}")
    private String token;
    @Value("${wechat.aesKey}")
    private String aesKey;
    @Value("${wechat.message.delay.threshold.seconds}")
    private long delayThreshold;
}
