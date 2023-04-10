package com.lea.framework.data.base.pagination;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author lea
 * @description
 * @history 2023-04-10 17:32 created by lea
 * @since 2023-04-10 17:32
 */
public class PageResponse<T> {

    private Integer pageNum;

    private Integer pageSize;

    private Long totalRows;

    private Integer totalPages;

    private List<T> data;

    public static <T> PageResponse<T> buildSuccess(Page<T> pageData) {
        PageResponse<T> response = new PageResponse<>();
        response.setPageNum(pageData.getPageNum());
        response.setPageSize(pageData.getPageSize());
        response.setTotalRows(pageData.getTotal());
        response.setTotalPages(pageData.getPages());
        response.setData(pageData.getResult());
        return response;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
