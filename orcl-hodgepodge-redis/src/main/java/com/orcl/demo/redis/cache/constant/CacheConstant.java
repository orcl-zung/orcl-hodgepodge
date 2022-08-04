package com.orcl.demo.redis.cache.constant;

/**
 * @description:
 * @author: orcl
 * @since: 2022/4/25-17:30
 * @history: 2022/4/25 created by orcl
 */
public interface CacheConstant {

    /**
     * 字典管理 cache key
     */
    String SYS_DICT_KEY = "sys_dict:";

    /**
     * 令牌自定义标识
     */
    String HEADER = "Authorization";

    /**
     * 令牌前缀
     */
    String TOKEN_PREFIX = "Bearer ";

    /**
     * 权限缓存前缀
     */
    String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 云迹token缓存前缀
     */
    String YJ_TOKEN_KEY = "yj_token";


    /**
     * 用户ID字段
     */
    String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    String DETAILS_USERNAME = "username";


    /**
     * 授权信息字段
     */
    String AUTHORIZATION_HEADER = "authorization";

    /**
     * 缓存基本常量
     */
    String BASE_CACHE_KEY = ":cache:";

    /**
     * 用户权限
     */
    String USER_PERMISSION_KEY = "userPermission:";

    /**
     * 缓存基本常量
     */
    String REDIS_LOCK_CACHE_KEY = "robot-redis-lock:";

    /**
     * 缓存基本常量
     */
    String REDIS_LOCK_PULL_ADD = "pullAdd:";

    /**
     * 缓存基本常量
     */
    String PUSH_CACHE_NAME = ":PUSH_CACHE:";

    /**
     * TOKEN缓存的KEY
     */
    String AUTH_TOKEN_KEY = "AUTH_TOKEN";

    /**
     * VIVO缓存BASE常量
     */
    String VIVO_CACHE_BASE_KEY = PUSH_CACHE_NAME + "VIVO_";

    /**
     * OPPO缓存BASE常量
     */
    String OPPO_CACHE_BASE_KEY = PUSH_CACHE_NAME + "OPPO_";

    /**
     * OPPO缓存BASE常量
     */
    String HUAWEI_CACHE_BASE_KEY = PUSH_CACHE_NAME + "HUAWEI_";

    /**
     * VIVO缓存TOKEN-KEY
     */
    String VIVO_TOKEN_KEY = VIVO_CACHE_BASE_KEY + AUTH_TOKEN_KEY;

    /**
     * OPPO缓存TOKEN-KEY
     */
    String HUAWEI_TOKEN_KEY = HUAWEI_CACHE_BASE_KEY + AUTH_TOKEN_KEY;

    /**
     * OPPO缓存TOKEN-KEY
     */
    String OPPO_TOKEN_KEY = OPPO_CACHE_BASE_KEY + AUTH_TOKEN_KEY;

    String ROBOT_TASK_REQUEST_COUNT = "robotTaskRequestCount:";

    /**
     * 用户异常REDIS-KEY
     */
    String USER_EXCEPTION_KEY = "exception:user:";

    String QUEUEING_TAG = "queueing_tag";

    /**
     * 审核key
     */
    String VERIFY_KEY = "verify_key";

    /**
     * 楼宇昨日商品销售top
     */
    String YESTERDAY_TOP_GOODS="YESTERDAY_TOP_GOODS:";

    //接单的key
    String MAN_SEND = "MAN_SEND";

    //确认发货的key
    String CONFIRM_BY_DELIVER = "CONFIRM_BY_DELIVER";

    //确认送达的key   completeByDeliver
    String COMPLETE_BY_DELIVER = "COMPLETE_BY_DELIVER";

    /**
     * traceId
     */
    String TRACE_ID="traceId";

    /**
     * 补货日志乱码，临时解决方案KEY
     */
    String USER_KEY = "user_key";

}
