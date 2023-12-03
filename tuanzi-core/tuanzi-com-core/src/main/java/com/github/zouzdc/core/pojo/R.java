package com.github.zouzdc.core.pojo;

import java.util.HashMap;

/**
 * @author ZDC
 * @version 1.0.0
 * @description 统一返回对象  200成功 3xx权限 5xx错误
 * @date 2023/11/2 23:33
 */
public class R<T> extends HashMap<String, Object> {

    public R(int code, String msg, Object data, Object err) {
        this(code, msg, data);
        // 当500时存在
        this.put("err", "");
    }

    public R(int code, String msg, Object data) {
        this.put("code", code);
        this.put("msg", msg);
        this.put("data", data);
    }


    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }


    /**
     * @description 操作成功
     * @version 1.0.0
     * @date 2023/11/9 21:13
     * @author ZDC
     */
    public static R ok() {
        return new R(200, "OK", null);
    }

    /***
     *  操作成功,当参数为1个,则将数据直接指向data字典, 当 参数为偶数个时,将单数参数设置为Map的key,每个随后的参数设置为Value
     * @param data
     * @return
     */
    public static R ok(Object... data) {
        return new R(200, "OK", integrationData(data));
    }

    public static R okMsg(String msg) {
        return new R(200, msg, null);
    }

    /**
     * @description 操作失败 ,并添加失败原因
     * @version 1.0.0
     * @date 2023/11/9 21:18
     * @author ZDC
     */
    public static R err(String msg) {
        return new R(500, msg, null);
    }

    /**
     * @description 操作失败, 系统调用
     * @version 1.0.0
     * @date 2023/11/9 21:19
     * @author ZDC
     */
    public static R err(String msg ,Throwable ex) {
        return new R(500, msg ==null ? "系统错误" : msg, null).addErr(ex);
    }

    /**
     * 获取data数据
     *
     * @return 返回获取到的数据，如果数据为空，则返回null
     */
    public T getData() {
        Object data = this.get("data");
        if (data != null) {
            return (T) data;
        }
        return null;
    }


    private R addErr(Throwable ex) {
        if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
            /* 报错内容加密
            String msg=ex.getStackTrace()[0].toString();
            try {
                msg = Base64.getEncoder().encodeToString(msg.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }*/
            this.put("err", ex.getMessage() + " -> " + ex.getStackTrace()[0].toString());
        } else {
            this.put("err", ex.getMessage());
        }
        return this;
    }


    private static Object integrationData(Object... data) {
        int length = 0;

        if (data == null || (length = data.length) == 0) {
            return null;
        }

        if (length == 1) {
            return data[0];
        }

        if (length % 2 == 0) {
            HashMap<Object, Object> map = new HashMap<>();
            for (int i = 0; i < length; i += 2) {
                map.put(data[i], data[i + 1]);
            }
            return map;
        }
        throw new RuntimeException("参数数量不正确");
    }

}