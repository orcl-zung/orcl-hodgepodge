package com.lea.framework.data.base.idgen.db;

import com.lea.framework.data.base.idgen.BizIdGenerator;
import com.lea.framework.data.base.idgen.IdGenerateException;
import com.lea.framework.data.base.idgen.db.store.CodeStore;
import com.lea.framework.data.base.idgen.db.store.KeyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lea
 * @description
 * @history 2023-04-10 17:56 created by lea
 * @since 2023-04-10 17:56
 */
public class DatabaseBizIdGenerator implements BizIdGenerator {

    /** 用于保存序列键数据 */
    protected Map<String, KeyStore> keyStoreCache;
    protected Map<String, CodeStore> codeStoreCache;

    private Object keyCacheLock = new Object();
    private Object codeCacheLock = new Object();

    @Autowired
    private DataSource dataSource;

    @Autowired
    private KeyStoreFactory keyStoreFactory;

    private KeyStore batchKeyStore = null;

    public DatabaseBizIdGenerator() {
        keyStoreCache = Collections.synchronizedMap(new HashMap<String, KeyStore>());
        codeStoreCache = Collections.synchronizedMap(new HashMap<String, CodeStore>());
    }

    public void init(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void testInit(){
        System.out.println("DatabaseBizIdGenerator: "+this);
    }

    /**
     * 从高速缓存中获取指定关键字的序列号（即关键字段的唯一序号）并将序号递增。
     *
     * @param keyName
     *            关键字名称
     * @return 返回关键字的序号
     * @throws Exception
     *             工具类异常
     */
    @Override
    public long getNextKey(String keyName) {

        KeyStore objKeyStore = null;

        synchronized (keyName.intern()) {
            if (keyStoreCache.containsKey(keyName)) {
                // 返回配置信息
                objKeyStore = keyStoreCache.get(keyName);
            } else {
                objKeyStore = keyStoreFactory.newKeyStore(dataSource);
                keyStoreCache.put(keyName, objKeyStore);
            }
        }

        return objKeyStore.getNextKey(keyName);
    }

    /**
     * 生成编号
     *
     * @param businessCode
     * @return
     */
    @Override
    public String generateCode(String businessCode) {
        CodeStore codeStore;
        synchronized (businessCode.intern()) {
            codeStore = codeStoreCache.get(businessCode);
            if(codeStore==null) {
                codeStore = keyStoreFactory.newCodeStore(dataSource);
                codeStoreCache.put(businessCode, codeStore);
            }
        }
        return codeStore.generateCode(businessCode);
    }

    /**
     * 生成编号, 带前缀
     *
     * @param businessCode
     * @return
     */
    @Override
    public String generateCode(String prefix, String businessCode) {
        return prefix + generateCode(businessCode);
    }

    /**
     * 根据keyName跟number获取一批主键ID
     *
     * @param keyName
     * @param number
     * @return
     * @throws IdGenerateException
     */
    @Override
    public List<Long> getBatchKey(String keyName, Integer number) throws IdGenerateException {
        if (number == null || number <= 0) {
            throw new RuntimeException("数量必须大于0！");
        }

        synchronized (keyName.intern()) {
            if (batchKeyStore == null) {
                batchKeyStore = keyStoreFactory.batchKeyStore(dataSource);
            }
        }
        return batchKeyStore.getBatchKey(keyName, number);
    }

}
