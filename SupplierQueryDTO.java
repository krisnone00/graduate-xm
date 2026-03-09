package com.example.dto.request;

public class SupplierQueryDTO {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String keyword; // 搜索名称或联系人

    public Integer getPageNum() { return pageNum; }
    public void setPageNum(Integer pageNum) { this.pageNum = pageNum; }
    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
}