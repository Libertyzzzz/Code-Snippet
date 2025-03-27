package com.xu.code.practice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "nacos-dynamic-config")
@Component
@Data
@RefreshScope
public class NacosDynamicProperties {

    /**
     * @Author liberty
     * @Date 2025/3/26 21:32
     */
    private Integer id = 1;

}
