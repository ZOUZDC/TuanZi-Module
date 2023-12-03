package com.github.zouzdc.framework.config;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.github.zouzdc.core.constant.CoreConstant;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class JacksonConfig {



    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {

        JsonSerializer<Date> jsonSerializer = new JsonSerializer<>() {
            @Override
            public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(DateUtil.format(value, CoreConstant.DATE_TIME_FORMAT));
            }
        };

        JsonDeserializer<Date> jsonDeserializer = new JsonDeserializer<>() {
            @Override
            public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
                return DateUtil.parse(p.getText()).toJdkDate();
            }
        };


        return builder -> {
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(CoreConstant.DATE_TIME_FORMAT)));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(CoreConstant.DATE_TIME_FORMAT)));

            builder.serializerByType(LocalDate.class,  new LocalDateSerializer(DateTimeFormatter.ofPattern(CoreConstant.DATE_FORMAT)));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(CoreConstant.DATE_FORMAT)));

//            builder.serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(CoreConstant.TIME_FORMAT)));
//            builder.deserializerByType(LocalTime.class,  new LocalTimeDeserializer(DateTimeFormatter.ofPattern(CoreConstant.TIME_FORMAT)));

            builder.serializerByType(Date.class,jsonSerializer);
            builder.deserializerByType(Date.class, jsonDeserializer);

            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(Long.TYPE,  ToStringSerializer.instance);
            builder.serializerByType(BigInteger.class,  ToStringSerializer.instance);

            builder.failOnUnknownProperties(false);
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }
}