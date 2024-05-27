package com.orcl.design_pattern.chain.v1;

/**
 * @author lea
 * @description
 * @history 2024-05-26 23:37 created by lea
 * @since 2024-05-26 23:37
 */
public class LogFilter implements Filter {
    @Override
    public void doFilter(Request req, Response res, FilterChain filterChain) {
        // 前置操作
        System.out.println("log start...");

        // 直接放行到下一个 Filter
        filterChain.doFilter(req, res);

        // 后置操作
        System.out.println("log end...");
    }
}
