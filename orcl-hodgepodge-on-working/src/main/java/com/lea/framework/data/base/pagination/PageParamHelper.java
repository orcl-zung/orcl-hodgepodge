package com.lea.framework.data.base.pagination;

import org.apache.ibatis.session.RowBounds;

/**
 * @author lea
 * @description
 * @history 2023-04-10 17:28 created by lea
 * @since 2023-04-10 17:28
 */
public class PageParamHelper {

    public static void correctPage(PageParam pageParam, int minPageSize, int maxPageSize) {
        if (pageParam.getPageNum() == null || pageParam.getPageNum() == 0) {
            pageParam.setPageNum(1);
        }
        if (pageParam.getPageSize() == null) {
            pageParam.setPageSize(minPageSize);
        } else if (pageParam.getPageSize() < minPageSize) {
            pageParam.setPageSize(minPageSize);
        } else if (pageParam.getPageSize() > maxPageSize) {
            pageParam.setPageSize(maxPageSize);
        }
    }

    public static void correctPage(PageParam pageParam, int maxPageSize) {
        correctPage(pageParam, 10, maxPageSize);
    }

    public static RowBounds toRowBounds(PageParam pageParam) {
        int offset = 0;
        if (pageParam.getPageNum() > 0) {
            offset = (pageParam.getPageNum() - 1) * pageParam.getPageSize();
        }
        return new RowBounds(offset, pageParam.getPageSize());
    }

}
