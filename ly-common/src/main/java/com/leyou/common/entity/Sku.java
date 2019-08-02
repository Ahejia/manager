package com.leyou.common.entity;


import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
/**
 * sku表 表示具体的商品实体，如黑色的 64g的ipone 6s
 */
public class Sku implements Serializable {

    private static final long serialVersionUID = 2663003826536984179L;
    @Id
    private Long id;

    /**
     * spuId
     */
    private Long spuId;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品的图片，以"，"分隔
     */
    private String images;
    /**
     * 销售价格
     */
    private Long price;
    /**
     * 特有规格属性在spu属性模板中的对应下标组合
     */
    private String indexes;
    /**
     * sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序
     */
    private String ownSpec;
    /**
     * 是否有效，0无效1有效
     */
    private Byte enable;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;

    /**库存*/
    private Integer stock;
    @Transient
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return images
     */
    public String getImages() {
        return images;
    }

    /**
     * @param images images
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * @return price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * @param price price
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * @return indexes
     */
    public String getIndexes() {
        return indexes;
    }

    /**
     * @param indexes indexes
     */
    public void setIndexes(String indexes) {
        this.indexes = indexes;
    }

    /**
     * @return ownSpec
     */
    public String getOwnSpec() {
        return ownSpec;
    }

    /**
     * @param ownSpec ownSpec
     */
    public void setOwnSpec(String ownSpec) {
        this.ownSpec = ownSpec;
    }

    /**
     * @return enable
     */
    public Byte getEnable() {
        return enable;
    }

    /**
     * @param enable enable
     */
    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    /**
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return lastUpdateTime
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * @param lastUpdateTime lastUpdateTime
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
