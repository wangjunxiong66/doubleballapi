package com.doubleball.mapper;

import com.doubleball.entity.DoubleballStatistics;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/8/13 下午2:05
 */
@Mapper
public interface DoubleballStatisticsMapper {

    // 查询所有记录，没有查询条件
    @Select("select * from DoubleballStatistics")
    List<DoubleballStatistics> getBallByAll();

    // 查询所有记录的总数
    @Select("select count(*) from DoubleballStatistics")
    int getBallByAllCount();

    // 查询所有记录，通过页面查询
    @Select("select * from DoubleballStatistics limit #{page},#{size}")
    List<DoubleballStatistics> getBallByAllAndPage(@Param("page") int page,@Param("size") int size);

    // 查询所有记录，通过id查询
    @Select("select * from DoubleballStatistics where id=#{id}")
    DoubleballStatistics getBallById(@Param("id") Integer id);

    // 插入记录
    @Insert("insert into DoubleballStatistics(issue,red_one,red_two,red_three,red_four,red_five,red_six,blue,draw_prize_date) values(#{issue},#{red_one},#{red_two},#{red_three},#{red_four},#{red_five},#{red_six},#{blue},#{draw_prize_date})")
    void insertBall(@Param("issue") Integer issue,@Param("red_one") Integer red_one,@Param("red_two") Integer red_two,
                    @Param("red_three") Integer red_three,@Param("red_four") Integer red_four,@Param("red_five") Integer red_five,
                    @Param("red_six") Integer red_six,@Param("blue") Integer blue,@Param("draw_prize_date") String draw_prize_date);

    // 通过id更新记录
    @Update("update DoubleballStatistics set issue=#{issue},red_one=#{red_one},red_two=#{red_two},red_three=#{red_three},red_four=#{red_four},red_five=#{red_five},red_six=#{red_six},blue=#{blue},draw_prize_date=#{draw_prize_date} where id=#{id}")
    int updateBallById(@Param("id") Integer id,@Param("issue") Integer issue,@Param("red_one") Integer red_one,@Param("red_two") Integer red_two,
                   @Param("red_three") Integer red_three,@Param("red_four") Integer red_four,@Param("red_five") Integer red_five,
                   @Param("red_six") Integer red_six,@Param("blue") Integer blue,@Param("draw_prize_date") String draw_prize_date);

    // 通过id删除记录
    @Delete("delete from DoubleballStatistics where id=#{id}")
    void deleteBallById(@Param("id") Integer id);


}
