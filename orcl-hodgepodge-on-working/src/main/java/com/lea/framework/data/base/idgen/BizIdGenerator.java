package com.lea.framework.data.base.idgen;

import java.util.List;

/**
 * @author lea
 * @description
 * @history 2023-04-07 18:13 created by lea
 * @since 2023-04-07 18:13
 */
public interface BizIdGenerator {

    long getNextKey(String keyName) throws IdGenerateException;

    String generateCode(String bizCode) throws IdGenerateException;

    String generateCode(String prefix, String bizCode) throws IdGenerateException;

    List<Long> getBatchKey(String keyName, Integer number) throws IdGenerateException;

}
