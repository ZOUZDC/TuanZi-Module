package com.github.zouzdc.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fhs.core.trans.vo.TransPojo;
import com.github.zouzdc.pojo.base.SuperIdEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity基类
 */
@Data
@NoArgsConstructor
public abstract class BaseEntity implements SuperIdEntity,TransPojo {

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date ts;

    private Integer vs;



}