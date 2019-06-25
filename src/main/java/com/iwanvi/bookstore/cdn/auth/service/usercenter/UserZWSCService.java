package com.iwanvi.bookstore.cdn.auth.service.usercenter;



public interface UserZWSCService {

    /**
     * 判断用户是否存在
     * @param userId
     * @return Boolean
     */
    Boolean checkUserIsExist(Long userId);
}