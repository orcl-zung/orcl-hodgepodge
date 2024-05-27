package com.orcl.design_pattern.chain.v2;

import com.orcl.design_pattern.chain.v1.Request;
import com.orcl.design_pattern.chain.v1.Response;
import com.orcl.design_pattern.chain.v1.Servlet;
import lombok.Setter;

/**
 * @author lea
 * @description
 * @history 2024-05-27 21:57 created by lea
 * @since 2024-05-27 21:57
 */
public class FilterChain {

    @Setter
    private Servlet servlet;

    private Filter firstFilter;

    public void doFilter(Request req, Response res) {
        if (firstFilter != null) {
            Filter filter = firstFilter;
            firstFilter = firstFilter.getNext();
            filter.doFilter(req, res);
        } else {
            servlet.service(req, res);
        }
    }

    public void addFilter(Filter filter) {
        if (firstFilter == null) {
            firstFilter = filter;
        } else {
            // 遍历找到最后一个节点，把最新的Filter追加上去
            Filter currentFilter = firstFilter;
            while (currentFilter.getNext() != null) {
                currentFilter = currentFilter.getNext();
            }
            currentFilter.setNext(filter);
        }
    }

}
