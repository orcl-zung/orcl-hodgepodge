package com.lea.framework.data.base.idgen.toolkit;

import com.lea.framework.data.base.idgen.IdGenerateException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lea
 * @description ID 生成器
 * @history 2023-04-07 18:18 created by lea
 * @since 2023-04-07 18:18
 */
public class IdWorker {

    private IdWorker() {

    }

    public static final Sequence SEQUENCE = new Sequence(null);

    /**
     * 默认批量获取 id 的个数
     */
    public static final int DEFAULT_BATCH_COUNT = 100;

    /**
     * 一次获取的最多 Id 个数
     */
    public static final int MAX_BATCH_COUNT = 1000;

    /**
     * 获取一个 id
     *
     * @return id 序列
     */
    public static long getId() {
        return SEQUENCE.nextId();
    }

    /**
     * 获取一批 id， 数量为 {@link IdWorker#DEFAULT_BATCH_COUNT}
     *
     * @return id 列表
     */
    public static List<Long> getBatchId() {
        return getBatchId(DEFAULT_BATCH_COUNT);
    }

    public static List<Long> getBatchId(int batchCount) {
        if (batchCount < 1 || batchCount > MAX_BATCH_COUNT) {
            throw new IdGenerateException(String.format("一次获取的 id 数必须大于 0，并且小于等于%d", MAX_BATCH_COUNT));
        }
        List<Long> ids = new ArrayList<>(batchCount);
        for (int i = 0; i < batchCount; i++) {
            ids.add(getId());
        }
        return ids;
    }

}
