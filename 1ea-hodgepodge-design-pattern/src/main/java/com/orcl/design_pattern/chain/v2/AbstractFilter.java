package com.orcl.design_pattern.chain.v2;

import com.orcl.design_pattern.chain.v1.Request;
import com.orcl.design_pattern.chain.v1.Response;

/**
 * @author lea
 * @description
 * @history 2024-05-27 21:54 created by lea
 * @since 2024-05-27 21:54
 */
public abstract class AbstractFilter implements Filter {

    private Filter nextFilter;

    @Override
    public boolean doFilter(Request req, Response res) {
        return this.judge(req, res) ? this.next(req, res) : this.stop();
    }

    protected abstract boolean judge(Request req, Response res);

    private boolean next(Request req, Response res) {
        return nextFilter != null && nextFilter.doFilter(req, res);
    }

    private boolean stop() {
        return false;
    }

    @Override
    public void setNext(Filter filter) {
        this.nextFilter = filter;
    }

    @Override
    public Filter getNext() {
        return nextFilter;
    }
}
