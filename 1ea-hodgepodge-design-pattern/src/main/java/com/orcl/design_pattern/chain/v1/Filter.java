package com.orcl.design_pattern.chain.v1;

/**
 * @author lea
 * @description
 * @history 2024-05-26 22:44 created by lea
 * @since 2024-05-26 22:44
 */
public interface Filter {

    void doFilter(Request req, Response res, FilterChain filterChain);
}
