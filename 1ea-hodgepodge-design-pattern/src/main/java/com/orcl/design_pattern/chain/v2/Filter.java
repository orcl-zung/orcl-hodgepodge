package com.orcl.design_pattern.chain.v2;

import com.orcl.design_pattern.chain.v1.Request;
import com.orcl.design_pattern.chain.v1.Response;

/**
 * @author lea
 * @description
 * @history 2024-05-27 21:53 created by lea
 * @since 2024-05-27 21:53
 */
public interface Filter {

    boolean doFilter(Request req, Response res);

    void setNext(Filter filter);

    Filter getNext();

}
