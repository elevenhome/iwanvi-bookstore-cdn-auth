package com.iwanvi.bookstore.cdn.auth.comm;

import java.io.Serializable;

/**
 * 响应信息中文描述
 * @author zzw
 * @since 2018年10月10日18:37:51
 */
public class RetInfo implements Serializable {

    public static final String PARA_ERROR = "请求参数有误";

    public static final String PARA_LESS_ERROR = "请求关键参数缺失";

    public static final String NO_LOGIN = "请连接网络或退出重新进入";

    public static final String BOOK_OFF_LINE = "该书已经下架了哟";

    public static final String GET_CATALOG_FAIL = "获取目录失败，请稍后再试";

    public static final String SYSTEM_ERROR = "系统繁忙,请稍后再试哟";

    public static final String ACC_NOT_ENOUGH = "账户余额不足了哟，请充值";

    public static final String CASH_NOT_ENOUGH = "代金券余额不足了哟，请充值";


}
