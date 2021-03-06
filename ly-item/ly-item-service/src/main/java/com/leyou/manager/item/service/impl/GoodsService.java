package com.leyou.manager.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.entity.*;
import com.leyou.common.enums.MessageEnums;
import com.leyou.common.utils.PageResult;
import com.leyou.common.vo.SpuBo;
import com.leyou.manager.item.dao.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
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

    private Logger logger = LoggerFactory.getLogger(GoodsService.class);
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private CategoryServiceimpl categoryService;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private StockMapper stockMapper;

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
        Page<Spu> pageInfo = (Page<Spu>) spuMapper.selectByExample(example);
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


    @Transactional(rollbackFor = {})
    public void save(SpuBo spu) {
        // 保存spu
        logger.info("---准备保存spu的数据---");
        spu.setSaleable(MessageEnums.IS_RIGHT.getCode());
        spu.setValid(MessageEnums.IS_RIGHT.getCode());
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spuMapper.insert(spu);
        // 保存spu详情
        logger.info("---再保存spu详情数据---");
        spu.getSpuDetail().setSpuId(spu.getId());
        spuDetailMapper.insert(spu.getSpuDetail());

        // 保存sku和库存信息
        saveSkuAndStock(spu.getSkus(), spu.getId());
    }

    private void saveSkuAndStock(List<Sku> skus, Long spuId) {
        for (Sku sku : skus) {
            if (!sku.getEnable().equals(MessageEnums.IS_RIGHT.getCode())) {
                continue;
            }
            // 保存sku
            sku.setSpuId(spuId);
            // 默认不参与任何促销
            logger.info("---保存sku信息---");
            sku.setLastUpdateTime(new Date());
            skuMapper.insert(sku);

            // 保存库存信息
            logger.info("---准备保存库存信息---");
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockMapper.insert(stock);
        }
    }
    /**
     * @describe 编号查询详情
     * @param id 编号
     * @return com.leyou.common.entity.SpuDetail
     */
    public SpuDetail getSpuDetailById(Long id){
        return spuDetailMapper.selectByPrimaryKey(id);
    }

    /**
     * @describe spu编号查询sku信息
     * @param id spu编号
     * @return java.util.List<com.leyou.common.entity.Sku>
     */
    public List<Sku> getSkuBySpuId(Long id){
        //查询sku
        Sku record = new Sku();
        record.setSpuId(id);
        List<Sku> skus = skuMapper.select(record);
        for (Sku sku : skus) {
            sku.setStock(stockMapper.selectByPrimaryKey(sku.getId()).getStock());
        }
        return skus;
    }

    @Transactional
    public void update(SpuBo spu) {
        // 查询以前sku
        List<Sku> skus = getSkuBySpuId(spu.getId());
        // 如果以前存在，则删除
        if(!CollectionUtils.isEmpty(skus)) {
            List<Long> ids = skus.stream().map(s -> s.getId()).collect(Collectors.toList());
            // 删除以前库存
            Example example = new Example(Stock.class);
            example.createCriteria().andIn("skuId", ids);
            this.stockMapper.deleteByExample(example);

            // 删除以前的sku
            Sku record = new Sku();
            record.setSpuId(spu.getId());
            this.skuMapper.delete(record);

        }
        // 新增sku和库存
        saveSkuAndStock(spu.getSkus(), spu.getId());

        // 更新spu
        spu.setLastUpdateTime(new Date());
        spu.setCreateTime(null);
        spu.setValid(null);
        spu.setSaleable(null);
        this.spuMapper.updateByPrimaryKeySelective(spu);

        // 更新spu详情
        this.spuDetailMapper.updateByPrimaryKeySelective(spu.getSpuDetail());
    }


}
