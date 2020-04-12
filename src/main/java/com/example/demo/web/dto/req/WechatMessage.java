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
     *   <MsgType><![CDATA[text]]></MsgType>            //消息类型[text,location,shortvideo,video,voice,image]
     *   <Content><![CDATA[this is a test]]></Content>
     *   <PicUrl><![CDATA[this is a url]]></PicUrl>     //图片消息独有
     *   <MediaId><![CDATA[media_id]]></MediaId>        //消息媒体id，可以调用获取临时素材接口拉取数据。
     *   <Format><![CDATA[Format]]></Format>            //语音消息独有
     *   <Recognition>< ![CDATA[腾讯] ]></Recognition>    //开通语音识别后出现
     *   <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId> //视频消息缩略图id
     *   <Location_X>23.134521</Location_X>
     *   <Location_Y>113.358803</Location_Y>
     *   <Scale>20</Scale>
     *   <Label><![CDATA[位置信息]]></Label>
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

    @JacksonXmlProperty(localName = "PicUrl")
    @JacksonXmlCData
    private String picUrl;

    @JacksonXmlProperty(localName = "MediaId")
    @JacksonXmlCData
    private String mediaId;

    @JacksonXmlProperty(localName = "Format")
    @JacksonXmlCData
    private String format;

    @JacksonXmlProperty(localName = "Recognition")
    @JacksonXmlCData
    private String recognition;

    @JacksonXmlProperty(localName = "ThumbMediaId")
    @JacksonXmlCData
    private String thumbMediaId;

    @JacksonXmlProperty(localName = "Location_X")
    private float locationX;

    @JacksonXmlProperty(localName = "Location_Y")
    private float locationY;

    @JacksonXmlProperty(localName = "Scale")
    private int scale;

    @JacksonXmlProperty(localName = "Label")
    @JacksonXmlCData
    private String label;

    @JacksonXmlProperty(localName = "MsgId")
    private long msgId;
}
