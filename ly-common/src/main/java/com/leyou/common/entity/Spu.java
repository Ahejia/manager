package com.leyou.common.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
/**
 * 该表描述的是一个抽象性的商品，比如 iphone8
 */
public class Spu implements Serializable {
    private static final long serialVersionUID = 1257274503614301832L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 主题
     */
    private String title;
    /**
     * 子标题
     */
    private String subTitle;

    /**
     * 1级类目id
     */
    private Long cid1;

    /**
     * 2级类目
     */
    private Long cid2;
    /**
     * 3级类目
     */
    private Long cid3;

    /**
     * 商品所属品牌编号
     */
    private Long brandId;

    /**
     * 是否上架 0下架1上架
     */
    private Byte saleable;

    /**
     * 是否有效  0删除1有效  default = 1
     */
    private Byte valid;

    /**
     * 添加时间
     */
    private Date createTime;
    /**
     * 修改时间
     */

    private Date lastUpdateTime;

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
     * @return subTitle
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * @param subTitle subTitle
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * @return cid1
     */
    public Long getCid1() {
        return cid1;
    }

    /**
     * @param cid1 cid1
     */
    public void setCid1(Long cid1) {
        this.cid1 = cid1;
    }

    /**
     * @return cid2
     */
    public Long getCid2() {
        return cid2;
    }

    /**
     * @param cid2 cid2
     */
    public void setCid2(Long cid2) {
        this.cid2 = cid2;
    }

    /**
     * @return cid3
     */
    public Long getCid3() {
        return cid3;
    }

    /**
     * @param cid3 cid3
     */
    public void setCid3(Long cid3) {
        this.cid3 = cid3;
    }

    /**
     * @return brandId
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * @param brandId brandId
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * @return saleable
     */
    public Byte getSaleable() {
        return saleable;
    }

    /**
     * @param saleable saleable
     */
    public void setSaleable(Byte saleable) {
        this.saleable = saleable;
    }

    /**
     * @return valid
     */
    public Byte getValid() {
        return valid;
    }

    /**
     * @param valid valid
     */
    public void setValid(Byte valid) {
        this.valid = valid;
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
