package com.github.zouzdc.core.factorys;

import com.github.zouzdc.core.interfaces.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZDC
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/12/4 23:15
 */
@Component
public class InfoFactory {

    @Autowired(required = false)
    private Map<String, InfoService> serviceMap = new HashMap<>();

    public InfoService getService(String key) {
        return serviceMap.get(key);
    }

    public Collection<InfoService> getService() {
        return serviceMap.values();
    }
}