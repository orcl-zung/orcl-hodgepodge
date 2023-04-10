package com.lea.framework.data.base.idgen.db;

import com.lea.framework.data.base.idgen.db.store.CodeStore;
import com.lea.framework.data.base.idgen.db.store.KeyStore;

import javax.sql.DataSource;


/**
 * @author lea
 * @description
 * @history 2023-04-10 17:59 created by lea
 * @since 2023-04-10 17:59
 */
public interface KeyStoreFactory {

    /**
     * 创建新的KeyStore实例
     */
    KeyStore newKeyStore(DataSource dataSource);

    CodeStore newCodeStore(DataSource dataSource);

    KeyStore batchKeyStore(DataSource dataSource);

}
