package com.doubleball.controller;

import com.doubleball.protocol.ServiceResult;
import com.doubleball.service.DoubleballService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/8/13 下午2:28
 */
@RestController
@RequestMapping("/douballapi")
public class DoubleballController {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DoubleballService doubleballService;

    //  初始化数据，导入历史数据
    @GetMapping("/initball")
    public void initBall() {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        doubleballService.initBall();
    }

    //  查询库中所有的记录
    @GetMapping("/queryallball")
    public ServiceResult queryallball() {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        return doubleballService.queryallball();
    }

    //  获取随机红球和蓝球
    @GetMapping("/getrandomnum")
    public ServiceResult getRandomNum() {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        return doubleballService.getRandomNum();
    }

    //  统计每个球出现的次数
    @GetMapping("/calculateball")
    public void calculateBall() {
        System.out.println(DateTime.now());
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        doubleballService.calculateBall();
    }

}
