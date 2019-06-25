package com.iwanvi.bookstore.cdn.auth.comm;

/**
 * 响应信息码
 * @author zzw
 * @date 2018年7月12日11:24:06
 */


public enum ResultEnum {
    /**
     * 1000以下的不要使用200 300 301  404预留
     * 可以为数字字母组合
     */
    SUCC("0000", "成功"),
    FAIL("9999", "失败");


    private String retCode;

    private String retInfo;


    ResultEnum(String retCode, String retInfo) {
        this.retCode = retCode;
        this.retInfo = retInfo;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetInfo() {
        return retInfo;
    }

    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }
}
