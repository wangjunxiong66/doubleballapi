package com.doubleball.service;

import com.doubleball.entity.DoubleballRecord;
import com.doubleball.entity.DoubleballStatistics;
import com.doubleball.entity.ListDoubleballStatistics;
import com.doubleball.manager.DoubleballManager;
import com.doubleball.mapper.DoubleballStatisticsMapper;
import com.doubleball.protocol.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * @author wjx
 * @version 1.0
 * @date 2020/8/13 下午3:15
 */
//public interface DoubleballService {
//
//    public void initball(Integer issue, Integer red_one, Integer red_two, Integer red_three, Integer red_four, Integer red_five, Integer red_six,Integer blue, String draw_prize_date);
//
//}

@Service
public class DoubleballService {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DoubleballManager doubleballManager;

    public void initBall() {
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        doubleballManager.importHistoricalData();
    }

    // 查询记录，没有查询条件
    public ServiceResult<ListDoubleballStatistics> queryallball(){

        List<DoubleballStatistics> list = doubleballManager.queryallball();
        ListDoubleballStatistics listDoubleballStatistics = new ListDoubleballStatistics();
        listDoubleballStatistics.setList(list);
        return ServiceResult.success(listDoubleballStatistics);
    }

    // 通过页码查询记录
    public ServiceResult<ListDoubleballStatistics> queryallballbypage(int page,int size){

        List<DoubleballStatistics> list = doubleballManager.queryallballbypage(page,size);
        ListDoubleballStatistics listDoubleballStatistics = new ListDoubleballStatistics();
        listDoubleballStatistics.setList(list);
        int count = doubleballManager.getBallByAllCount();
        listDoubleballStatistics.setCount(count);
        int pageSize,temp;
        temp = count%size;
        if (temp==0){
            pageSize=count/size;
        } else {
            pageSize=(int)(count/size)+1;
        }
        listDoubleballStatistics.setPageSize(pageSize);
        return ServiceResult.success(listDoubleballStatistics);
    }

    //  通过id查询库中记录
    public ServiceResult<DoubleballStatistics> getBallById(int id){
        DoubleballStatistics doubleballStatistics =new DoubleballStatistics();
        doubleballStatistics = doubleballManager.getBallById(id);
        return ServiceResult.success(doubleballStatistics);
    }

    //  插入记录
    public String insertBall(DoubleballRecord doubleballRecord){
        doubleballManager.insertBall(doubleballRecord);
        return "success" ;
    }

    //  根据id修改记录
    public Integer updateBallById(DoubleballStatistics doubleballStatistics){
        return doubleballManager.updateBallById(doubleballStatistics);
    }

    //  根据id删除记录
    public void deleteBallById(int id){
        doubleballManager.deleteBallById(id);
    }

    //  预测下一期，随机数据
    public ServiceResult<DoubleballRecord> getRandomNum(){
        LOG.info("当前是 {} 的 {} 方法",this.getClass().getName(),new Exception().getStackTrace()[0].getMethodName());
        DoubleballRecord doubleballRecord =new DoubleballRecord();
        doubleballRecord = doubleballManager.getRandomNum();
        return ServiceResult.success(doubleballRecord);
    }
    
    //  对各种球类出现次数的统计
    public void calculateBall(){
        List<DoubleballStatistics> list = doubleballManager.queryallball();


        int[] red=new int[33] ;
        int[] blue=new int[16] ;
        int[] temp=new int[7];

        for(DoubleballStatistics doubleballStatistics:list){
            //  将记录中的红球和蓝球数据进行抓取
            temp[0]=doubleballStatistics.getRed_one();
            temp[1]=doubleballStatistics.getRed_two();
            temp[2]=doubleballStatistics.getRed_three();
            temp[3]=doubleballStatistics.getRed_four();
            temp[4]=doubleballStatistics.getRed_five();
            temp[5]=doubleballStatistics.getRed_six();
            temp[6]=doubleballStatistics.getBlue();
            //   统计红球出现的次数
            for (int i=0;i<6;i++){
                for (int j=1;j<=33;j++){
                    if (temp[i]==j)
                        red[j-1]=red[j-1]+1;
                }
            }
            //   统计蓝球出现的次数
            for (int i=1;i<=16;i++){
                if (temp[6]==i)
                    blue[i-1]=blue[i-1]+1;
                }
            }

            doubleballManager.insertCalculateData(red,blue);

    }

}