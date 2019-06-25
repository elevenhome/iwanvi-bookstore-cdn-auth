package com.iwanvi.bookstore.cdn.auth.service.business.impl;


import com.iwanvi.bookstore.cdn.auth.comm.config.redis.RedisConstant;
import com.iwanvi.bookstore.cdn.auth.comm.config.redis.RedisComponentOne;
import com.iwanvi.bookstore.cdn.auth.service.business.IdSignService;
import com.iwanvi.bookstore.cdn.auth.service.common.BaseServiceImpl;
import com.iwanvi.bookstore.tools.Base64Utils;
import com.iwanvi.bookstore.tools.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


/**
 * IdSign服务
 * @author zzw
 * @since 2019年6月25日10:48:11
 */
@Service
public class IdSignServiceImpl extends BaseServiceImpl implements IdSignService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IdSignServiceImpl.class);

    /**
     * 加密KEY
     */
    private static final String key = "zwscapp2020";

    /**
     * Redis缓存
     */
    @Autowired
    private transient RedisComponentOne redisComponent;

    /**
     * idSign|当前时间戳
     * @param userId 用户ID
     * @return
     */
    @Override
    public String getUserIdSingAndTime(Long userId, Integer source) {
        long sysNowTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        sb.append(getUserIdSign(userId, source));
        sb.append("|");
        sb.append(sysNowTime);
        return Base64Utils.encode(sb.toString());
    }


    @Override
    public String getUserIdSign(Long userId, Integer source) {
        StringBuffer idSignTemp = new StringBuffer();
        int rannum = new Random().nextInt(999 - 100) + 100;//3位随机数
        //(key + userId + 3位置随机数 + source)  字符串MD5加密  转换为大写
        idSignTemp.append(key).append(userId).append(rannum).append(source);
        String idSign = MD5Util.MD5Encode(idSignTemp.toString(), "UTF-8").toUpperCase();
        //签名添加缓存
        String cashKey = RedisConstant.IdSign.getUserIdSignKey(userId, source);
        redisComponent.setExpireCache(cashKey, idSign, RedisConstant.Expire.MINUTE_TWENTY);
        return idSign;
    }


    public static void main(String[] args) {
        long userId = 123456;
        String idSign = MD5Util.MD5Encode(userId + key, "UTF-8");
        System.out.println(idSign);
        System.out.println(MD5Util.MD5Encode(userId + "", "UTF-8"));
    }



}
