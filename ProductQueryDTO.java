package com.example.dto.request;

public class ProductQueryDTO {
    private String name;
    private Integer brandId;
    private Integer categoryId;
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    public ProductQueryDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getBrandId() { return brandId; }
    public void setBrandId(Integer brandId) { this.brandId = brandId; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public Integer getPageNum() { return pageNum; }
    public void setPageNum(Integer pageNum) { this.pageNum = pageNum; }
    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
}