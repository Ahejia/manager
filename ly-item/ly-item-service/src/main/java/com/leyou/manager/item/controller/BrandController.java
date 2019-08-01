package com.leyou.manager.item.controller;

import com.leyou.common.entity.Brand;
import com.leyou.common.utils.PageResult;
import com.leyou.manager.item.service.impl.BrandServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version : 1.0
 * @ClassName: BrandController
 * @Description :
 * @auther: hejia
 * @date: 2019/4/9
 */
@RestController
public class BrandController {

    private Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandServiceImpl brandService;
    /**
     * @param page:当前页
     * @param rows：每页大小
     * @param sortBy:排序字段
     * @param desc:是否为降序
     * @param key:搜索关键字
     */
    @GetMapping("/brand/page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key){
        logger.info("---查询品牌的数据---");
        PageResult<Brand> result = this.brandService.queryBrandByPageAndSort(page, rows, sortBy, desc, key);

        if(result == null || result.getItems().size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    /**  新增品牌
     * @param brand:品牌的信息
     * @param cids:商品分类编号的集合
     */
    @PostMapping("/brand")
    public ResponseEntity<Void> saveBrand(Brand brand,@RequestParam("cids")List<Long> cids){
        brandService.saveBrand(brand,cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
