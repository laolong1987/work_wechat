package com.web.component.wechat.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by admin on 2017/8/22.
 */
public class Article {

    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "图文消息缩略图", required = true)
    private String thumb_media_id;

    @ApiModelProperty(value = "作者", required = false)
    private String author;

    @ApiModelProperty(value = "“阅读原文”链接", required = false)
    private String content_source_url;

    @ApiModelProperty(value = "图文消息的内容,支持html标签，不超过666 K个字节", required = true)
    private String content;

    @ApiModelProperty(value = "描述", required = false)
    private String digest;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent_source_url() {
        return content_source_url;
    }

    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
