package com.iwanvi.bookstore.cdn.auth.service.common;

/**
 * 基础Service
 *
 * @author zzw
 * @since 2018年8月11日11:41:05
 */
public interface BaseService {

    /**
     * 版本号转化为数据
     * @param version 字符串版本号
     * @return 数字版本号
     */
    Integer getVersionNum(final String version);

    /**
     * 判断版本号是否大于620
     * @param version 版本
     * @return boolean 大于620返回true  小于返回false
     */
    Boolean checkVersionGT620(String version);

}
