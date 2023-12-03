package com.github.zouzdc.core.constant;

import cn.hutool.core.collection.CollectionUtil;

import java.util.Set;

public class BizConstant {

    /**
     * BaseController中方法的数据权限
     * 只是用来控制总体的增改查删, 和业务控制无关
     */
    public static Set<String> curdAll = CollectionUtil.newHashSet("C","U","R","D");



}