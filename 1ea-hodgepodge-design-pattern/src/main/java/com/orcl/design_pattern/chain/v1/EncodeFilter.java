package com.orcl.design_pattern.chain.v1;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lea
 * @description
 * @history 2024-05-26 23:37 created by lea
 * @since 2024-05-26 23:37
 */
public class EncodeFilter implements Filter{
    @Override
    public void doFilter(Request req, Response res, FilterChain chain) {
        // 前置操作
        System.out.println("encode start...");

        // 随即拦截，阻止请求到达 Servlet
        if (ThreadLocalRandom.current().nextInt(10) > 5) {
            chain.doFilter(req, res);
        } else {
            System.out.println("encodeFilter 终止...");
            return;
        }

        // 后置操作
        System.out.println("encode end...");
    }
}
