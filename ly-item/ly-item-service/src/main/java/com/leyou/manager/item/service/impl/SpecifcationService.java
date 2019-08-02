package com.leyou.manager.item.service.impl;

import com.leyou.common.entity.Specification;
import com.leyou.manager.item.dao.SpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version : 1.0
 * @ClassName: SpecifcationService
 * @Description :
 * @auther: hejia
 * @date: 2019/4/17
 */
@Service
public class SpecifcationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    public Specification queryById(Long id){
        return this.specificationMapper.selectByPrimaryKey(id);
    }

    /**通过商品分类id查询规格参数*/
    public List<Specification> querySpecParams(Long gid,Long cid,Boolean searching,Boolean generic){
        Specification specification = new Specification();
        specification.setCategoryId(cid);
        return specificationMapper.select(specification);
    }
}
