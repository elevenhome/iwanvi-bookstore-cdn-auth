package com.iwanvi.bookstore.cdn.auth.controller;

import com.iwanvi.bookstore.cdn.auth.service.business.AuthService;
import com.iwanvi.bookstore.tools.Utils;
import io.swagger.annotations.*;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 权限-获取资源
 *
 * @author zzw
 * @since 2019年3月20日16:11:20
 */

@Api(description = "权限-获取资源")
@RestController
public class AuthController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private transient AuthService authService;


    @ApiOperation(value = "鉴权-获取章节", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "uid", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "cnid", value = "渠道ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "bookId", value = "图书ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "chapterId", value = "章节ID", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "idSign", value = "签名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "version", value = "版本号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "source", value = "1-中文书城APP 2-中文书城PC 3-中文书城H5 4-中文书城IOS 10-免电APP", required = true, dataType = "In")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "允许访问章节内容", response = Integer.class),
            @ApiResponse(code = 401, message = "鉴权未通过，拒绝访问章节内容", response = Integer.class),
    })
    @GetMapping("/cdnauth")
    public Integer cdnauth(HttpServletResponse response, final String idSign,
                           final String uid, final String cnid,
                           final String bookId, final String chapterId,
                           final String version, final String source) {
        LOGGER.info("章节鉴权请求 idSign={} uid={} cnid={} bookId={} chapterId={} version={} source={}",
                idSign, uid, cnid, bookId, chapterId, version, source);
        Integer httpStatus = HttpStatus.SC_UNAUTHORIZED;
        response.setStatus(HttpStatus.SC_UNAUTHORIZED); //设置默认值
        try {
            if (Utils.isEmpty(idSign) || Utils.isEmpty(uid) || Utils.isEmpty(cnid)
                    || Utils.isEmpty(bookId) || Utils.isEmpty(chapterId)) {
                return httpStatus;
            }
            Long userId = Long.parseLong(uid);
            Integer channelId = Integer.parseInt(cnid);
            Long chapId = Long.parseLong(chapterId);
            Integer src = null;
            if (Utils.isNotEmpty(source)) {
                src = Integer.parseInt(source);
            }
            httpStatus = authService.cdnauth(idSign, userId, channelId, bookId, chapId, version, src);
            response.setStatus(httpStatus);
        } catch (Exception e) {
            LOGGER.info("章节鉴权异常 chapterId={} {}", chapterId, e);
        }
        LOGGER.info("章节鉴权返回 httpStatus={} bookId={} chapterId={}", httpStatus, bookId, chapterId);
        return httpStatus;
    }


}
