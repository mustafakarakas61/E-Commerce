package com.bringtome.ecommerce.model;


import com.bringtome.ecommerce.repository.projection.ProductProjection;
import org.springframework.data.domain.Page;

public class PrmSearch {

    private Integer page;
    private Integer perPage;

    Page<ProductProjection> totalRow;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Page<ProductProjection> getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Page<ProductProjection> totalRow) {
        this.totalRow = totalRow;
    }
}
