package com.orcl.design_pattern.chain.v2;

import com.orcl.design_pattern.ApplicationTest;
import com.orcl.design_pattern.chain.v1.Request;
import com.orcl.design_pattern.chain.v1.Response;
import com.orcl.design_pattern.chain.v1.Servlet;
import org.junit.Test;

/**
 * @author lea
 * @description
 * @history 2024-05-27 21:59 created by lea
 * @since 2024-05-27 21:59
 */
public class V2Test extends ApplicationTest {

    @Test
    public void test_chain_v2() {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new AFilter());
        filterChain.addFilter(new BFilter());
        filterChain.addFilter(new CFilter());
        filterChain.setServlet(new Servlet());

        filterChain.doFilter(new Request(), new Response());
    }

    private static class AFilter implements Filter {
        @Override
        public boolean doFilter(Request req, Response res) {
            return false;
        }

        @Override
        public void setNext(Filter filter) {

        }

        @Override
        public Filter getNext() {
            return null;
        }
    }

    private static class BFilter implements Filter {

        @Override
        public boolean doFilter(Request req, Response res) {
            return false;
        }

        @Override
        public void setNext(Filter filter) {

        }

        @Override
        public Filter getNext() {
            return null;
        }
    }

    private static class CFilter implements Filter {

        @Override
        public boolean doFilter(Request req, Response res) {
            return false;
        }

        @Override
        public void setNext(Filter filter) {

        }

        @Override
        public Filter getNext() {
            return null;
        }
    }
}
