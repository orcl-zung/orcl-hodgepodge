package com.orcl.demo.redis.cache.service;

import com.orcl.demo.redis.cache.entity.DictData;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import java.util.List;
import java.util.Objects;

import static com.orcl.demo.redis.cache.constant.CacheConstant.SYS_DICT_KEY;

/**
 * @description:
 * @author: orcl
 * @since: 2022/4/25-17:34
 * @history: 2022/4/25 created by orcl
 */
public class DistService {

    private CacheManager cacheManager;

    private RedisService redisService;

    public DistService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 根据字典类型获取字典值列表
     *
     * @param dictType 字典类型
     * @return
     */
    public List<DictData> getByDictType(String dictType) {
        Ehcache dictCache = cacheManager.getEhcache("distCache");
        Element element = dictCache.get(dictType);
        List<DictData> arrayList;
        if (Objects.isNull(element)) {
            // redis 获取
            arrayList = (List<DictData>) redisService.getCacheObject(getCacheKey(dictType));
            dictCache.put(new Element(dictType, arrayList));
        } else {

            arrayList = (List<DictData>) element.getObjectValue();
        }
        return arrayList;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return SYS_DICT_KEY + configKey;
    }

    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

}
