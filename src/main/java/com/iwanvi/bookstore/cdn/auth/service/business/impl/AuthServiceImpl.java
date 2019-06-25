package com.iwanvi.bookstore.cdn.auth.service.business.impl;
import com.iwanvi.bookstore.cdn.auth.comm.Constants;
import com.iwanvi.bookstore.cdn.auth.comm.config.redis.RedisConstant;
import com.iwanvi.bookstore.cdn.auth.comm.config.redis.RedisComponentOne;
import com.iwanvi.bookstore.cdn.auth.service.business.AuthService;
import com.iwanvi.bookstore.cdn.auth.service.common.BaseServiceImpl;
import com.iwanvi.bookstore.cdn.auth.service.freezwsc.UserCXService;
import com.iwanvi.bookstore.cdn.auth.service.usercenter.UserZWSCService;
import com.iwanvi.bookstore.tools.Utils;
import org.apache.http.HttpStatus;
import org.apache.tomcat.util.bcel.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 鉴权业务
 * @author zzw
 * @since 2019年3月20日16:42:14
 */
@Service
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private transient RedisComponentOne redisComponent;
    /**
     * 中文书城用户
     */
    @Autowired
    private transient UserZWSCService userZWSCService;
    /**
     * 创新用户
     */
    @Autowired
    private transient UserCXService userCXService;



    @Override
    public Integer cdnauth(final String idSign, final Long uid,
                               final Integer cnid, final String bookId, final Long chapterId,
                               final String version, final Integer source) {
        try {
            if (uid <= 0 || cnid <= 0) {
                return HttpStatus.SC_UNAUTHORIZED;
            }
            if (Utils.isNotEmpty(source) && source > Constants.AuthSource.CX_APP) {
                return this.cdnauthCX(idSign, uid, version, source); //免电
            } else {
                return this.cdnauthZWSC(idSign, uid, version, source); //中文书城
            }
        } catch (Exception e) {
            LOGGER.info("鉴权异常 {} {}", e.getMessage(), e);
            return HttpStatus.SC_UNAUTHORIZED;
        }
    }

    /**
     * 鉴权验证(中文书城)
     * @param idSign 用户签名
     * @param uid    用户ID
     * @return Integer 鉴权成功失败状态  200-成功 401-失败
     * @author zzw
     * @since 2019年3月20日16:42:14
     */
    private Integer cdnauthZWSC(final String idSign, final Long uid,
                                  final String version, final Integer source) {
        try {
            //620之后做idSign签名验证 这个逻辑后续中文书城升级完成后，都需要做验证，现在为了兼容以前版本。
            if (checkVersionGT620(version) && Utils.isNotEmpty(source)) {
                String cashKey = RedisConstant.IdSign.getUserIdSignKey(uid, source);
                String cashIdSign = redisComponent.getCache(cashKey);
                if (Utils.isEmpty(cashIdSign) || !cashIdSign.equals(idSign)) {
                    LOGGER.info("用户签名已失效 userId={} cashIdSign={} idSign={}", uid, cashIdSign, idSign);
                    return HttpStatus.SC_UNAUTHORIZED;
                }
            }

            //验证用户是否存在
            Boolean isExist = userZWSCService.checkUserIsExist(uid);
            if (isExist) {
                return HttpStatus.SC_OK;
            }
        } catch (Exception e) {
            LOGGER.info("鉴权异常 {} {}", e.getMessage(), e);
        }
        return HttpStatus.SC_UNAUTHORIZED;
    }

    /**
     * 鉴权验证 （免电）
     * @param idSign 用户签名
     * @param uid    用户ID
     * @return Integer 鉴权成功失败状态  200-成功 401-失败
     * @author zzw
     * @since 2019年3月20日16:42:14
     */
    private Integer cdnauthCX(final String idSign, final Long uid,
                                final String version, final Integer source) {
        try {
            String cashKey = RedisConstant.IdSign.getUserIdSignKey(uid, source);
            String cashIdSign = redisComponent.getCache(cashKey);
            if (Utils.isEmpty(cashIdSign) || !cashIdSign.equals(idSign)) {
                LOGGER.info("用户签名已失效 userId={} cashIdSign={} idSign={}", uid, cashIdSign, idSign);
                return HttpStatus.SC_UNAUTHORIZED;
            }

            //验证用户是否存在
            Boolean isExist = userCXService.checkUserIsExist(uid);
            if (isExist) {
                return HttpStatus.SC_OK;
            }
        } catch (Exception e) {
            LOGGER.info("鉴权异常 {} {}", e.getMessage(), e);
        }
        return HttpStatus.SC_UNAUTHORIZED;
    }






}
