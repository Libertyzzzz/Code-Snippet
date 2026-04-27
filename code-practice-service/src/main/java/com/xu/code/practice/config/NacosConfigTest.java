//package com.xu.code.practice.config;
//
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.exception.NacosException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//@Slf4j
//public class NacosConfigTest implements CommandLineRunner {
//
//    /**
//     * @Author liberty
//     * @Date 2025/3/27 22:32
//     */
//    @Resource
//    private ConfigService configService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            String dataId = "code-snippet-local.yaml";
//            String group = "DEFAULT_GROUP";
//            String config = configService.getConfig(dataId, group, 5000);
//            if (config != null) {
//                log.info("Successfully retrieved config: " + config);
//            } else {
//                log.warn("Config not found for dataId: " + dataId + ", group: " + group);
//            }
//        } catch (NacosException e) {
//            log.error("Failed to connect to Nacos server: " + e.getMessage());
//
//        }
//    }
//
//}
