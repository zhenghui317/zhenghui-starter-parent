package com.phenix.starter.mybatisplus.datasource;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据源配置解密密码
 */
@Slf4j
public class UmspscDataSource extends HikariDataSource {
    /**
     * 解密的用户名
     */
    private String usernameDecipher;

    /**
     * 解密的密码
     */
    private String passwordDecipher;
    /**
     * 密匙
     */
    private final static String PKEY = "phenix";


    /**
     * 获取加密的用户名
     * @return
     */
    @Override
    public String getUsername() {
        if (StringUtils.isNotBlank(usernameDecipher)) {
            return usernameDecipher;
        }
        String encUsername = super.getUsername();
        if (null == encUsername) {
            return null;
        }
        try {
            //  密文解密，解密方法可以修改
            String key = HexUtil.encodeHexStr(PKEY);
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
            usernameDecipher = aes.decryptStr(encUsername, CharsetUtil.CHARSET_UTF_8);
            return usernameDecipher;
        } catch (Exception e) {
            log.error("数据库用户名解密出错，{" + encUsername + "}");
            return "";
        }
    }

    /**
     * 获取加密的密码
     * @return
     */
    @Override
    public String getPassword() {
        if (StringUtils.isNotBlank(passwordDecipher)) {
            return passwordDecipher;
        }
        String encPassword = super.getPassword();
        if (null == encPassword) {
            return null;
        }
        try {
            //  密文解密，解密方法可以修改
            String key = HexUtil.encodeHexStr(PKEY);
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
            passwordDecipher = aes.decryptStr(encPassword, CharsetUtil.CHARSET_UTF_8);
            return passwordDecipher;
        } catch (Exception e) {
            log.error("数据库密码解密出错，{" + encPassword + "}");
            return "";
        }
    }

}