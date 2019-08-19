package com.leyou.common.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 商品详细信息表
 */
public class SpuDetail implements Serializable {
    private static final long serialVersionUID = 7173886773902882949L;
    /***/
    @Id
    private Long spuId;
    /**
     * 商品描述信息
     */
    private String description;
    /**
     * 全部规格参数数据
     */
    private String specifications;
    /**
     * 特有规格参数以及可选值信息，json格式
     */
    private String specTemplate;
    /**
     * 包装清单
     */
    private String packingList;
    /**
     * 售后服务
     */
    private String afterService;

    /**
     * @return spuId
     */
    public Long getSpuId() {
        return spuId;
    }

    /**
     * @param spuId spuId
     */
    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return specifications
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * @param specifications specifications
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /**
     * @return specTemplate
     */
    public String getSpecTemplate() {
        return specTemplate;
    }

    /**
     * @param specTemplate specTemplate
     */
    public void setSpecTemplate(String specTemplate) {
        this.specTemplate = specTemplate;
    }

    /**
     * @return packingList
     */
    public String getPackingList() {
        return packingList;
    }

    /**
     * @param packingList packingList
     */
    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    /**
     * @return afterService
     */
    public String getAfterService() {
        return afterService;
    }

    /**
     * @param afterService afterService
     */
    public void setAfterService(String afterService) {
        this.afterService = afterService;
    }
}
