package com.leyou.manager.item.controller;

import com.leyou.common.entity.Category;
import com.leyou.manager.item.service.impl.CategoryServiceimpl;
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

@RestController
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryServiceimpl categoryService;

    @GetMapping("/category/list")
    public ResponseEntity<List<Category>> queryByParentId(@RequestParam(value = "pid",defaultValue = "0")Long pid){
        List<Category> list = categoryService.queryListByParent(pid);
        return ResponseEntity.ok(list);
    }

    /*通过品牌id查询分类*/
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid")Long bid){
        List<Category> list = categoryService.queryByBrandId(bid);
        if(list == null || list.size() < 1){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
