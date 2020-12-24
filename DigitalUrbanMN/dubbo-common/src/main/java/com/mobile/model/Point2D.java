package com.mobile.model;

import java.io.Serializable;

/**
 * 经纬度实体类
 */
public class Point2D implements Serializable {
    //经度
    public double x;
    //维度
    public double y;

    /**
     *
     * @param x 经度
     * @param y 维度
     */
    public Point2D(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * 获取经度
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * 经度赋值
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * 获取维度
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * 维度赋值
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

}