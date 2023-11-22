package com.github.zouzdc.pojo.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询基类
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BaseQueryVｏ implements Serializable {

    private int size = 10;
    private int current = 1;
    private List<Long> ids;
    private Map<String, Object> searchMap;

    @JsonIgnore
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @JsonIgnore
    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
    @JsonIgnore
    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
    @JsonIgnore
    public Map<String, Object> getSearchMap() {
        return searchMap==null?new HashMap<>():searchMap;
    }

    public void setSearchMap(Map<String, Object> searchMap) {
        this.searchMap = searchMap;
    }
}