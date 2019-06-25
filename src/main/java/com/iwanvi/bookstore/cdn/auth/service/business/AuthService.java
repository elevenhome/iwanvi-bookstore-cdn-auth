package com.iwanvi.bookstore.cdn.auth.service.business;


import com.iwanvi.bookstore.cdn.auth.service.common.BaseService;

/**
 * 鉴权
 * @author zzw
 * @since 2019年3月19日01:34:36
 */
public interface AuthService extends BaseService {

    /**
     * 鉴权
     *
     * @param idSign    用户签名
     * @param uid       用户ID
     * @param cnid      渠道ID
     * @param bookId    图书ID
     * @param chapterId 章节ID
     * @param version 版本号
     * @param source  来源 1-中文书城APP 2-中文书城pc 3-中文书城H5 4-中文书城IOS 10-免电APP
     * @return Integer 鉴权成功失败状态  200-成功 401-失败
     * @author zzw
     * @since 2019年3月20日16:42:14
     */
    Integer cdnauth(final String idSign, final Long uid, final Integer cnid,
                        final String bookId, final Long chapterId, final String version, final Integer source);

}
