package com.github.zouzdc.config;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.github.zouzdc.constant.CoreConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Date类型数据 配置
 *
 * @author ZDC
 * @date 2023/12/2 16:08
 */
@Configuration
public class DateConverConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        for (HttpMessageConverter<?> converter : converters) {
            if (MappingJackson2HttpMessageConverter.class.isAssignableFrom(converter.getClass())) {

               /* JsonDeserializer<Date> jsonDeserializerDate = new JsonDeserializer<>() {
                    @Override
                    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
                        return DateUtil.parse(p.getText()).toJdkDate();
                    }
                };
                JsonDeserializer<LocalDateTime> jsonDeserializerLocalDateTime = new JsonDeserializer<>() {
                    @Override
                    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
                        return DateUtil.parse(p.getText()).toLocalDateTime();
                    }
                };

                ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();

                SimpleModule simpleModule = new SimpleModule();

                simpleModule.addDeserializer(LocalDateTime.class,jsonDeserializerLocalDateTime);

                simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(CoreConstant.DATE_FORMAT)));

                simpleModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(CoreConstant.TIME_FORMAT)));

                simpleModule.addDeserializer(Date.class, jsonDeserializerDate);

                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                objectMapper.registerModule(simpleModule);*/
            }
        }

    }
}
