package com.leyou.common.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 商品规格参数模板，json格式。
 */
public class Specification implements Serializable {
    private static final long serialVersionUID = 1067400184261739320L;
    /**
     * 规格模板所属商品分类id
     */
    @Id
    private Long categoryId;
    /**
     * 规格参数模板，json格式
     */
    private String specifications;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }
}