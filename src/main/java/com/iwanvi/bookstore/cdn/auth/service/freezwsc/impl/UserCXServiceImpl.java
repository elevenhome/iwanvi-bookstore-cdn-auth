package com.iwanvi.bookstore.cdn.auth.service.freezwsc.impl;


import com.iwanvi.bookstore.cdn.auth.comm.config.redis.RedisComponentOne;
import com.iwanvi.bookstore.cdn.auth.comm.config.redis.RedisConstant;
import com.iwanvi.bookstore.cdn.auth.dao.freezwsc.UserCXMapper;
import com.iwanvi.bookstore.cdn.auth.po.freezwsc.UserCX;
import com.iwanvi.bookstore.cdn.auth.service.freezwsc.UserCXService;
import com.iwanvi.bookstore.tools.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCXServiceImpl implements UserCXService {

    @Autowired
    private transient RedisComponentOne redisComponentOne;
    @Autowired
    private transient UserCXMapper userCXMapper;

    @Override
    public Boolean checkUserIsExist(Long userId){
        String key = RedisConstant.User.getUserIsExistCx(userId);
        String userIdCash = redisComponentOne.getString(key);
        if (Utils.isNotEmpty(userIdCash)) {
            return true;
        }
        UserCX user = userCXMapper.selectByPrimaryKey(userId.intValue());
        if (Utils.isNotEmpty(user)) {
            redisComponentOne.setExpireCache(key, userId, RedisConstant.Expire.MINUTE_TWENTY);
            return true;
        }
        return false;
    }


}