package com.leyou.manager.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version : 1.0
 * @ClassName: UploadProperties
 * @Description :
 * @auther: hejia
 * @date: 2019/4/16
 */
@Service
@ConfigurationProperties(prefix = "ly.upload")
public class UploadProperties {

    private String baseUrl;

    private List<String> allowTypes;

    /**
     * @return baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl baseUrl
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return allowTypes
     */
    public List<String> getAllowTypes() {
        return allowTypes;
    }

    /**
     * @param allowTypes allowTypes
     */
    public void setAllowTypes(List<String> allowTypes) {
        this.allowTypes = allowTypes;
    }
}
