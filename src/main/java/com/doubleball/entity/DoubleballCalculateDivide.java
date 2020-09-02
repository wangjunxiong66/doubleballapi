package com.doubleball.entity;

import java.util.List;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/9/1 下午4:08
 * 在统计数字出现的次数基础上，将红球和蓝球统计数据分开
 */
public class DoubleballCalculateDivide {
    private List<DoubleballCalculateData> redballs;
    private List<DoubleballCalculateData> blueballs;
    private int[] redballsnum;
    private int[] blueballsnum;

    public int[] getRedballsnum() {
        return redballsnum;
    }

    public void setRedballsnum(int[] redballsnum) {
        this.redballsnum = redballsnum;
    }

    public int[] getBlueballsnum() {
        return blueballsnum;
    }

    public void setBlueballsnum(int[] blueballsnum) {
        this.blueballsnum = blueballsnum;
    }

    public List<DoubleballCalculateData> getRedballs() {
        return redballs;
    }

    public void setRedballs(List<DoubleballCalculateData> redballs) {
        this.redballs = redballs;
    }

    public List<DoubleballCalculateData> getBlueballs() {
        return blueballs;
    }

    public void setBlueballs(List<DoubleballCalculateData> blueballs) {
        this.blueballs = blueballs;
    }
}
