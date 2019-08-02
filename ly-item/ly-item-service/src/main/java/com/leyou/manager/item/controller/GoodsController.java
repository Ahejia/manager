package com.leyou.manager.item.controller;

import com.leyou.common.utils.PageResult;
import com.leyou.common.vo.SpuBo;
import com.leyou.manager.item.service.impl.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @version : 1.0
 * @ClassName: GoodeController
 * @Description : 商品相关的接口
 * @auther: hejia
 * @date: 2019/4/17
 */
@RestController
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "saleable")Boolean saleable,
            @RequestParam(value = "key",required = false)String key){

        //分页查询spu信息
        PageResult<SpuBo> result = this.goodsService.querySpuByPageAndSort(page, rows, saleable, key);
        if(result == null || result.getItems().size() ==  0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(result);
    }
    /***
     * @describe 新增商品
     * @param spu
     * @return org.springframework.http.ResponseEntity<java.lang.Void>
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spu){
        try {
            logger.info("---保存商品---");
            goodsService.save(spu);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
