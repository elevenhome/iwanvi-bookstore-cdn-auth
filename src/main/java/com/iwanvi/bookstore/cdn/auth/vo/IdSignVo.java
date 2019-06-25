package com.iwanvi.bookstore.cdn.auth.vo;


import com.iwanvi.bookstore.cdn.auth.comm.BaseResponse;
import com.iwanvi.bookstore.cdn.auth.comm.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  ID签名返回对象
 * @author zzw
 * @since 2018年12月13日10:47:55
 */
@ApiModel(value = "签名返回对象")
public class IdSignVo extends BaseResponse {

    @ApiModelProperty(value = "格式：idSign|当前时间戳（base64编码）;base64解码截取idSign;服务端过期时间20分钟，客户端10分钟刷新一次签名 ")
    public String result;

    public IdSignVo(ResultEnum resultEnum) {
        super(resultEnum);
    }

    @Override
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
