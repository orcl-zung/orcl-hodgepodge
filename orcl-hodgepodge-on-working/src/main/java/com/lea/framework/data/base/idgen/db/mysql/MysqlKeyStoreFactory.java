package com.lea.framework.data.base.idgen.db.mysql;

import com.lea.framework.data.base.idgen.db.KeyStoreFactory;
import com.lea.framework.data.base.idgen.db.mysql.store.MysqlCachedCodeStore;
import com.lea.framework.data.base.idgen.db.mysql.store.MysqlCachedKeyStore;
import com.lea.framework.data.base.idgen.db.mysql.store.MysqlSimpleCodeStore;
import com.lea.framework.data.base.idgen.db.mysql.store.MysqlSimpleKeyStore;
import com.lea.framework.data.base.idgen.db.store.CodeStore;
import com.lea.framework.data.base.idgen.db.store.KeyStore;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;


/**
 * @author lea
 * @description
 * @history 2023-04-10 17:44 created by lea
 * @since 2023-04-10 17:44
 */
public class MysqlKeyStoreFactory implements KeyStoreFactory {
    @Value("${jlc.data.idgen.default-keystore.key-cache-enabled:true}")
    private boolean keyCacheEnabled = true;

    public boolean isKeyCacheEnabled() {
        return keyCacheEnabled;
    }

    public void setKeyCacheEnabled(boolean keyCacheEnabled) {
        this.keyCacheEnabled = keyCacheEnabled;
    }

    @Override
    public KeyStore newKeyStore(DataSource dataSource) {
        if(keyCacheEnabled){
            return new MysqlCachedKeyStore(dataSource);
        }else {
            return new MysqlSimpleKeyStore(dataSource);
        }
    }

    @Override
    public CodeStore newCodeStore(DataSource dataSource) {
        if(keyCacheEnabled){
            return new MysqlCachedCodeStore(dataSource);
        }else {
            return new MysqlSimpleCodeStore(dataSource);
        }
    }

    @Override
    public KeyStore batchKeyStore(DataSource dataSource) {
        return new MysqlSimpleKeyStore(dataSource);
    }
}
