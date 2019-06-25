package com.iwanvi.bookstore.cdn.auth.service.business;


import com.iwanvi.bookstore.cdn.auth.service.common.BaseService;

/**
 * IdSign服务
 *
 * @author zzw
 * @since 2018年8月11日11:41:05
 */
public interface IdSignService extends BaseService {


    /**
     * @param userId 用户ID
     * @return id签名和签名生成时间
     * @author zzw
     * @since 2018年8月11日11:41:05
     */
    String getUserIdSingAndTime(Long userId, Integer source);

    /**
     * 获取用户ID签名
     * @param userId
     * @return String IdSign返回
     * @author zzw
     * @since 2018年8月11日11:41:05
     */
    String getUserIdSign(Long userId, Integer source);


}

