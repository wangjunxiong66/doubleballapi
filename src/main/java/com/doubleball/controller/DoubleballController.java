package com.doubleball.controller;

import com.doubleball.entity.DoubleballRecord;
import com.doubleball.entity.DoubleballStatistics;
import com.doubleball.protocol.ServiceResult;
import com.doubleball.service.DoubleballService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //  插入新记录
    @PostMapping("/insertball")
    public String saveBall(@RequestBody DoubleballRecord doubleballRecord) {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        String temp;
        temp = doubleballService.insertBall(doubleballRecord);
        return temp ;
    }

    //  查询库中所有的记录
    @GetMapping("/queryallball")
    public ServiceResult queryallball() {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        return doubleballService.queryallball();
    }

    //  通过页码查询库中所有的记录
    @GetMapping("/queryallballbypage/{page}/{size}")
    public ServiceResult queryallball(@PathVariable("page") int page,@PathVariable("size") int size) {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        return doubleballService.queryallballbypage(page,size);
    }

    //  通过id查询库中记录
    @GetMapping("/queryballbyid/{id}")
    public ServiceResult queryBallById(@PathVariable("id") int id) {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        return doubleballService.getBallById(id);
    }

    //  根据id修改记录
    @PutMapping("/updateballbyid")
    public ServiceResult updateBall(@RequestBody DoubleballStatistics doubleballStatistics) {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        int temp = doubleballService.updateBallById(doubleballStatistics);
        LOG.info("修改的结果是：   "+temp);
        if (temp!=0){
            LOG.info("success");
            return ServiceResult.success("success");
        }else {
            LOG.info("false");
            return ServiceResult.failure(-1,"false");
        }
    }

    //  根据id删除记录
    @DeleteMapping("/deleteballbyid/{id}")
    public ServiceResult deleteBallById(@PathVariable("id") int id) {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        doubleballService.deleteBallById(id);
        // 如果没有异常，认为删除成功
        LOG.info("没有异常发生，认为删除成功");
        return ServiceResult.success("success");
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
