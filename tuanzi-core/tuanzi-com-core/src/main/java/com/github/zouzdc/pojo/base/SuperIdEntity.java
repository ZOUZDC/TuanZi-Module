package com.github.zouzdc.pojo.base;

import java.io.Serializable;

/**
 * Entity基类
 */
public interface SuperIdEntity extends Serializable {

    //id前端精度丢失问题,请通过具体的配置解决
    Long id = null;

     default  Long getId() {
        return id;
    }
     default void setId(Long id) {
        id=id;
    }

}