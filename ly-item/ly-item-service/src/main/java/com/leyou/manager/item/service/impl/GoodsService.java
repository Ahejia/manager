package com.leyou.manager.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.entity.Brand;
import com.leyou.common.entity.Spu;
import com.leyou.common.utils.PageResult;
import com.leyou.common.vo.SpuBo;
import com.leyou.manager.item.dao.BrandMapper;
import com.leyou.manager.item.dao.SpuMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version : 1.0
 * @ClassName: GoodsService
 * @Description :
 * @auther: hejia
 * @date: 2019/4/17
 */
@Service
public class GoodsService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private CategoryServiceimpl categoryService;
    @Autowired
    private BrandMapper brandMapper;

    public PageResult<SpuBo> querySpuByPageAndSort(Integer page,Integer rows,Boolean saleable,String key){
        //查询Spu
        //分页，最多允许查询100条
        PageHelper.startPage(page,Math.min(rows,100));
        //创建查询条件
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //是否过滤上下架
        if(saleable != null){
            criteria.orEqualTo("saleable",saleable);
        }
        //是否模糊查询
        if(StringUtils.isNoneBlank(key)){
            criteria.andLike("title","%"+key+"%");
        }
        Page<Spu> pageInfo = (Page<Spu>) this.spuMapper.selectByExample(example);
        List<SpuBo> list = pageInfo.getResult().stream().map(spu -> {
            //把spu变为spuBo
            SpuBo spuBo = new SpuBo();
            //属性拷贝
            BeanUtils.copyProperties(spu,spuBo);

            //查询spu的商品分类名称，要查三级分类
            List<String> names = this.categoryService.queryNameByIds(Arrays.asList(spu.getCid1(),spu.getCid2(),spu.getCid3()));
            //将分类名称拼接后存入
            spuBo.setcName(StringUtils.join(names,"/"));

            //查询spu的品牌名称
            Brand brand = this.brandMapper.selectByPrimaryKey(spu.getBrandId());
            spuBo.setbName(brand.getName());
            return spuBo;

        }).collect(Collectors.toList());


        return new PageResult<>(pageInfo.getTotal(),list);
    }
}
