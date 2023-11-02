package com.github.zouzdc.pojo;

import java.util.HashMap;

/**
 * @description 统一返回对象   200成功 3xx权限 5xx错误
 * @version 1.0.0
 * @date 2023/11/2 23:33
 * @author ZDC
 */
public class R extends HashMap<String, Object> {

    public R(int code, String msg, Object data) {
        this.put("code", code);
        this.put("msg", msg);
        this.put("data", data);
        // 当500时存在
        //this.put("err", "");
    }



    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }


    public static R ok() {
        return new R(200, "OK", null);
    }

    /***
     * 操作成功,当参数为1个,则将数据直接指向data字典, 当 参数为偶数个时,将单数参数设置为Map的key,每个随后的参数设置为Value
     * @param data
     * @return
     */
    public static R ok(Object... data) {
        return new R(200, "OK", integrationData(data));
    }


    /*错误信息*/
    public static R err(String msg) {
        return new R(500, msg, null);
    }

    public static R err(Throwable ex) {
        return new R(500, ex.getMessage(), null).addErr(ex);
    }


    private R addErr(Throwable ex){
        if (ex.getStackTrace()!=null && ex.getStackTrace().length>0) {
            /* 报错内容加密
            String msg=ex.getStackTrace()[0].toString();
            try {
                msg = Base64.getEncoder().encodeToString(msg.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }*/
            this.put("err",ex.getMessage() +" -> "+ex.getStackTrace()[0].toString());
        }else{
            this.put("err",ex.getMessage());
        }
        return this;
    }

    private static Object integrationData(Object... data) {

        if (data == null || data.length == 0) {
            return null;
        }

        int length = data.length;

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
        throw new RuntimeException("返回结果参数数量不正确");
    }
}