package com.orcl.design_pattern.chain.v1;

import lombok.Setter;

/**
 * @author lea
 * @description
 * @history 2024-05-26 23:35 created by lea
 * @since 2024-05-26 23:35
 */
public class FilterChain {

    @Setter
    private Servlet servlet;

    private Filter[] filters = new Filter[0];

    private int pos = 0;

    private int n = 0;

    public void doFilter(Request req, Response res) {
        if (pos < n) {
            Filter filter = filters[pos++];
            filter.doFilter(req, res, this);
        } else {
            servlet.service(req, res);
        }
    }

    public void addFilter(Filter filter) {
        for (Filter existingFilter : filters) {
            if (existingFilter == filter) {
                return;
            }
        }

        if (n == filters.length) {
            // 数组扩容
            Filter[] newFilters = new Filter[n + 10];
            System.arraycopy(filters, 0, newFilters, 0, n);
            filters = newFilters;
        }

        filters[n++] = filter;
    }

}
