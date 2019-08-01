package com.leyou.manager.item.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.entity.Brand;
import com.leyou.common.utils.PageResult;
import com.leyou.manager.item.dao.BrandMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @version : 1.0
 * @ClassName: BrandServiceImpl
 * @Description :
 * @auther: hejia
 * @date: 2019/4/9
 */
@Service
public class BrandServiceImpl {

    @Autowired
    private BrandMapper brandMapper;
    /**
    * @param page:当前页
     * @param rows：每页大小
    */
    public PageResult<Brand> queryBrandByPageAndSort(Integer page,Integer rows,String sortBy,Boolean desc,String key){
        //开始分页
        PageHelper.startPage(page,rows);
        //过滤
        Example example = new Example(Brand.class);
        if(StringUtils.isNoneBlank(key)){
            example.createCriteria().andLike("name","%"+key+"%")
                    .orEqualTo("letter",key);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(),pageInfo);
    }

    /**新增品牌*/
    @Transactional
    public void saveBrand(Brand brand, List<Long>cids){
        this.brandMapper.insertSelective(brand);
        for(Long cid : cids){
            this.brandMapper.insertCategoryBrand(cid,brand.getId());
        }
    }

    /**
     * @describe 通过商品类目编号查询品牌信息
     * @param cid
     * @return java.util.List<com.leyou.common.entity.Brand>
     */
    public List<Brand> queryBrandByCategory(Long cid){
        return brandMapper.queryBrandByCategory(cid);
    }
}
