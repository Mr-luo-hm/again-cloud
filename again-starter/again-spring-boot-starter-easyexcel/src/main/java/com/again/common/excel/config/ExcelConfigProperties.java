package com.again.common.excel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = ExcelConfigProperties.PREFIX)
public class ExcelConfigProperties {

    static final String PREFIX = "excel";

    /**
     * 模板路径
     */
    private String templatePath = "excel";

}

