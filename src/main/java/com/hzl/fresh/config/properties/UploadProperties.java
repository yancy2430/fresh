package com.hzl.fresh.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "upload",ignoreUnknownFields = true)
public class UploadProperties {
    public String aliyunOssEndpoint;
    public String aliyunOssAccessKeyId;
    public String aliyunOssAccessKeySecret;
    public String aliyunOssBucketName;
    public String aliyunOssFilePath;
    public String aliyunOssDomain;
}
