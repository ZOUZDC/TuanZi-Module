package com.github.zouzdc.framework.domain.base;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zouzdc.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;


public class BaseController<T extends BaseEntity, V extends BaseQueryVo, S extends BaseService<T, V>> {

    @Autowired(required = false)
    private S baseService;


     HashSet<String> authSet =CollectionUtil.newHashSet();



    @PostMapping("/page")
    public R page(@RequestBody V vo) {
        if(!authSet.contains("R")){
            return R.err("无权限");
        }

        Page<T> page = new Page<>(vo.getCurrent(), vo.getSize());
        page.addOrder( OrderItem.desc("ts"));

        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        MapUtil.removeNullValue(vo.getSearchMap());
        pageWrappers(wrapper, vo);

        baseService.page(page, wrapper);
        Object result = pageResult(page);
        if (result != null) {
            return R.ok(result);
        }
        return R.ok(page);
    }

    //page查询参数
    private void pageWrappers(LambdaQueryWrapper<T> wrapper, V vo) {

    }

    //page返回数据自定义格式
    private Object pageResult(Page<T> page) {
        return null;
    }


    @GetMapping(value = "/detail/{id}")
    public R detail(@PathVariable Long id) {
        if(!authSet.contains("R")){
            return R.err("无权限");
        }

        T one = baseService.getById(id);
        Object result = detailResult(one);
        if (result != null) {
            return R.ok(result);
        }
        return R.ok(one);
    }

    //返回数据自定义格式
    private Object detailResult(T one) {
        return null;
    }


    @PostMapping(value = "/save")
    public R save(@RequestBody V vo) {
        if(!authSet.contains("C")){
            return R.err("无权限");
        }

        if (vo.getId() != null) {
            return R.err("参数错误");
        }
        T one = saveOrUpdateHandle(vo, false);
        return R.ok(baseService.save(one));
    }


    @PostMapping(value = "/update")
    public R update(@RequestBody V vo) {
        if(!authSet.contains("U")){
            return R.err("无权限");
        }

        if (vo.getId() == null) {
            return R.err("参数错误");
        }
        T one = saveOrUpdateHandle(vo, true);
        return R.ok(baseService.updateById(one));
    }

    private T saveOrUpdateHandle(V vo, boolean update) {
        return BeanUtil.copyProperties(vo, getTClass());
    }


    @PostMapping(value = "/delete")
    public R delete(@RequestBody V vo) {
        if(!authSet.contains("D")){
            return R.err("无权限");
        }

        if (vo.getId() == null) {
            return R.err("参数错误");
        }
        return R.ok(baseService.removeById(vo.getId()));
    }

    @PostMapping(value = "/deleteBatch")
    public R deleteBatch(@RequestBody V vo) {
        if(!authSet.contains("D")){
            return R.err("无权限");
        }

        if (CollectionUtil.isEmpty(vo.getIds())) {
            return R.err("参数错误");
        }
        return R.ok(baseService.removeBatchByIds(vo.getIds()));
    }



    public Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }


}