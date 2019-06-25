package com.iwanvi.bookstore.cdn.auth.service.freezwsc;



public interface UserCXService {


    /**
     * 判断用户是否存在
     * @param userId
     * @return Boolean
     */
    Boolean checkUserIsExist(Long userId);


}