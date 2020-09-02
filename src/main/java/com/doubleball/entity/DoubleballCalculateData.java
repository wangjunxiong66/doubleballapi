package com.doubleball.entity;

/**
 * @author wjx
 * @version 1.0
 * @date 2020/9/1 下午5:52
 */
public class DoubleballCalculateData {
    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoubleballCalculateData() {
    }

    public DoubleballCalculateData(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
