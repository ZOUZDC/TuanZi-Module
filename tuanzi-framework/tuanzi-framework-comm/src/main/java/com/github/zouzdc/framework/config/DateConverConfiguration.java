package com.github.zouzdc.framework.config;

import cn.hutool.core.date.DateUtil;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.Parser;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;


/**
 * @description Date类型数据 针对于PathVariable、RequestParam参数的反序列化
 * @version 1.0.0
 * @date 2023/12/2 22:25
 * @author ZDC
 */
@Configuration
public class DateConverConfiguration implements WebMvcConfigurer {


    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebConversionService service =(WebConversionService)registry;

        //LocalDateTime类型反序列化
        service.addParser(new Parser<LocalDateTime>() {
            @Override
            public LocalDateTime parse(String text, Locale locale) {
                return DateUtil.parse(text).toLocalDateTime();
            }
        });
        //Date类型反序列化
        service.addParser(new Parser<Date>() {
            @Override
            public Date parse(String text, Locale locale) {
                return DateUtil.parse(text).toJdkDate();
            }
        });
        //LocalDate类型反序列化
        service.addParser(new Parser<LocalDate>() {
            @Override
            public LocalDate parse(String text, Locale locale) {
                return DateUtil.parse(text).toLocalDateTime().toLocalDate();
            }
        });

        WebMvcConfigurer.super.addFormatters(registry);
    }

}