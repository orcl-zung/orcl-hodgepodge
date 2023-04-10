package com.lea.framework.data.base.idgen.db.store;

import java.util.List;

/**
 * @author lea
 * @description
 * @history 2023-04-10 17:41 created by lea
 * @since 2023-04-10 17:41
 */
public interface KeyStore {

    long getNextKey(String key);

    List<Long> getBatchKey(String key, Integer number);

}
