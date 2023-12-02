package com.github.zouzdc.pojo.base;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询基类
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class SuperQueryVo implements SuperIdEntity {

    private int size = 10;
    private int current = 1;
    private Map<String, Object> searchMap;


    @JSONField(serialize=false)
    public int getSize() {
        return size<0?10:size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @JSONField(serialize=false)
    public int getCurrent() {
        return current<1?1:current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }


    @JSONField(serialize=false)
    public Map<String, Object> getSearchMap() {
        return searchMap==null?new HashMap<>():searchMap;
    }

    public void setSearchMap(Map<String, Object> searchMap) {
        this.searchMap = searchMap;
    }


}