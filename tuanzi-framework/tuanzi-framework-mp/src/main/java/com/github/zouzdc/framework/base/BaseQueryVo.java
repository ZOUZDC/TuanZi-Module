package com.github.zouzdc.framework.base;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询基类
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BaseQueryVo extends BaseEntity {

    private int size = 10;
    private int current = 1;
    private List<String> ids;
    private Map<String, Object> searchMap;

    @JsonIgnore
    @JSONField(serialize=false)
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @JsonIgnore
    @JSONField(serialize=false)
    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
    @JsonIgnore
    @JSONField(serialize=false)
    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
    @JsonIgnore
    @JSONField(serialize=false)
    public Map<String, Object> getSearchMap() {
        return searchMap==null?new HashMap<>():searchMap;
    }

    public void setSearchMap(Map<String, Object> searchMap) {
        this.searchMap = searchMap;
    }
}