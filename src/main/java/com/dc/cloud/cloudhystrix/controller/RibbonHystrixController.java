package com.dc.cloud.cloudhystrix.controller;

import com.dc.cloud.cloudhystrix.service.RibbonHystrixService;
import com.dc.cloud.cloudhystrix.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author DC
 * @Date 2019-11-05
 */
@RestController
public class RibbonHystrixController {
    @Autowired
    private RibbonHystrixService ribbonHystrixService;

    @GetMapping("/ribbon/{id}")
    public User getById(@PathVariable Long id){
        return ribbonHystrixService.findById(id);
    }
}
