package com.lagou.domain;

public class ResourceVO {
    private Integer categoryId;
    private Integer currentPage;
    private String name;
    private Integer pageSize;
    private String url;

    @Override
    public String toString() {
        return "ResourceVO{" +
                "categoryId=" + categoryId +
                ", currentPage=" + currentPage +
                ", name='" + name + '\'' +
                ", pageSize=" + pageSize +
                ", url='" + url + '\'' +
                '}';
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
