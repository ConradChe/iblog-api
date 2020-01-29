package com.iblog.util;

public class IdCreateUtil {

    public static String getCode(String prefix) {
        //先获取到端口号
        String port = IpAndPortUtil.getLocalPort();

        //获取随机数
        Long nextId = IdWorker.getInstance().nextId();

        if (prefix == null) {
            return port + nextId;
        }
        //拼接code
        String code = prefix + port + nextId;
        return code;
    }

}
