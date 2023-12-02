package com.github.zouzdc.pojo;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Data
@ToString
public class TestXx {
    private String name;
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;
    private Date date;
    private Long longu;
    private long longl;
    private BigInteger bigInteger;
    private Integer integer;





}
