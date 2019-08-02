package com.leyou.common.enums;

/**
 * 把常量修改为枚举
 * @author hejia
 * @Classname MessageEnums
 * @Date 2019/8/2 0002 11:37
 */
public enum  MessageEnums {

    IS_RIGHT((byte)1),
    IS_FAULT((byte)0);

    private Byte code;

    public Byte getCode() {
        return code;
    }

    MessageEnums(Byte code) {
        this.code = code;
    }
}
