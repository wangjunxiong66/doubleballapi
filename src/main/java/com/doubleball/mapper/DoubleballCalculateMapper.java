package com.doubleball.mapper;

import com.doubleball.entity.DoubleballCalculate;
import org.apache.ibatis.annotations.*;
//import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/8/14 下午3:14
 */
@Mapper
public interface DoubleballCalculateMapper {

    // 通过id查询记录
    @Select("select * from doubleballcalculate where id=#{id}")
    DoubleballCalculate getBallCalculateById(@Param("id") Integer id);

    // 插入记录
    @Insert("insert into doubleballcalculate(red_one,red_two,red_three,red_four,red_five,red_six,red_seven,red_eight,red_nine,red_ten,red_eleven,red_twelve,red_thirteen,red_fourteen,red_fifteen,red_sixteen,red_seventeen,red_eighteen,red_nineteen,red_twenty,red_twenty_one,red_twenty_two,red_twenty_three,red_twenty_four,red_twenty_five,red_twenty_six,red_twenty_seven,red_twenty_eight,red_twenty_nine,red_thirty,red_thirty_one,red_thirty_two,red_thirty_three,blue_one,blue_two,blue_three,blue_four,blue_five,blue_six,blue_seven,blue_eight,blue_nine,blue_ten,blue_eleven,blue_twelve,blue_thirteen,blue_fourteen,blue_fifteen,blue_sixteen,calculate_date) values(#{red_one},#{red_two},#{red_three},#{red_four},#{red_five},#{red_six},#{red_seven},#{red_eight},#{red_nine},#{red_ten},#{red_eleven},#{red_twelve},#{red_thirteen},#{red_fourteen},#{red_fifteen},#{red_sixteen},#{red_seventeen},#{red_eighteen},#{red_nineteen},#{red_twenty},#{red_twenty_one},#{red_twenty_two},#{red_twenty_three},#{red_twenty_four},#{red_twenty_five},#{red_twenty_six},#{red_twenty_seven},#{red_twenty_eight},#{red_twenty_nine},#{red_thirty},#{red_thirty_one},#{red_thirty_two},#{red_thirty_three},#{blue_one},#{blue_two},#{blue_three},#{blue_four},#{blue_five},#{blue_six},#{blue_seven},#{blue_eight},#{blue_nine},#{blue_ten},#{blue_eleven},#{blue_twelve},#{blue_thirteen},#{blue_fourteen},#{blue_fifteen},#{blue_sixteen},#{calculate_date})")
    void insertBallCalculate(@Param("red_one") Integer red_one,@Param("red_two") Integer red_two, @Param("red_three") Integer red_three,@Param("red_four") Integer red_four,@Param("red_five") Integer red_five, @Param("red_six") Integer red_six, @Param("red_seven") Integer red_seven,@Param("red_eight") Integer red_eight,@Param("red_nine") Integer red_nine,@Param("red_ten") Integer red_ten,@Param("red_eleven") Integer red_eleven,@Param("red_twelve") Integer red_twelve, @Param("red_thirteen") Integer red_thirteen,@Param("red_fourteen") Integer red_fourteen,@Param("red_fifteen") Integer red_fifteen,@Param("red_sixteen") Integer red_sixteen,@Param("red_seventeen") Integer red_seventeen, @Param("red_eighteen") Integer red_eighteen,@Param("red_nineteen") Integer red_nineteen,@Param("red_twenty") Integer red_twenty,@Param("red_twenty_one") Integer red_twenty_one,@Param("red_twenty_two") Integer red_twenty_two, @Param("red_twenty_three") Integer red_twenty_three,@Param("red_twenty_four") Integer red_twenty_four,@Param("red_twenty_five") Integer red_twenty_five,@Param("red_twenty_six") Integer red_twenty_six, @Param("red_twenty_seven") Integer red_twenty_seven,@Param("red_twenty_eight") Integer red_twenty_eight,@Param("red_twenty_nine") Integer red_twenty_nine,@Param("red_thirty") Integer red_thirty, @Param("red_thirty_one") Integer red_thirty_one,@Param("red_thirty_two") Integer red_thirty_two,@Param("red_thirty_three") Integer red_thirty_three,@Param("blue_one") Integer blue_one,@Param("blue_two") Integer blue_two, @Param("blue_three") Integer blue_three,@Param("blue_four") Integer blue_four,@Param("blue_five") Integer blue_five,@Param("blue_six") Integer blue_six,@Param("blue_seven") Integer blue_seven, @Param("blue_eight") Integer blue_eight,@Param("blue_nine") Integer blue_nine,@Param("blue_ten") Integer blue_ten,@Param("blue_eleven") Integer blue_eleven,@Param("blue_twelve") Integer blue_twelve, @Param("blue_thirteen") Integer blue_thirteen,@Param("blue_fourteen") Integer blue_fourteen,@Param("blue_fifteen") Integer blue_fifteen,@Param("blue_sixteen") Integer blue_sixteen,@Param("calculate_date") String calculate_date);

    // 通过id删除记录
    @Delete("delete from doubleballcalculate where id=#{id}")
    void deleteBllCalculate(@Param("id") Integer id);

}
