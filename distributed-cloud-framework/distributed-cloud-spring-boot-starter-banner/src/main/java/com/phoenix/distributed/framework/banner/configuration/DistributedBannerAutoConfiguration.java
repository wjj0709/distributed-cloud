package com.phoenix.distributed.framework.banner.configuration;

import com.phoenix.distributed.framework.banner.BannerApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author wjj-phoenix
 * @since 2025-09-16
 */
@AutoConfiguration
// @AutoConfigureOrder(AutoConfigureOrderConst.FRAMEWORK_BANNER)
public class DistributedBannerAutoConfiguration {
    /**
     * @return banner 打印器
     */
    @Bean
    public BannerApplicationRunner bannerApplicationRunner() {
        return new BannerApplicationRunner();
    }
}
