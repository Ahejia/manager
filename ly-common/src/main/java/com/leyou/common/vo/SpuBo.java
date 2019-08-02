package com.leyou.common.vo;

import com.leyou.common.entity.Sku;
import com.leyou.common.entity.Spu;
import com.leyou.common.entity.SpuDetail;

import java.beans.Transient;
import java.util.List;

/**
 * @Transient 表示在数据库中不添加数据，没有get方法直接在属性上添加即可，若有get方法，正确使用方式是在get方法上添加注解，如下所示
 * @version : 1.0
 * @ClassName: SpuBo
 * @Description :
 * @auther: hejia
 * @date: 2019/4/17
 */
public class SpuBo extends Spu {
    /**
     * 商品分类名称
     */
    private String cName;
    /**
     * 品牌名称
     */
    private String bName;

    /**
     * 商品详情
     */
    private SpuDetail spuDetail;
    /**
     * Sku列表
     */
    private List<Sku> skus;

    @Transient
    public SpuDetail getSpuDetail() {
        return spuDetail;
    }

    public void setSpuDetail(SpuDetail spuDetail) {
        this.spuDetail = spuDetail;
    }

    @Transient
    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    /**
     * @return cName
     */
    @Transient
    public String getcName() {
        return cName;
    }

    /**
     * @param cName cName
     */
    public void setcName(String cName) {
        this.cName = cName;
    }

    /**
     * @return bName
     */
    @Transient
    public String getbName() {
        return bName;
    }

    /**
     * @param bName bName
     */
    public void setbName(String bName) {
        this.bName = bName;
    }
}
