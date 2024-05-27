package com.orcl.design_pattern.chain.v1;

import com.orcl.design_pattern.ApplicationTest;
import org.junit.Test;

/**
 * @author lea
 * @description
 * @history 2024-05-26 22:41 created by lea
 * @since 2024-05-26 22:41
 */
public class FilterTest extends ApplicationTest {

    @Test
    public void test_filter_v1() {

        FilterChain chain = new FilterChain();
        chain.addFilter(new LogFilter());
        chain.addFilter(new EncodeFilter());
        chain.setServlet(new Servlet());

        // 模拟一个请求，要经过 Filter
        chain.doFilter(new Request(), new Response());

    }

}
