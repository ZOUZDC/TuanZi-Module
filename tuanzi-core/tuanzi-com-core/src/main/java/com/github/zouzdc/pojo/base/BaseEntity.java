package com.github.zouzdc.pojo.base;

import com.fhs.core.trans.vo.TransPojo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity基类
 */
@Data
@NoArgsConstructor
public abstract class BaseEntity implements TransPojo {

    private  Long id;

    private String createBy;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String updateBy;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


    private Integer delFlag;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ts;

    /*version*/
    private Integer vs;

}