package com.github.zouzdc.framework.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.github.zouzdc.core.pojo.base.SuperIdEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity基类
 */
@Data
@NoArgsConstructor
public abstract class BaseEntity implements SuperIdEntity {

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime ts;





}