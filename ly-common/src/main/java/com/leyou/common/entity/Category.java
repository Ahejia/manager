package com.leyou.common.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 类目表
 */
public class Category implements Serializable {

    private static final long serialVersionUID = -8149186333857982993L;
    @Id
    private Long id;
    /**类目名称*/
    private String name;
    /**父类目id,顶级项目填0*/
    private Long parentId;
    /**是否为父节点，0否1是*/
    private Byte isParent;
    /**排序指数，越小越靠前*/
    private Integer sort;

    /**
     * @return  编号
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id  编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return  类目名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name  类目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return isParent
     */
    public Byte getIsParent() {
        return isParent;
    }

    /**
     * @param isParent isParent
     */
    public void setIsParent(Byte isParent) {
        this.isParent = isParent;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort 排序指数
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
