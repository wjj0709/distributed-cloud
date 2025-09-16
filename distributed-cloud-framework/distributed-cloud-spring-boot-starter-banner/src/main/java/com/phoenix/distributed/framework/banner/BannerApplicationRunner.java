package com.phoenix.distributed.framework.banner;

import com.phoenix.distributed.lang.utils.Threads;
import com.phoenix.distributed.lang.utils.ansi.AnsiAppender;
import com.phoenix.distributed.lang.utils.ansi.style.color.AnsiForeground;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author wjj-phoenix
 * @since 2025-09-16
 */
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {
    @Value("${spring.profiles.active}")
    private String env;

    @Value("${orion.version:1.0}")
    private String version;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String apiPrefix;

    @Value("${springdoc.api-docs.enabled}")
    private Boolean apiDocsEnabled;

    // @Value("${spring.datasource.druid.stat-view-servlet.enabled}")
    // private Boolean druidConsoleEnabled;



    @Override
    public void run(ApplicationArguments args) {
        AnsiAppender appender = AnsiAppender.create().append(AnsiForeground.BRIGHT_GREEN, ":: orion-visor v" + version + " 服务已启动(" + env + ") ::\n");
        // swagger 地址
        if (apiDocsEnabled) {
            appender.append(AnsiForeground.BRIGHT_GREEN, ":: swagger 文档       ").append(AnsiForeground.BRIGHT_BLUE, "http://127.0.0.1:" + port + "/doc.html\n");
        }

        appender.append(AnsiForeground.BRIGHT_GREEN, ":: server 健康检测    ").append(AnsiForeground.BRIGHT_BLUE, "curl -X GET --location \"http://127.0.0.1:" + port + apiPrefix + "/server/bootstrap/health\"");

        Threads.start(() -> {
            Threads.sleep(1000L);
            System.out.println(appender);
        });
    }
}
