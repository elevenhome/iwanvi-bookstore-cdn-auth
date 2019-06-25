package com.iwanvi.bookstore.cdn.auth.service.usercenter.impl;


import com.iwanvi.bookstore.cdn.auth.comm.config.redis.RedisComponentOne;
import com.iwanvi.bookstore.cdn.auth.comm.config.redis.RedisConstant;
import com.iwanvi.bookstore.cdn.auth.dao.usercenter.UserZWSCMapper;
import com.iwanvi.bookstore.cdn.auth.po.usercenter.UserZWSC;
import com.iwanvi.bookstore.cdn.auth.service.usercenter.UserZWSCService;
import com.iwanvi.bookstore.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserZWSCServiceImpl implements UserZWSCService {

    @Autowired
    private transient RedisComponentOne redisComponentOne;
    @Autowired
    private transient UserZWSCMapper userZWSCMapper;

    @Override
    public Boolean checkUserIsExist(Long userId){

        String key = RedisConstant.User.getUserIsExistZwsc(userId);
        Long userIdCash = redisComponentOne.getCache(key);
        if (Utils.isNotEmpty(userIdCash)) {
            return true;
        }
        UserZWSC user = userZWSCMapper.selectByPrimaryKey(userId, getTableSeq(userId));
        if (Utils.isNotEmpty(user)) {
            redisComponentOne.setExpireCache(key, userId, RedisConstant.Expire.MINUTE_TWENTY);
            return true;
        }
        return false;
    }


    /**
     * 获取用户所在表
     * @param userId 用户ID
     * @return String 表名
     */
    private String getUserTableName(Long userId) {
        if (userId == null || userId <= 0) return "";
        return String.format("user%s", getTableSeq(userId));
    }

    /**
     * 获取表序号
     * @param userId 用户ID
     * @return Long 表序号
     */
    private Long getTableSeq(Long userId) {
        return userId % 1024;
    }

}