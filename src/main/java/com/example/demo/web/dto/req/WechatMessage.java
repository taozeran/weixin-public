package com.example.demo.web.dto.req;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by taozeran on 2020/3/25/17:52
 */
@Data
@NoArgsConstructor
@JacksonXmlRootElement(localName = "xml")
public class WechatMessage {
    /**
     * <xml>
     *   <ToUserName><![CDATA[toUser]]></ToUserName>
     *   <FromUserName><![CDATA[fromUser]]></FromUserName>
     *   <CreateTime>1348831860</CreateTime>
     *   <MsgType><![CDATA[text]]></MsgType>
     *   <Content><![CDATA[this is a test]]></Content>
     *   <MsgId>1234567890123456</MsgId>
     * </xml>
     */
    @JacksonXmlProperty(localName = "ToUserName")
    @JacksonXmlCData
    private String toUserName;

    @JacksonXmlProperty(localName = "FromUserName")
    @JacksonXmlCData
    private String fromUserName;

    @JacksonXmlProperty(localName = "CreateTime")
    private long timestamp;

    @JacksonXmlProperty(localName = "MsgType")
    @JacksonXmlCData
    private String msgType;

    @JacksonXmlProperty(localName = "Content")
    @JacksonXmlCData
    private String content;

    @JacksonXmlProperty(localName = "MsgId")
    private long msgId;
}
