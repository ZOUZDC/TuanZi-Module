package com.github.zouzdc.base;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;


public interface BaseService<E,V> extends IService<E> {


    /**
     * @description 自定义保存方法,具体的实现类需要使用 @Transactional(rollbackFor = Exception.class)
     * @version 1.0.0
     * @date 2023/11/24 1:20
     * @author ZDC
     */
    default void saveInfo(V vo){};

    /**
     * @description 自定义更新方法,具体的实现类需要使用 @Transactional(rollbackFor = Exception.class)
     * @version 1.0.0
     * @date 2023/11/24 1:20
     * @author ZDC
     */
    default void updateInfo(V vo){};

}