package com.iwanvi.bookstore.cdn.auth.dao.usercenter;

import com.iwanvi.bookstore.cdn.auth.po.usercenter.UserZWSC;

public interface UserZWSCMapper {
    int deleteByPrimaryKey(Long id, Long tableSeq);

    int insert(UserZWSC record, Long tableSeq);

    int insertSelective(UserZWSC record, Long tableSeq);

    UserZWSC selectByPrimaryKey(Long id, Long tableSeq);

    int updateByPrimaryKeySelective(UserZWSC record, Long tableSeq);

    int updateByPrimaryKey(UserZWSC record, Long tableSeq);
}