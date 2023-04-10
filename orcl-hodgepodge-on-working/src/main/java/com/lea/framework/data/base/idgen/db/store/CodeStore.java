package com.lea.framework.data.base.idgen.db.store;

/**
 * @author lea
 * @description
 * @history 2023-04-10 17:41 created by lea
 * @since 2023-04-10 17:41
 */
public interface CodeStore {

    String generateCode(String businessCode);

}
