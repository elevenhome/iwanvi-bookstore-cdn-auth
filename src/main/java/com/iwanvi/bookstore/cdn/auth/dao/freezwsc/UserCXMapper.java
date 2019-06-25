package com.iwanvi.bookstore.cdn.auth.dao.freezwsc;

import com.iwanvi.bookstore.cdn.auth.po.freezwsc.UserCX;

public interface UserCXMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCX record);

    int insertSelective(UserCX record);

    UserCX selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCX record);

    int updateByPrimaryKey(UserCX record);
}