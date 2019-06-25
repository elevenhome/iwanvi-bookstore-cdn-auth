package com.iwanvi.bookstore.cdn.auth.service.common;

import com.iwanvi.bookstore.tools.Utils;

/**
 * 基础Service
 *
 * @author zzw
 * @since 2019年3月10日11:33:16
 */
public class BaseServiceImpl implements BaseService {

    @Override
    public Integer getVersionNum(final String version) {
        if (Utils.isEmpty(version)) {
            return null;
        }
        String v = version.trim().replaceAll("\\.", "");
        return Integer.parseInt(v);
    }


    @Override
    public Boolean checkVersionGT620 (String version){
       Integer v = this.getVersionNum(version);
        if (Utils.isNotEmpty(v) && v >= 620) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        BaseServiceImpl aa  = new  BaseServiceImpl();
        Integer v = aa.getVersionNum("6.1.1");
        System.out.println(v);
    }

}
