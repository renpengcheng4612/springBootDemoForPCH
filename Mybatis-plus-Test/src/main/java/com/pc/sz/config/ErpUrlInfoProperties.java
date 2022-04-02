package com.pc.sz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("erp-url-info")
public class ErpUrlInfoProperties {

    /**
     * 访问url
     */
    private String erpUrl;
    /**
     * 查询单个部门
     */
    private ApiInfo singleOrganization;
}
