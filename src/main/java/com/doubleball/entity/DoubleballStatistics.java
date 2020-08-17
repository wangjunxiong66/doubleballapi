package com.doubleball.entity;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/8/13 上午9:14
 * 每期开奖记录表
 */
public class DoubleballStatistics {

    private Integer id;
    private Integer issue;
    private Integer red_one;
    private Integer red_two;
    private Integer red_three;
    private Integer red_four;
    private Integer red_five;
    private Integer red_six;
    private Integer blue;
    private String draw_prize_date;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public Integer getRed_one() {
        return red_one;
    }

    public void setRed_one(int red_one) {
        this.red_one = red_one;
    }

    public Integer getRed_two() {
        return red_two;
    }

    public void setRed_two(int red_two) {
        this.red_two = red_two;
    }

    public Integer getRed_three() {
        return red_three;
    }

    public void setRed_three(int red_three) {
        this.red_three = red_three;
    }

    public Integer getRed_four() {
        return red_four;
    }

    public void setRed_four(int red_four) {
        this.red_four = red_four;
    }

    public Integer getRed_five() {
        return red_five;
    }

    public void setRed_five(int red_five) {
        this.red_five = red_five;
    }

    public Integer getRed_six() {
        return red_six;
    }

    public void setRed_six(int red_six) {
        this.red_six = red_six;
    }

    public Integer getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public String getDraw_prize_date() {
        return draw_prize_date;
    }

    public void setDraw_prize_date(String draw_prize_date) {
        this.draw_prize_date = draw_prize_date;
    }

}
