package com.doubleball.manager;

import com.doubleball.entity.DoubleballStatistics;
import com.doubleball.mapper.DoubleballCalculateMapper;
import com.doubleball.mapper.DoubleballStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/8/14 上午9:40
 */
@Component
public class DoubleballManager {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DoubleballStatisticsMapper doubleballStatisticsMapper;

    @Autowired
    private DoubleballCalculateMapper doubleballCalculateMapper;

    //  导入历史数据
    public void importHistoricalData(){
        // 以下是将原始数据导入到记录表中
        try {
            // 读取历史数据文件内容
            LOG.info("开始读取历史数据...........");
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/main/resources/file/historydata.csv"));
            String line = null;
            LOG.info("开始初始化数据...........");
            while ((line = reader.readLine()) != null) {
                // CSV格式文件为逗号分隔符文件，这里根据逗号切分
                String item[] = line.split(",");
                //  将字符串内容提取出来，存在数组中，开奖日期单独存放
                int[] item1 = new int[8];
                for(int i=0;i<item.length-1;i++){
                    String tempstr=item[i].trim();
                    int temp = Integer.parseInt(tempstr);
                    item1[i]=temp;
                }
                String draw_prize_date = item[item.length-1] ;
                int issue, red_one, red_two, red_three, red_four, red_five, red_six, blue;
                issue=item1[0];
                red_one=item1[1];
                red_two=item1[2];
                red_three=item1[3];
                red_four=item1[4];
                red_five=item1[5];
                red_six=item1[6];
                blue=item1[7];

                //  将数组插入到数据库
                doubleballStatisticsMapper.insertBall(issue, red_one, red_two, red_three, red_four, red_five, red_six, blue, draw_prize_date);
            }
            LOG.info("初始化数据完成...........");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  获取记录表所有记录
    public List<DoubleballStatistics> queryallball(){
        return doubleballStatisticsMapper.getBallByAll();
    }

    //  生成随机数字
    public DoubleballStatistics getRandomNum(){

        int blueball;
        Random ranblue = new Random() ;
        blueball=ranblue.nextInt(17);
        LOG.info("随机生成蓝球    "+blueball);

        int[] redball=new int[6];
        Random ranred = new Random() ;
        Set<Integer> ran=new TreeSet<Integer>() ;
        int i=0;
        while (ran.size()<6){
            ran.add(ranred.nextInt(33)+1);
        }
        Iterator<Integer> integerIterator = ran.iterator();
        while(integerIterator.hasNext()){
//            System.out.println(" i  是    "+i);
            redball[i]=integerIterator.next();
//            System.out.println("redball["+i+"]   是    "+redball[i]);
            i++;
        }
        System.out.println("红球是："+redball[0]+" "+redball[1]+" "+redball[2]+" "+redball[3]+" "+redball[4]+" "+redball[5]);

        DoubleballStatistics doubleballStatistics = new DoubleballStatistics();
        doubleballStatistics.setRed_one(redball[0]);
        doubleballStatistics.setRed_two(redball[1]);
        doubleballStatistics.setRed_three(redball[2]);
        doubleballStatistics.setRed_four(redball[3]);
        doubleballStatistics.setRed_five(redball[4]);
        doubleballStatistics.setRed_six(redball[5]);
        doubleballStatistics.setBlue(blueball);
        return doubleballStatistics;
    }

    //   将统计数据插入到doubleballcalculate表
    public void insertCalculateData(int[] red,int[] blue){
        int red_one,red_two,red_three,red_four,red_five,red_six,red_seven,red_eight,red_nine,red_ten,red_eleven,red_twelve,red_thirteen,red_fourteen,red_fifteen,red_sixteen,red_seventeen,red_eighteen,red_nineteen,red_twenty,red_twenty_one,red_twenty_two,red_twenty_three,red_twenty_four,red_twenty_five,red_twenty_six,red_twenty_seven,red_twenty_eight,red_twenty_nine,red_thirty,red_thirty_one, red_thirty_two,red_thirty_three,blue_one,blue_two,blue_three,blue_four,blue_five,blue_six,blue_seven,blue_eight,blue_nine,blue_ten,blue_eleven,blue_twelve,blue_thirteen,blue_fourteen,blue_fifteen,blue_sixteen;
        red_one=red[0];
        red_two=red[1];
        red_three=red[2];
        red_four=red[3];
        red_five=red[4];
        red_six=red[5];
        red_seven=red[6];
        red_eight=red[7];
        red_nine=red[8];
        red_ten=red[9];
        red_eleven=red[10];
        red_twelve=red[11];
        red_thirteen=red[12];
        red_fourteen=red[13];
        red_fifteen=red[14];
        red_sixteen=red[15];
        red_seventeen=red[16];
        red_eighteen=red[17];
        red_nineteen=red[18];
        red_twenty=red[19];
        red_twenty_one=red[20];
        red_twenty_two=red[21];
        red_twenty_three=red[22];
        red_twenty_four=red[23];
        red_twenty_five=red[24];
        red_twenty_six=red[25];
        red_twenty_seven=red[26];
        red_twenty_eight=red[27];
        red_twenty_nine=red[28];
        red_thirty=red[29];
        red_thirty_one=red[30];
        red_thirty_two=red[31];
        red_thirty_three=red[32];

        blue_one=blue[0];
        blue_two=blue[1];
        blue_three=blue[2];
        blue_four=blue[3];
        blue_five=blue[4];
        blue_six=blue[5];
        blue_seven=blue[6];
        blue_eight=blue[7];
        blue_nine=blue[8];
        blue_ten=blue[9];
        blue_eleven=blue[10];
        blue_twelve=blue[11];
        blue_thirteen=blue[12];
        blue_fourteen=blue[13];
        blue_fifteen=blue[14];
        blue_sixteen=blue[15];

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        String calculate_date = simpleDateFormat.format(new Date());
        doubleballCalculateMapper.insertBallCalculate(red_one,red_two,red_three,red_four,red_five,red_six,red_seven,red_eight,red_nine,red_ten,red_eleven,red_twelve,red_thirteen,red_fourteen,red_fifteen,red_sixteen,red_seventeen,red_eighteen,red_nineteen,red_twenty,red_twenty_one,red_twenty_two,red_twenty_three,red_twenty_four,red_twenty_five,red_twenty_six,red_twenty_seven,red_twenty_eight,red_twenty_nine,red_thirty,red_thirty_one, red_thirty_two,red_thirty_three,blue_one,blue_two,blue_three,blue_four,blue_five,blue_six,blue_seven,blue_eight,blue_nine,blue_ten,blue_eleven,blue_twelve,blue_thirteen,blue_fourteen,blue_fifteen,blue_sixteen, calculate_date);

    }

}
