package com.phenix.starter.mybatisplus.autoconfigure;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@Component
public class MybatisPlusObjectHandler implements MetaObjectHandler {
    private static final String FIELD_CREATE_TIME = "createTime";
    private static final String FIELD_UPDATE_TIME = "updateTime";
    private static final String FIELD_LOGIN_DELETE = "isDelete";

    /**
     * 新增的时候自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(FIELD_CREATE_TIME, LocalDateTime.now(), metaObject);
        this.setFieldValByName(FIELD_UPDATE_TIME, LocalDateTime.now(), metaObject);
        this.setFieldValByName(FIELD_LOGIN_DELETE, false, metaObject);
    }

    /**
     * 更新的时候自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(FIELD_UPDATE_TIME, LocalDateTime.now(), metaObject);
    }
}
