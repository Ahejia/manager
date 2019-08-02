package com.leyou.manager.item.controller;

import com.leyou.common.entity.Specification;
import com.leyou.manager.item.service.impl.SpecifcationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version : 1.0
 * @ClassName: SpecificationController
 * @Description :
 * @auther: hejia
 * @date: 2019/4/17
 */
@RestController
public class SpecificationController {

    private Logger logger = LoggerFactory.getLogger(SpecificationController.class);

    @Autowired
    private SpecifcationService specifcationService;
    @GetMapping("spec/{id}")
    public ResponseEntity<String> querySpecificationByCategoryId(@PathVariable("id")Long id){
        Specification specification = this.specifcationService.queryById(id);
        if(specification == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(specification.getSpecifications());
    }

    @GetMapping("spec/params")
    public ResponseEntity<List<Specification>> querySpecParam(
            @RequestParam(value="gid", required = false) Long gid,
            @RequestParam(value="cid", required = false) Long cid,
            @RequestParam(value="searching", required = false) Boolean searching,
            @RequestParam(value="generic", required = false) Boolean generic
    ){
        logger.info("---查询类目编号为"+cid+"的规格参数---");
        List<Specification> list = specifcationService.querySpecParams(gid,cid,searching,generic);
        if(list == null || list.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }


}
