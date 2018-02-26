package org.rapid.sample.soa.deploy;

import org.rapid.dubbo.DubboConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@Configuration
@DubboComponentScan("org.rapid.sample.soa.service")
@Import(DubboConfig.class)
public class SoaConfig {

}
