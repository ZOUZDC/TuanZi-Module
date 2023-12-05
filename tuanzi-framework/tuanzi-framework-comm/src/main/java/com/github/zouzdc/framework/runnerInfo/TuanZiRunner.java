package com.github.zouzdc.framework.runnerInfo;

import cn.hutool.core.lang.Pair;
import com.github.zouzdc.core.factorys.InfoFactory;
import com.github.zouzdc.core.interfaces.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ZDC
 * @Description 团子启动信息展示
 * @Version 1.0.0
 * @Date 2023/12/4 22:23
 */
@Slf4j
@Component
public class TuanZiRunner implements CommandLineRunner {

    @Autowired
    private InfoFactory infoFactory;

    @Autowired
    private Environment env;

    @Override
    public void run(String... args) throws Exception {
        String context = """
                          
                ---------------------------------------------
                小团子
                版本:%s
                当前激活配置:%s
                接口文档:%s
                ---------------------------------------------
                """.formatted(
                p("tuanzi.version"),
                p("spring.profiles.active"),
                peq("springdoc.swagger-ui.enabled", "false", "false")?"未启动":p("springdoc.swagger-ui.path")

        );

        log.info(context);
        for (InfoService infoService : infoFactory.getService()) {
            List<Pair<String, String>> runnerInfo = infoService.getRunnerInfo();
            for (Pair<String, String> pair : runnerInfo) {
                System.out.println(pair.getValue());
            }
        }
    }

    private String p(String property){
        return env.getProperty(property);
    }
    private String p(String property ,String defaultValue){
        return env.getProperty(property,defaultValue);
    }
    private boolean peq(String property ,String defaultValue,String reducedValue){
        return Objects.equals(p(property, defaultValue), reducedValue);
    }

}