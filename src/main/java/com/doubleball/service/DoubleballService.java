package com.doubleball.service;

import com.doubleball.entity.*;
import com.doubleball.manager.DoubleballManager;
import com.doubleball.mapper.DoubleballStatisticsMapper;
import com.doubleball.protocol.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static java.time.LocalDate.now;


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

    // 查询记录，没有查询条件（从"双色球信息统计表doubleballstatistics"）
    public ServiceResult<ListDoubleballStatistics> queryallball(){

        List<DoubleballStatistics> list = doubleballManager.queryallball();
        ListDoubleballStatistics listDoubleballStatistics = new ListDoubleballStatistics();
        listDoubleballStatistics.setList(list);
        return ServiceResult.success(listDoubleballStatistics);
    }

    // 通过页码查询记录（从"双色球信息统计表doubleballstatistics"）
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

    //  通过id查询库中记录（从"双色球信息统计表doubleballstatistics"）
    public ServiceResult<DoubleballStatistics> getBallById(int id){
        DoubleballStatistics doubleballStatistics =new DoubleballStatistics();
        doubleballStatistics = doubleballManager.getBallById(id);
        return ServiceResult.success(doubleballStatistics);
    }

    //  插入记录（到"双色球信息统计表doubleballstatistics"）
    public String insertBall(DoubleballRecord doubleballRecord){
        doubleballManager.insertBall(doubleballRecord);
        return "success" ;
    }

    //  根据id修改记录（从"双色球信息统计表doubleballstatistics"）
    public Integer updateBallById(DoubleballStatistics doubleballStatistics){
        return doubleballManager.updateBallById(doubleballStatistics);
    }

    //  根据id删除记录（从"双色球信息统计表doubleballstatistics"）
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

    //  对各种球类出现次数的统计（到"计算双色球统计信息结果表doubleballcalculate"）
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

    //  查询每个球出现的次数的统计数据（从"计算双色球统计信息结果表doubleballcalculate"）
    public ServiceResult<DoubleballCalculate> getStatistics(){
        DoubleballCalculate doubleballCalculate = new DoubleballCalculate();
        doubleballCalculate = doubleballManager.getStatistics();
        String calculatedate,retrievedate;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        calculatedate = simpleDateFormat.format(date);

        if (doubleballCalculate==null){
            LOG.info("查询出记录为空，需要先进行数据的统计插入");
            calculateBall();
            LOG.info("数据统计插入后，再次查询数据");
            doubleballCalculate = doubleballManager.getStatistics();
        }

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = doubleballCalculate.getCalculate_date();
        retrievedate = simpleDateFormat1.format(date1);

        System.out.println("今日日期是： "+calculatedate+"      查询记录的日期是： "+retrievedate);
        if (calculatedate.equals(retrievedate)){
            return ServiceResult.success(doubleballCalculate);
        } else {
            LOG.info("查询出记录不是最新记录，需要重新进行数据的统计插入");
            calculateBall();
            LOG.info("数据统计插入后，再次查询数据");
            doubleballCalculate = doubleballManager.getStatistics();
            return ServiceResult.success(doubleballCalculate);

        }
    }

    //  查询每个球出现的次数的统计数据，并分为红球统计数据和蓝球统计数据（从"计算双色球统计信息结果表doubleballcalculate"）
    public ServiceResult<DoubleballCalculateDivide> getStatisticsDivide(){

        DoubleballCalculateDivide doubleballCalculateDivide = new DoubleballCalculateDivide();

        DoubleballCalculate doubleballCalculate = new DoubleballCalculate();
        doubleballCalculate = doubleballManager.getStatistics();
        String calculatedate,retrievedate;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        calculatedate = simpleDateFormat.format(date);

        if (doubleballCalculate==null){
            LOG.info("查询出记录为空，需要先进行数据的统计插入");
            calculateBall();
            LOG.info("数据统计插入后，再次查询数据");
            doubleballCalculate = doubleballManager.getStatistics();
        }

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = doubleballCalculate.getCalculate_date();
        retrievedate = simpleDateFormat1.format(date1);

        System.out.println("今日日期是： "+calculatedate+"      查询记录的日期是： "+retrievedate);

        List<DoubleballCalculateData> redballs=new ArrayList<>();
        List<DoubleballCalculateData> blueballs=new ArrayList<>();
        int[] redballsnum = new int[33];
        int[] blueballsnum = new int[16];
        DoubleballCalculateData doubleballCalculateData = new DoubleballCalculateData();

        if (calculatedate.equals(retrievedate)){
            //  提取红球记录
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_one(),"红球1"));
            redballsnum[0] = doubleballCalculate.getRed_one();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_two(),"红球2"));
            redballsnum[1] = doubleballCalculate.getRed_two();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_three(),"红球3"));
            redballsnum[2] = doubleballCalculate.getRed_three();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_four(),"红球4"));
            redballsnum[3] = doubleballCalculate.getRed_four();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_five(),"红球5"));
            redballsnum[4] = doubleballCalculate.getRed_five();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_six(),"红球6"));
            redballsnum[5] = doubleballCalculate.getRed_six();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_seven(),"红球7"));
            redballsnum[6] = doubleballCalculate.getRed_seven();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_eight(),"红球8"));
            redballsnum[7] = doubleballCalculate.getRed_eight();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_nine(),"红球9"));
            redballsnum[8] = doubleballCalculate.getRed_nine();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_ten(),"红球10"));
            redballsnum[9] = doubleballCalculate.getRed_ten();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_eleven(),"红球11"));
            redballsnum[10] = doubleballCalculate.getRed_eleven();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twelve(),"红球12"));
            redballsnum[11] = doubleballCalculate.getRed_twelve();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_thirteen(),"红球13"));
            redballsnum[12] = doubleballCalculate.getRed_thirteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_fourteen(),"红球14"));
            redballsnum[13] = doubleballCalculate.getRed_fourteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_fifteen(),"红球15"));
            redballsnum[14] = doubleballCalculate.getRed_fifteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_sixteen(),"红球16"));
            redballsnum[15] = doubleballCalculate.getRed_sixteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_seventeen(),"红球17"));
            redballsnum[16] = doubleballCalculate.getRed_seventeen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_eighteen(),"红球18"));
            redballsnum[17] = doubleballCalculate.getRed_eighteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_nineteen(),"红球19"));
            redballsnum[18] = doubleballCalculate.getRed_nineteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty(),"红球20"));
            redballsnum[19] = doubleballCalculate.getRed_twenty();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_one(),"红球21"));
            redballsnum[20] = doubleballCalculate.getRed_twenty_one();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_two(),"红球22"));
            redballsnum[21] = doubleballCalculate.getRed_twenty_two();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_three(),"红球23"));
            redballsnum[22] = doubleballCalculate.getRed_twenty_three();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_four(),"红球24"));
            redballsnum[23] = doubleballCalculate.getRed_twenty_four();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_five(),"红球25"));
            redballsnum[24] = doubleballCalculate.getRed_twenty_five();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_six(),"红球26"));
            redballsnum[25] = doubleballCalculate.getRed_twenty_six();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_seven(),"红球27"));
            redballsnum[26] = doubleballCalculate.getRed_twenty_seven();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_eight(),"红球28"));
            redballsnum[27] = doubleballCalculate.getRed_twenty_eight();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_nine(),"红球29"));
            redballsnum[28] = doubleballCalculate.getRed_twenty_nine();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_thirty(),"红球30"));
            redballsnum[29] = doubleballCalculate.getRed_thirty();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_thirty_one(),"红球31"));
            redballsnum[30] = doubleballCalculate.getRed_thirty_one();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_thirty_two(),"红球32"));
            redballsnum[31] = doubleballCalculate.getRed_thirty_two();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_thirty_three(),"红球33"));
            redballsnum[32] = doubleballCalculate.getRed_thirty_three();
            //  提取蓝球记录
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_one(),"蓝球1"));
            blueballsnum[0] = doubleballCalculate.getBlue_one();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_two(),"蓝球2"));
            blueballsnum[1] = doubleballCalculate.getBlue_two();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_three(),"蓝球3"));
            blueballsnum[2] = doubleballCalculate.getBlue_three();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_four(),"蓝球4"));
            blueballsnum[3] = doubleballCalculate.getBlue_four();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_five(),"蓝球5"));
            blueballsnum[4] = doubleballCalculate.getBlue_five();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_six(),"蓝球6"));
            blueballsnum[5] = doubleballCalculate.getBlue_six();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_seven(),"蓝球7"));
            blueballsnum[6] = doubleballCalculate.getBlue_seven();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_eight(),"蓝球8"));
            blueballsnum[7] = doubleballCalculate.getBlue_eight();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_nine(),"蓝球9"));
            blueballsnum[8] = doubleballCalculate.getBlue_nine();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_ten(),"蓝球10"));
            blueballsnum[9] = doubleballCalculate.getBlue_ten();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_eleven(),"蓝球11"));
            blueballsnum[10] = doubleballCalculate.getBlue_eleven();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_twelve(),"蓝球12"));
            blueballsnum[11] = doubleballCalculate.getBlue_twelve();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_thirteen(),"蓝球13"));
            blueballsnum[12] = doubleballCalculate.getBlue_thirteen();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_fourteen(),"蓝球14"));
            blueballsnum[13] = doubleballCalculate.getBlue_fourteen();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_fifteen(),"蓝球15"));
            blueballsnum[14] = doubleballCalculate.getBlue_fifteen();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_sixteen(),"蓝球16"));
            blueballsnum[15] = doubleballCalculate.getBlue_sixteen();

            doubleballCalculateDivide.setRedballs(redballs);
            doubleballCalculateDivide.setBlueballs(blueballs);
            doubleballCalculateDivide.setRedballsnum(redballsnum);
            doubleballCalculateDivide.setBlueballsnum(blueballsnum);

            return ServiceResult.success(doubleballCalculateDivide);
        } else {
            LOG.info("查询出记录不是最新记录，需要重新进行数据的统计插入");
            calculateBall();
            LOG.info("数据统计插入后，再次查询数据");
            doubleballCalculate = doubleballManager.getStatistics();
            //  提取红球记录
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_one(),"红球1"));
            redballsnum[0] = doubleballCalculate.getRed_one();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_two(),"红球2"));
            redballsnum[1] = doubleballCalculate.getRed_two();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_three(),"红球3"));
            redballsnum[2] = doubleballCalculate.getRed_three();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_four(),"红球4"));
            redballsnum[3] = doubleballCalculate.getRed_four();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_five(),"红球5"));
            redballsnum[4] = doubleballCalculate.getRed_five();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_six(),"红球6"));
            redballsnum[5] = doubleballCalculate.getRed_six();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_seven(),"红球7"));
            redballsnum[6] = doubleballCalculate.getRed_seven();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_eight(),"红球8"));
            redballsnum[7] = doubleballCalculate.getRed_eight();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_nine(),"红球9"));
            redballsnum[8] = doubleballCalculate.getRed_nine();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_ten(),"红球10"));
            redballsnum[9] = doubleballCalculate.getRed_ten();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_eleven(),"红球11"));
            redballsnum[10] = doubleballCalculate.getRed_eleven();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twelve(),"红球12"));
            redballsnum[11] = doubleballCalculate.getRed_twelve();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_thirteen(),"红球13"));
            redballsnum[12] = doubleballCalculate.getRed_thirteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_fourteen(),"红球14"));
            redballsnum[13] = doubleballCalculate.getRed_fourteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_fifteen(),"红球15"));
            redballsnum[14] = doubleballCalculate.getRed_fifteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_sixteen(),"红球16"));
            redballsnum[15] = doubleballCalculate.getRed_sixteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_seventeen(),"红球17"));
            redballsnum[16] = doubleballCalculate.getRed_seventeen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_eighteen(),"红球18"));
            redballsnum[17] = doubleballCalculate.getRed_eighteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_nineteen(),"红球19"));
            redballsnum[18] = doubleballCalculate.getRed_nineteen();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty(),"红球20"));
            redballsnum[19] = doubleballCalculate.getRed_twenty();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_one(),"红球21"));
            redballsnum[20] = doubleballCalculate.getRed_twenty_one();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_two(),"红球22"));
            redballsnum[21] = doubleballCalculate.getRed_twenty_two();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_three(),"红球23"));
            redballsnum[22] = doubleballCalculate.getRed_twenty_three();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_four(),"红球24"));
            redballsnum[23] = doubleballCalculate.getRed_twenty_four();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_five(),"红球25"));
            redballsnum[24] = doubleballCalculate.getRed_twenty_five();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_six(),"红球26"));
            redballsnum[25] = doubleballCalculate.getRed_twenty_six();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_seven(),"红球27"));
            redballsnum[26] = doubleballCalculate.getRed_twenty_seven();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_eight(),"红球28"));
            redballsnum[27] = doubleballCalculate.getRed_twenty_eight();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_twenty_nine(),"红球29"));
            redballsnum[28] = doubleballCalculate.getRed_twenty_nine();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_thirty(),"红球30"));
            redballsnum[29] = doubleballCalculate.getRed_thirty();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_thirty_one(),"红球31"));
            redballsnum[30] = doubleballCalculate.getRed_thirty_one();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_thirty_two(),"红球32"));
            redballsnum[31] = doubleballCalculate.getRed_thirty_two();
            redballs.add(new DoubleballCalculateData(doubleballCalculate.getRed_thirty_three(),"红球33"));
            redballsnum[32] = doubleballCalculate.getRed_thirty_three();
            //  提取蓝球记录
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_one(),"蓝球1"));
            blueballsnum[0] = doubleballCalculate.getBlue_one();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_two(),"蓝球2"));
            blueballsnum[1] = doubleballCalculate.getBlue_two();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_three(),"蓝球3"));
            blueballsnum[2] = doubleballCalculate.getBlue_three();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_four(),"蓝球4"));
            blueballsnum[3] = doubleballCalculate.getBlue_four();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_five(),"蓝球5"));
            blueballsnum[4] = doubleballCalculate.getBlue_five();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_six(),"蓝球6"));
            blueballsnum[5] = doubleballCalculate.getBlue_six();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_seven(),"蓝球7"));
            blueballsnum[6] = doubleballCalculate.getBlue_seven();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_eight(),"蓝球8"));
            blueballsnum[7] = doubleballCalculate.getBlue_eight();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_nine(),"蓝球9"));
            blueballsnum[8] = doubleballCalculate.getBlue_nine();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_ten(),"蓝球10"));
            blueballsnum[9] = doubleballCalculate.getBlue_ten();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_eleven(),"蓝球11"));
            blueballsnum[10] = doubleballCalculate.getBlue_eleven();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_twelve(),"蓝球12"));
            blueballsnum[11] = doubleballCalculate.getBlue_twelve();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_thirteen(),"蓝球13"));
            blueballsnum[12] = doubleballCalculate.getBlue_thirteen();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_fourteen(),"蓝球14"));
            blueballsnum[13] = doubleballCalculate.getBlue_fourteen();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_fifteen(),"蓝球15"));
            blueballsnum[14] = doubleballCalculate.getBlue_fifteen();
            blueballs.add(new DoubleballCalculateData(doubleballCalculate.getBlue_sixteen(),"蓝球16"));
            blueballsnum[15] = doubleballCalculate.getBlue_sixteen();

            doubleballCalculateDivide.setRedballs(redballs);
            doubleballCalculateDivide.setBlueballs(blueballs);
            doubleballCalculateDivide.setRedballsnum(redballsnum);
            doubleballCalculateDivide.setBlueballsnum(blueballsnum);

            return ServiceResult.success(doubleballCalculateDivide);
        }
    }


}