package com.tao;

public enum FontEnum {
    /**
     * 微软雅黑
     */
    MSYH("fonts\\msyh.ttf");
    private String name;

    FontEnum(String s) {
        this.name = s;
    }

    public String getName(){
        return this.name;
    }
}
