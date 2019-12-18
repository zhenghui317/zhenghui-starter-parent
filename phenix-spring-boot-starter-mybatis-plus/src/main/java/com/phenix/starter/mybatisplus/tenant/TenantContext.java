package com.phenix.starter.mybatisplus.tenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 租户内容
 *
 * @author phenix
 * @date 2019-11-21
 */
public class TenantContext {
    private static Logger logger = LoggerFactory.getLogger(TenantContext.class);

    private static ThreadLocal<Long> currentTenant = new ThreadLocal<>();

    /**
     * 设置当前租户id
     *
     * @param tenantId
     */
    public static void setCurrentTenant(Long tenantId) {
        logger.debug("Setting tenant to " + tenantId);
        currentTenant.set(tenantId);
    }

    /**
     * 获取当前租户id
     */
    public static Long getCurrentTenant() {
        return currentTenant.get();
    }

    /**
     * 清除租户信息
     */
    public static void clear() {
        currentTenant.set(null);
    }
}
