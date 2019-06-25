package com.iwanvi.bookstore.cdn.auth.controller;


import com.alibaba.fastjson.JSONObject;

import com.iwanvi.bookstore.cdn.auth.comm.ResultEnum;
import com.iwanvi.bookstore.cdn.auth.comm.RetInfo;
import com.iwanvi.bookstore.cdn.auth.service.business.IdSignService;
import com.iwanvi.bookstore.cdn.auth.vo.IdSignVo;
import com.iwanvi.bookstore.tools.Utils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户签名")
@RestController
public class IdSignController extends BaseController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IdSignController.class);

    @Autowired
    private IdSignService idSignService;

    @ApiOperation(value = "获取用户签名和签名生成时间", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "source", value = "1-中文书城APP 2-中文书城PC 3-中文书城H5 4-中文书城IOS 10-免电APP", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "Long")
    })
    @ApiResponses({@ApiResponse(code = 0, message = "", response = IdSignVo.class)})
    @RequestMapping("/getUserIdSign")
    public IdSignVo getUserIdSign(final Long userId, final Integer source){
        IdSignVo baseResponse = new IdSignVo(ResultEnum.FAIL);
        LOGGER.info("获取用户签名请求 userId={} source={}",userId, source);
        try {
            if (Utils.isEmpty(userId) || Utils.isEmpty(source)) {
                baseResponse.setRetInfo(RetInfo.PARA_ERROR);
                return baseResponse;
            }
            String idSign = idSignService.getUserIdSingAndTime(userId, source);
            baseResponse.setResultEnum(ResultEnum.SUCC);
            baseResponse.setResult(idSign);
        } catch (Exception e) {
            baseResponse.setRetInfo(RetInfo.SYSTEM_ERROR);
            LOGGER.info("获取用户签名和签名生成异常 userId={} {}",userId, e);
        }
        LOGGER.info("获取ID签名返回 = {}", JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }
}
